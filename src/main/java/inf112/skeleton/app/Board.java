package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
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

public class Board {

    private TiledMap map;
    private HashMap<String, TiledMapTileLayer> mapLayers;
    private HashMap<String, TiledMapTileLayer.Cell> playerStates;



    public Board(){

        map = new TmxMapLoader().load("example.tmx");

        mapLayers = new HashMap<>();
        mapLayers.put("ground", (TiledMapTileLayer) map.getLayers().get("Ground"));
        mapLayers.put("hole", (TiledMapTileLayer) map.getLayers().get("Hole"));
        mapLayers.put("wall", (TiledMapTileLayer) map.getLayers().get("Wall"));
        mapLayers.put("flag", (TiledMapTileLayer) map.getLayers().get("Flags"));
        mapLayers.put("playerLayer", (TiledMapTileLayer) map.getLayers().get("Player"));

        playerStates = new HashMap<>();
        Texture player = new Texture(Gdx.files.internal("player.png"));
        TextureRegion[][] frank = new TextureRegion(new Texture("player.png")).split(300,300);
        playerStates.put("alive", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(frank[0][0])));
        playerStates.put("dead", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(frank[0][1])));
        playerStates.put("won", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(frank[0][2])));
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

    public boolean validPlayerPosition(Vector2 pos){
        return pos.x >= 0 && pos.x < camera.viewportWidth && pos.y >= 0 && pos.y < camera.viewportHeight;
    }
}


