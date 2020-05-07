package inf112.skeleton.app.board;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.cards.ICard;

import java.util.ArrayList;
import java.util.HashMap;

public class Board extends InputAdapter {

    private TiledMap map;
    private HashMap<String, TiledMapTileLayer> mapLayers;
    private TiledMapTileLayer playerLayer;
    private Robot robot;
    private Robot[] listOfRobots;
    private final int BOARDWIDTH = 12;
    private final int BOARDHEIGHT = 12;
    private final int LEGALMOVE = 1; //For normal board movement
    private final int ILLEGALMOVE = 0; //For walls/obstacles
    private final int SUICIDALMOVE = -1; //For holes/fall off map
    private final boolean RIGHT = true;
    private final boolean LEFT = false;
    private ArrayList<Lazers> LazerList = new ArrayList<>();
    private ArrayList<Grills> GrillList = new ArrayList<>();
    private ArrayList<Pushers> PusherList = new ArrayList<>();
    private ArrayList<Repair> RepairList = new ArrayList<>();
    private ArrayList<Robot> DeadRobots = new ArrayList<>();


    ConveyorBelts conveyorBelts;
    Walls walls;




    public Board(){
        map = new TmxMapLoader().load("maps/Map1.tmx");
        mapLayers = new HashMap<>();
        mapLayers.put("conveyor", (TiledMapTileLayer) map.getLayers().get("Conveyor"));
        mapLayers.put("flag", (TiledMapTileLayer) map.getLayers().get("Flag"));
        mapLayers.put("gear", (TiledMapTileLayer) map.getLayers().get("Gear"));
        mapLayers.put("ground", (TiledMapTileLayer) map.getLayers().get("Ground"));
        mapLayers.put("hole", (TiledMapTileLayer) map.getLayers().get("Hole"));
        mapLayers.put("playerLayer", (TiledMapTileLayer) map.getLayers().get("Player"));
        mapLayers.put("wall", (TiledMapTileLayer) map.getLayers().get("Wall"));
        mapLayers.put("lazer", (TiledMapTileLayer) map.getLayers().get("Lazer"));
        mapLayers.put("grill", (TiledMapTileLayer) map.getLayers().get("Burner"));
        mapLayers.put("push", (TiledMapTileLayer) map.getLayers().get("Push"));
        mapLayers.put("repair", (TiledMapTileLayer) map.getLayers().get("Repair"));
        playerLayer = mapLayers.get("playerLayer");
        conveyorBelts = new ConveyorBelts(this);
        walls = new Walls(this);
        createBoardElems();
    }


    public TiledMap getMap(){
        return map;
    }

    public Walls getWalls() {return walls; }

    public Robot[] getListOfRobots() {return listOfRobots; }

    public HashMap<String, TiledMapTileLayer> getTiledMapTileLayers() { return mapLayers; }

    public TiledMapTileLayer getPlayerLayer(){
        return mapLayers.get("playerLayer");
    }

    public void doRobotTurn(ICard currentCard) {
        Robot currentRobot = currentCard.getPlayer().getRobot();
        Pos oldPos = currentRobot.getPos().copy();
        currentCard.action();
        updatePlayer(oldPos, currentRobot);
    }

    
    public void updatePlayer(Pos oldPos, Robot robot){
        getPlayerLayer().setCell(oldPos.getPosX(), oldPos.getPosY(), null);
        if(checkPos(robot.getPos()) == SUICIDALMOVE){robot.die();}
        else{
            playerLayer.setCell(robot.getPos().getPosX(), robot.getPos().getPosY(), robot.getCurrentState());
            checkFlags(robot);
        }
    }

    public ArrayList<Robot> getDeadRobots(){
        return DeadRobots;
    }



    public int checkPos(Pos pos) {
        if (pos.getPosX() >= 0 && pos.getPosX() < BOARDWIDTH && pos.getPosY() >= 0 && pos.getPosY() < BOARDHEIGHT) {
            //Check for hole
            try {
                if (mapLayers.get("hole").getCell(pos.getPosX(), pos.getPosY()) != null) return SUICIDALMOVE;
            }
            catch (NullPointerException e) {}
        }
        else {
            //Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/Sound effects/FBI_OpenUp.mp3"));
            //sound.play(1.0f);
            return SUICIDALMOVE;
        }

        return LEGALMOVE;
    }

    private void checkFlags(Robot robot){
        try{
            Pos pos  = robot.getPos();
            if(mapLayers.get("flag").getCell(pos.getPosX(), pos.getPosY()) != null){
                robot.updateCheckpoint(mapLayers.get("flag").getCell(pos.getPosX(), pos.getPosY()).getTile().getId());
            }
        }catch(NullPointerException e){}
    }

    //TODO Give own classes
    public void doGroundTileEffects(int round){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            //nutthin
        }

        for (Lazers lazer : LazerList) {
            lazer.shoot();
        }

        for (Grills grill : GrillList) {
            grill.burn(round);
        }

        for (Pushers pusher : PusherList) {
            pusher.push(round);
        }



        Pos currentPos;
        for (Robot robot : listOfRobots) {



            currentPos = robot.getPos().copy();
            if (mapLayers.get("flag").getCell(currentPos.getPosX(), currentPos.getPosY()) != null){
                System.out.println("hej");
                robot.updateRespawnPoint(currentPos);
            }
            if (mapLayers.get("conveyor").getCell(currentPos.getPosX(), currentPos.getPosY()) != null) {
                conveyorTypes(currentPos, robot);
                updatePlayer(currentPos, robot);
                currentPos = robot.getPos();//.copy();
            };
            if (mapLayers.get("hole").getCell(currentPos.getPosX(), currentPos.getPosY()) != null) { robot.die(); }
            if (mapLayers.get("gear").getCell(currentPos.getPosX(), currentPos.getPosY()) != null) {
                gearTypes(currentPos, robot);
            }
            if (mapLayers.get("wall").getCell(currentPos.getPosX(), currentPos.getPosY()) != null) {  };

            for (Repair fix : RepairList) {
                if (robot.getPos().getPosX() == fix.getPos().getPosX()
                && robot.getPos().getPosY() == fix.getPos().getPosY()) {

                    System.out.println(robot.getCurrentHp());
                    fix.heal(robot);
                    System.out.println(robot.getCurrentHp());
                }

            }

        }
    }

    private void gearTypes(Pos currentPos, Robot robot) {
        TiledMapTileLayer.Cell currentGear = mapLayers.get("gear").getCell(currentPos.getPosX(), currentPos.getPosY());
        if (currentGear.getTile().getId() == 53) { robot.turn(LEFT); }
        else if (currentGear.getTile().getId() == 54) { robot.turn(RIGHT); }
    }


    private void conveyorTypes(Pos currentPos, Robot robot) {
        TiledMapTileLayer.Cell currentBelt = mapLayers.get("conveyor").getCell(currentPos.getPosX(), currentPos.getPosY());
        int beltType = currentBelt.getTile().getId();
        int aboveBeltType = -1;
        int belowBeltType = -1;
        int leftBeltType = -1;
        int rightBeltType = -1;
        TiledMapTileLayer.Cell aboveBelt = null;
        TiledMapTileLayer.Cell belowBelt = null;
        TiledMapTileLayer.Cell leftBelt = null;
        TiledMapTileLayer.Cell rightBelt = null;


        if (mapLayers.get("conveyor").getCell(currentPos.getPosX(), currentPos.getPosY()+1) != null) {
            aboveBelt = mapLayers.get("conveyor").getCell(currentPos.getPosX(), currentPos.getPosY()+1);
            aboveBeltType = aboveBelt.getTile().getId();
        }


        if (mapLayers.get("conveyor").getCell(currentPos.getPosX(), currentPos.getPosY()-1) != null) {
            belowBelt = mapLayers.get("conveyor").getCell(currentPos.getPosX(), currentPos.getPosY()-1);
            belowBeltType = belowBelt.getTile().getId();
        }

        if (mapLayers.get("conveyor").getCell(currentPos.getPosX()-1, currentPos.getPosY()) != null) {
            leftBelt = mapLayers.get("conveyor").getCell(currentPos.getPosX()-1, currentPos.getPosY());
            leftBeltType = leftBelt.getTile().getId();
        }

        if (mapLayers.get("conveyor").getCell(currentPos.getPosX()+1, currentPos.getPosY()) != null) {
            rightBelt = mapLayers.get("conveyor").getCell(currentPos.getPosX()+1, currentPos.getPosY());
            rightBeltType = rightBelt.getTile().getId();
        }


        conveyorBelts.belts(robot, beltType, aboveBeltType, belowBeltType, leftBeltType, rightBeltType);

    }

    public void createTextures(){
        playerLayer.setCell(robot.getPos().getPosX(), robot.getPos().getPosY(), robot.getCurrentState());
    }

    public void createRobots(int playerAmount, String[] models){
        listOfRobots = new Robot[playerAmount];
        for(int i = 0; i < listOfRobots.length; i++){
        listOfRobots[i] = new Robot(i, 0, models[i], this);
        }
        robot = listOfRobots[0];
        for(Robot robot : listOfRobots){
            updatePlayer(new Pos(), robot);
        }
    }

    public Robot[] getRobots(){
        return listOfRobots;
    }

    private void createBoardElems(){
        TiledMapTileLayer.Cell lazerTile;
        TiledMapTileLayer.Cell grillTile;
        TiledMapTileLayer.Cell pushTile;
        TiledMapTileLayer.Cell repairTile;
        TiledMapTileLayer.Cell flagTile;
        Pos pos;
        for (int i = 0; i < BOARDWIDTH; i++) {
            for (int j = 0; j < BOARDHEIGHT; j++) {
                pos = new Pos();
                lazerTile = mapLayers.get("lazer").getCell(i,j);
                grillTile = mapLayers.get("grill").getCell(i, j);
                pushTile = mapLayers.get("push").getCell(i, j);
                repairTile = mapLayers.get("repair").getCell(i, j);
                flagTile = mapLayers.get("flag").getCell(i, j);
                if (lazerTile != null) {
                    pos.setPos(i, j);
                    int lazerTileType = lazerTile.getTile().getId();
                    if (lazerTileType == 38) LazerList.add(new Lazers(pos, Direction.EAST, this));
                    //Lag ny "if (lazerTileType == num) for hver retning laser, vi har bare 1 for no
                }

                if (grillTile != null) {
                    pos.setPos(i, j);
                    int grillTileType = grillTile.getTile().getId();
                    if (grillTileType == 90) GrillList.add(new Grills(pos, this, true));
                    if (grillTileType == 89) GrillList.add(new Grills(pos, this, false));
                }

                if (pushTile != null) {
                    pos.setPos(i, j);
                    int pushTileType = pushTile.getTile().getId();
                    if (pushTileType == 9) PusherList.add(new Pushers(pos, this, false, Direction.SOUTH));
                    if (pushTileType == 2) PusherList.add(new Pushers(pos, this, true, Direction.WEST));
                }

                if (repairTile != null) {
                    pos.setPos(i, j);
                    int repairTileType = repairTile.getTile().getId();
                    if (repairTileType == 15) RepairList.add(new Repair(pos, this));
                }

                if (flagTile != null) {
                    pos.setPos(i, j);
                    int flagTileType = flagTile.getTile().getId();
                    if (flagTileType == 55 || flagTileType == 63 || flagTileType == 71 || flagTileType == 79) {
                        RepairList.add(new Repair(pos, this));
                    }
                }
            }
        }
    }


    public int getBOARDWIDTH(){
        return BOARDWIDTH;
    }

    public int getBOARDHEIGHT(){
        return BOARDHEIGHT;
    }


}


