package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

public class Board extends InputAdapter {

    private TiledMap map;
    private HashMap<String, TiledMapTileLayer> mapLayers;
    private HashMap<String, TiledMapTileLayer.Cell> playerStates;
    private Vector2 position;



    public Board(){
        position = new Vector2(0, 0);
        map = new TmxMapLoader().load("example.tmx");

        mapLayers = new HashMap<>();
        mapLayers.put("ground", (TiledMapTileLayer) map.getLayers().get("Ground"));
        mapLayers.put("hole", (TiledMapTileLayer) map.getLayers().get("Hole"));
        mapLayers.put("wall", (TiledMapTileLayer) map.getLayers().get("Wall"));
        mapLayers.put("flag", (TiledMapTileLayer) map.getLayers().get("Flags"));
        mapLayers.put("playerLayer", (TiledMapTileLayer) map.getLayers().get("Player"));

        //Graphics for player. Move to robot.
        playerStates = new HashMap<>();
        Texture player = new Texture(Gdx.files.internal("player.png"));
        TextureRegion[][] frank = new TextureRegion(new Texture("player.png")).split(300,300);
        playerStates.put("alive", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(frank[0][0])));
        playerStates.put("dead", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(frank[0][1])));
        playerStates.put("won", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(frank[0][2])));

        //place player
        mapLayers.get("playerLayer").setCell((int) position.x, (int) position.y, playerStates.get("alive"));
    }

    public TiledMap getMap(){
        return map;
    }

    public HashMap<String, TiledMapTileLayer> getMapLayers() {
        return mapLayers;
    }

    public HashMap<String, TiledMapTileLayer.Cell> getPlayerStates() {
        return playerStates;
    }

    public TiledMapTileLayer getPlayerLayer(){
        return mapLayers.get("playerLayer");
    }





    @Override
    public boolean keyUp(int keycode){
        Vector2 newPos = generateNewPlayerPosition(keycode);
        if(validPlayerPosition(newPos)){
            System.out.println("hei");
            getPlayerLayer().setCell((int)position.x, (int)position.y, null);
            position = newPos;
            getPlayerLayer().setCell((int)position.x, (int)position.y, playerStates.get("alive"));
            return true;
        }
        return false;
    }

    /**
     * @param keycode key that was pressed
     * @return a new position if player has pressed a key indicating a move, old position otherwise.
     */
    public Vector2 generateNewPlayerPosition(int keycode) {
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
    public static boolean validPlayerPosition(Vector2 pos){
        return pos.x >= 0 && pos.x < 5 && pos.y >= 0 && pos.y < 5;
    }
}


