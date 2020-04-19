package inf112.skeleton.app.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.HashMap;

public class Lazers {

    int x;
    int y;
    Direction dir;
    Board board;
    boolean hit;
    TiledMapTileLayer.Cell wallCheck;
    TiledMapTileLayer.Cell playerCheck;
    HashMap<String, TiledMapTileLayer> mapLayers;


    public Lazers(Pos pos, Direction dir, Board board){
        x = pos.getPosX();
        y = pos.getPosY();
        this.dir = dir;
        this.board = board;
        mapLayers = board.getTiledMapTileLayers();
    }

    public void shoot(){
        hit = false;
        switch (dir) {
            case NORTH:
                hit = shootUp();
                break;
            case EAST:
                //hit = shootRight();
                break;
            case SOUTH:
                //hit = shootDown();
                break;
            case WEST:
                //hit = shootLeft();
                break;
        }
    }

    private boolean shootUp(){
        for (int i = 0; i < board.getBOARDHEIGHT() - y; i++) {
        }
        return true;
    }

    private boolean checkPlayer(int x, int y){
        playerCheck = mapLayers.get("playerLayer").getCell(x, y);
        if (playerCheck != null) {
            System.out.println("BOOM HEADSHOT");
            return true;
        }
        return false;
    }

    private boolean checkWall(int x, int y) {
        return true;
        //TODO Sjekk om den treffer en vegg, stopp isåfall
        //TODO Pass på om veggen er horisontal/vertikal
    }
}
