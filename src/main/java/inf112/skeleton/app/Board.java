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
    private Vector2 position;
    private Robot player;
    private Robot[] listOfPlayers = new Robot[5];

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

    }


    public TiledMap getMap(){
        return map;
    }

    public TiledMapTileLayer getPlayerLayer(){
        return mapLayers.get("playerLayer");
    }

    @Override
    public boolean keyUp(int keycode){
        Vector2 newPos = generateNewPlayerPosition(keycode, player.getPos());
        if(validPlayerPosition(newPos)){
            getPlayerLayer().setCell((int)position.x, (int) position.y, null);
            position = newPos;
            getPlayerLayer().setCell((int)position.x, (int) position.y, player.getTexture());
            return true;
        }
        return false;
    }

    /**
     * @param keycode key that was pressed
     * @return a new position if player has pressed a key indicating a move, old position otherwise.
     */
    public Vector2 generateNewPlayerPosition(int keycode, Vector2 pos) {
        if(keycode == Input.Keys.UP) return new Vector2().set(position.x, position.y+1);
        else if(keycode == Input.Keys.DOWN) return new Vector2().set(position.x, position.y-1);
        else if(keycode == Input.Keys.LEFT) return new Vector2().set(position.x-1, position.y);
        else if(keycode == Input.Keys.RIGHT) return new Vector2().set(position.x+1, position.y);
        else return position;
    }

    /**
     * Checks whether suggested player position is valid.
     * @param pos new position to try.
     * @return true if given position is a valid player position, false otherwise.
     */
    public boolean validPlayerPosition(Vector2 pos){
        return pos.x >= 0 && pos.x < 5 && pos.y >= 0 && pos.y < 5;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void createTextures(){
        player.createPlayerTexture("player.png");
        mapLayers.get("playerLayer").setCell((int) position.x, (int) position.y, player.getTexture());
    }



    public Robot[] getPlayers(){
        return listOfPlayers;
    }
}


