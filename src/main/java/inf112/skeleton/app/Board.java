package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import java.util.HashMap;

public class Board extends InputAdapter {

    private TiledMap map;
    private HashMap<String, TiledMapTileLayer> mapLayers;
    private TiledMapTileLayer playerLayer;
    private Robot player;
    private Robot[] listOfPlayers;
    private final int BOARDWIDTH = 10;
    private final int BOARDHEIGHT = 10;
    private final int LEGALMOVE = 1; //For normal board movement
    private final int ILLEGALMOVE = 0; //For walls/obstacles
    private final int SUICIDALMOVE = -1; //For holes/fall off map
    private final boolean RIGHT = true;
    private final boolean LEFT = false;

    public Board(Robot[] players){
        listOfPlayers = players;
        player = listOfPlayers[0];

        map = new TmxMapLoader().load("testMap2.tmx");

        mapLayers = new HashMap<>();
        mapLayers.put("conveyor", (TiledMapTileLayer) map.getLayers().get("Conveyor"));
        mapLayers.put("flag", (TiledMapTileLayer) map.getLayers().get("Flag"));
        mapLayers.put("gear", (TiledMapTileLayer) map.getLayers().get("Gear"));
        mapLayers.put("ground", (TiledMapTileLayer) map.getLayers().get("Ground"));
        mapLayers.put("hole", (TiledMapTileLayer) map.getLayers().get("Hole"));
        mapLayers.put("playerLayer", (TiledMapTileLayer) map.getLayers().get("Player"));
        mapLayers.put("wall", (TiledMapTileLayer) map.getLayers().get("Wall"));
        playerLayer = mapLayers.get("playerLayer");
    }


    public TiledMap getMap(){
        return map;
    }

    public TiledMapTileLayer getPlayerLayer(){
        return mapLayers.get("playerLayer");
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
                    player.win();
                    return LEGALMOVE;
                }
            }
            catch (NullPointerException e)
            {
                //Do nothing
            }
        }
        else {
            return SUICIDALMOVE;
        }

        return LEGALMOVE;
    }

    public void doGroundTileEffects(){
        Pos currentPos;
        for (Robot robot : listOfPlayers) {
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
        if      (currentBelt.getTile().getId() == 49) robot.push(Direction.NORTH); //UP ARROW
        else if (currentBelt.getTile().getId() == 50) robot.push(Direction.SOUTH); //DOWN ARROW
        else if (currentBelt.getTile().getId() == 51) robot.push(Direction.WEST);  //LEFT ARROW
        else if (currentBelt.getTile().getId() == 52) robot.push(Direction.EAST);  //RIGHT ARROW
        else if ((currentBelt.getTile().getId() == 35)) { //UP TO RIGHT ARROW
            robot.turn(RIGHT);
            robot.push(Direction.EAST);
        }
        else if ((currentBelt.getTile().getId() == 36)) { //RIGHT TO DOWN ARROW
            System.out.println("hej");
            robot.turn(RIGHT);
            robot.push(Direction.SOUTH);
        }
        else if ((currentBelt.getTile().getId() == 43)) { //LEFT TO UP ARROW
            robot.turn(RIGHT);
            robot.push(Direction.NORTH);
        }
        else if ((currentBelt.getTile().getId() == 44)) { //DOWN TO LEFT ARROW
            robot.turn(RIGHT);
            robot.push(Direction.WEST);
        }
        else if ((currentBelt.getTile().getId() == 33)) { //LEFT TO DOWN ARROW
            robot.turn(LEFT);
            robot.push(Direction.SOUTH);
        }
        else if ((currentBelt.getTile().getId() == 34)) { //UP TO LEFT ARROW
            robot.turn(LEFT);
            robot.push(Direction.WEST);
        }
        else if ((currentBelt.getTile().getId() == 41)) { //DOWN TO RIGHT ARROW
            robot.turn(LEFT);
            robot.push(Direction.EAST);
        }
        else if ((currentBelt.getTile().getId() == 42)) { //RIGHT TO UP ARROW
            robot.turn(LEFT);
            robot.push(Direction.NORTH);
        }
    }

    public void createTextures(){
        playerLayer.setCell(player.getPos().getPosX(), player.getPos().getPosY(), player.getCurrentState());
    }

    public Robot[] getPlayers(){
        return listOfPlayers;
    }
}


