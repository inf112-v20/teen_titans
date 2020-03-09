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
        mapLayers.put("flag", (TiledMapTileLayer) map.getLayers().get("Flags"));
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

            if (checkValidPos(newPos)) {
                player.move(1);


            }
            else {
                System.out.println("Illegal move");
                player.die();
                updatePlayer(oldPos); //Update player
                return false; //TODO Spør bendik om det er nødvendig å returne true/false
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

    private boolean checkValidPos(Pos pos) {
        return pos.getPosX() >= 0 && pos.getPosX() < 5 && pos.getPosY() >= 0 && pos.getPosY() < 5;
    }

//    @Override
//    public boolean keyUp(int keycode){
//        Vector2 newPos = generateNewPlayerPosition(keycode, player.getPos().getPos());
//        if(validPlayerPosition(newPos)){
//            getPlayerLayer().setCell((int)position.x, (int) position.y, null);
//            position = newPos;
//            getPlayerLayer().setCell((int)position.x, (int) position.y, player.getTexture());
//            return true;
//        }
//        return false;
//    }

    /**
     * @param keycode key that was pressed
     * @return a new position if player has pressed a key indicating a move, old position otherwise.
     */
//    public Vector2 generateNewPlayerPosition(int keycode, Vector2 pos) {
//        if(keycode == Input.Keys.UP) return new Vector2().set(position.x, position.y+1);
//        else if(keycode == Input.Keys.DOWN) return new Vector2().set(position.x, position.y-1);
//        else if(keycode == Input.Keys.LEFT) return new Vector2().set(position.x-1, position.y);
//        else if(keycode == Input.Keys.RIGHT) return new Vector2().set(position.x+1, position.y);
//        else return position;
//    }

    /**
     * Checks whether suggested player position is valid.
     * @param pos new position to try.
     * @return true if given position is a valid player position, false otherwise.
     */
//    public boolean validPlayerPosition(Vector2 pos){
//        return pos.x >= 0 && pos.x < 5 && pos.y >= 0 && pos.y < 5;
//    }

    public Vector2 getPosition(){
        return position;
    }

    public void createTextures(){
        //player.createPlayerTexture("player.png"); //Flyttet til robot så den kan holde styr på seg selv
        playerLayer.setCell(player.getPos().getPosX(), player.getPos().getPosY(), player.getCurrentState());
    }

    public Robot[] getPlayers(){
        return listOfPlayers;
    }
}


