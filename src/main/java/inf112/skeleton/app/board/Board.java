package inf112.skeleton.app.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.cards.ICard;

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

    ConveyorBelts conveyorBelts;
    Walls walls;



    public Board(){
        //createRobots(numPlayers);

        map = new TmxMapLoader().load("maps/Map1.tmx");

        mapLayers = new HashMap<>();
        mapLayers.put("conveyor", (TiledMapTileLayer) map.getLayers().get("Conveyor"));
        mapLayers.put("flag", (TiledMapTileLayer) map.getLayers().get("Flag"));
        mapLayers.put("gear", (TiledMapTileLayer) map.getLayers().get("Gear"));
        mapLayers.put("ground", (TiledMapTileLayer) map.getLayers().get("Ground"));
        mapLayers.put("hole", (TiledMapTileLayer) map.getLayers().get("Hole"));
        mapLayers.put("playerLayer", (TiledMapTileLayer) map.getLayers().get("Player"));
        mapLayers.put("wall", (TiledMapTileLayer) map.getLayers().get("Wall"));
        playerLayer = mapLayers.get("playerLayer");
        conveyorBelts = new ConveyorBelts();
        //walls = new Walls();

    }


    public TiledMap getMap(){
        return map;
    }

    public HashMap<String, TiledMapTileLayer> getTiledMapTileLayers() { return mapLayers; }

    public TiledMapTileLayer getPlayerLayer(){
        return mapLayers.get("playerLayer");
    }

    public void doRobotTurn(ICard currentCard) {
        System.out.println(currentCard.toString()+" belongs to "+currentCard.getPlayer().getPlayerNumber());
        Robot currentRobot = currentCard.getPlayer().getRobot();
        Pos oldPos = currentRobot.getPos().copy();
        currentCard.action();
        updatePlayer(oldPos, currentRobot);
    }


    public void updatePlayer(Pos oldPos, Robot player){
        getPlayerLayer().setCell(oldPos.getPosX(), oldPos.getPosY(), null);
        if(checkPos(player.getPos()) == SUICIDALMOVE){player.die();}
        playerLayer.setCell(player.getPos().getPosX(), player.getPos().getPosY(), player.getCurrentState());
    }

    private int checkPos(Pos pos) {
        if (pos.getPosX() >= 0 && pos.getPosX() < BOARDWIDTH && pos.getPosY() >= 0 && pos.getPosY() < BOARDHEIGHT) {
            try {
                if (mapLayers.get("hole").getCell(pos.getPosX(), pos.getPosY()) != null) return SUICIDALMOVE;
                else if (mapLayers.get("flag").getCell(pos.getPosX(), pos.getPosY()) != null) {

                    Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/Sound effects/WeAreTheChampions.mp3"));
                    long id = sound.play(1.0f);

                    robot.win();
                    return LEGALMOVE;
                }

            }
            catch (NullPointerException e)
            {
                //Do nothing
            }
        }
        else {

            Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/Sound effects/FBI_OpenUp.mp3"));
            sound.play(1.0f);
            return SUICIDALMOVE;
        }

        return LEGALMOVE;
    }

    //TODO Give own classes
    public void doGroundTileEffects(int round){
        Pos currentPos;
        for (Robot robot : listOfRobots) {
            currentPos = robot.getPos().copy();
            if (mapLayers.get("conveyor").getCell(currentPos.getPosX(), currentPos.getPosY()) != null) {
                conveyorTypes(currentPos, robot);
                updatePlayer(currentPos, robot);
                currentPos = robot.getPos().copy();
            };
            if (mapLayers.get("hole").getCell(currentPos.getPosX(), currentPos.getPosY()) != null) { robot.die(); }
            if (mapLayers.get("gear").getCell(currentPos.getPosX(), currentPos.getPosY()) != null) {
                gearTypes(currentPos, robot);
            }
            if (mapLayers.get("wall").getCell(currentPos.getPosX(), currentPos.getPosY()) != null) {  }; //TODO Add walls

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
        listOfRobots[i] = new Robot((i+1)*2, (i+1)*2, models[i], this);
        }
        robot = listOfRobots[0];
        for(Robot robot : listOfRobots){
            updatePlayer(new Pos(), robot);
        }
    }

    public Robot[] getRobots(){
        return listOfRobots;
    }

    public String intToPlayerModel(int i){
        switch (i){
            case 0:
                return "robots/pika.png";
            case 1:
                return "robots/charmander.png";
            case 2:
                return "robots/bulbasaur.png";
            case 3:
                return "robots/marsvin.png";
            case 4:
                return "robots/marsvin2.png";
            default:
                return "player.png";
        }
    }




}


