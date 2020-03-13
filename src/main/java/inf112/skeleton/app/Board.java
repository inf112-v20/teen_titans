package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;

import java.util.HashMap;

public class Board extends InputAdapter {

    private TiledMap map;
    private HashMap<String, TiledMapTileLayer> mapLayers;
    private TiledMapTileLayer playerLayer;
    private Vector2 position;
    private Robot player;
    private Robot[] listOfPlayers = new Robot[5];
    private final int BOARDWIDTH = 5;
    private final int BOARDHEIGHT = 5;
    private final int LEGALMOVE = 1; //For normal board movement
    private final int ILLEGALMOVE = 0; //For walls/obstacles
    private final int SUICIDALMOVE = -1; //For holes/fall off map
    private final boolean RIGHT = true;
    private final boolean LEFT = false;

    public Board(){

        position = new Vector2(0, 0);
        player = new Robot(0, 0);
        listOfPlayers[0] = player; //TEMP FOR Å LEGGE TIL SPILLERE (TESTING)


        map = new TmxMapLoader().load("example.tmx");

        mapLayers = new HashMap<>();
        mapLayers.put("ground", (TiledMapTileLayer) map.getLayers().get("Ground"));
        mapLayers.put("hole", (TiledMapTileLayer) map.getLayers().get("Hole"));
        mapLayers.put("wall", (TiledMapTileLayer) map.getLayers().get("Wall"));
        mapLayers.put("flag", (TiledMapTileLayer) map.getLayers().get("Flag"));
        mapLayers.put("playerLayer", (TiledMapTileLayer) map.getLayers().get("Player"));
        playerLayer = mapLayers.get("playerLayer");

    }


    public TiledMap getMap(){
        return map;
    }

    public TiledMapTileLayer getPlayerLayer(){
        return mapLayers.get("playerLayer");
    }

    @Override
    public boolean keyUp(int keyCode){
        Pos oldPos = player.getPos().copy();
        Pos newPos = new Pos();
        newPos.setPos(oldPos.getPosX(), oldPos.getPosY());
        if(keyCode == Input.Keys.UP) {

            switch(player.getDir()){
                case NORTH:
                    newPos.setPos(newPos.getPosX(), newPos.getPosY() + 1);
                    break;
                case EAST:
                    newPos.setPos(newPos.getPosX() + 1, newPos.getPosY());
                    break;
                case WEST:
                    newPos.setPos(newPos.getPosX() - 1, newPos.getPosY());
                    break;
                case SOUTH:
                    newPos.setPos(newPos.getPosX(), newPos.getPosY() - 1);
                    break;
            }

            if (checkPos(newPos) == LEGALMOVE) {
                player.move(1);

            }
            else if (checkPos(newPos) == SUICIDALMOVE) {
                System.out.println("You died");
                player.move(1);
                player.die();
                updatePlayer(oldPos); //Update player
                
            }
        }
        else if (keyCode == Input.Keys.LEFT) {
            player.turn(LEFT);
        }
        else if (keyCode == Input.Keys.RIGHT) {
            player.turn(RIGHT);
        }


        updatePlayer(oldPos); //Update player

        return true;

    }

    private void updatePlayer(Pos oldPos){
        getPlayerLayer().setCell(oldPos.getPosX(), oldPos.getPosY(), null);
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

    public Vector2 getPosition(){
        return position;
    }

    public void createTextures(){
        playerLayer.setCell(player.getPos().getPosX(), player.getPos().getPosY(), player.getCurrentState());
    }

    public Robot[] getPlayers(){
        return listOfPlayers;
    }
}


