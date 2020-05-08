package inf112.skeleton.app.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.HashMap;

public class Walls {

    private final HashMap<String, TiledMapTileLayer> mapLayers;
    private int currentTileType = -1;
    private int upTileType   = -1;
    private int leftTileType = -1;
    private int rightTileType = -1;
    private int downTileType = -1;
    private TiledMapTileLayer.Cell wallTile;
    private TiledMapTileLayer.Cell lazerTile;
    private TiledMapTileLayer.Cell pusherTile;
    private TiledMapTileLayer.Cell upTile;
    private TiledMapTileLayer.Cell leftTile;
    private TiledMapTileLayer.Cell rightTile;
    private TiledMapTileLayer.Cell downTile;

    public Walls(Board board){
        mapLayers = board.getTiledMapTileLayers();
    }


    public boolean wall(Pos oldPos, Direction dir){

        wallTile = mapLayers.get("wall").getCell(oldPos.getPosX(), oldPos.getPosY());
        lazerTile = mapLayers.get("lazer").getCell(oldPos.getPosX(), oldPos.getPosY());
        pusherTile = mapLayers.get("push").getCell(oldPos.getPosX(), oldPos.getPosY());

        if (wallTile != null) currentTileType = wallTile.getTile().getId();
        else currentTileType = -1;

        if (lazerTile != null) {
            currentTileType = lazerTile.getTile().getId();
        }

        if (pusherTile != null) {
            currentTileType = pusherTile.getTile().getId();
        }

        switch (dir) {
            case NORTH:
                return checkLegalUp(oldPos);
            case SOUTH:
                return checkLegalDown(oldPos);
            case WEST:
                return checkLegalLeft(oldPos);
            case EAST:
                return checkLegalRight(oldPos);
        }
        return true;
    }

    private boolean checkLegalUp(Pos pos) {
        upTile = null;
        upTileType = -1;

        if (mapLayers.get("wall").getCell(pos.getPosX(), pos.getPosY()+1) != null){
            upTile = mapLayers.get("wall").getCell(pos.getPosX(), pos.getPosY()+1);
        }
        else if (mapLayers.get("lazer").getCell(pos.getPosX(), pos.getPosY()+1) != null) {
            upTile = mapLayers.get("lazer").getCell(pos.getPosX(), pos.getPosY()+1);
        }
        else if (mapLayers.get("push").getCell(pos.getPosX(), pos.getPosY()+1) != null) {
            upTile = mapLayers.get("push").getCell(pos.getPosX(), pos.getPosY()+1);
        }

        if (upTile != null) upTileType = upTile.getTile().getId();
        else upTileType = -1;

        return currentTileType != 24 && currentTileType != 31 && currentTileType != 9 && upTileType != 32 && upTileType != 29;
    }

    private boolean checkLegalDown(Pos pos) {
        downTile = null;
        downTileType = -1;

        if (mapLayers.get("wall").getCell(pos.getPosX(), pos.getPosY()-1) != null){
            downTile = mapLayers.get("wall").getCell(pos.getPosX(), pos.getPosY()-1);
        }
        else if (mapLayers.get("lazer").getCell(pos.getPosX(), pos.getPosY()-1) != null) {
            downTile = mapLayers.get("lazer").getCell(pos.getPosX(), pos.getPosY()-1);
        }
        else if (mapLayers.get("push").getCell(pos.getPosX(), pos.getPosY()-1) != null) {
            downTile = mapLayers.get("push").getCell(pos.getPosX(), pos.getPosY()-1);
        }

        if (downTile != null) downTileType = downTile.getTile().getId();
        else downTileType = -1;

        return downTileType != 24 && downTileType != 31 && downTileType != 9 && currentTileType != 32 && currentTileType != 29;
    }

    private boolean checkLegalLeft(Pos pos) {
        leftTile = null;
        leftTileType = -1;

        if (mapLayers.get("wall").getCell(pos.getPosX()-1, pos.getPosY()) != null){
            leftTile = mapLayers.get("wall").getCell(pos.getPosX()-1, pos.getPosY());
        }
        else if (mapLayers.get("lazer").getCell(pos.getPosX()-1, pos.getPosY()) != null) {
            leftTile = mapLayers.get("lazer").getCell(pos.getPosX()-1, pos.getPosY());
        }
        else if (mapLayers.get("push").getCell(pos.getPosX()-1, pos.getPosY()) != null) {
            leftTile = mapLayers.get("push").getCell(pos.getPosX()-1, pos.getPosY());
        }

        if (leftTile != null) leftTileType = leftTile.getTile().getId();
        else leftTileType = -1;

        return currentTileType != 32 && currentTileType != 30 && currentTileType != 24 && currentTileType != 38 && leftTileType != 2;
    }

    private boolean checkLegalRight(Pos pos) {
        rightTile = null;
        rightTileType = -1;

        if (mapLayers.get("wall").getCell(pos.getPosX()+1, pos.getPosY()) != null){
            rightTile = mapLayers.get("wall").getCell(pos.getPosX()+1, pos.getPosY());
        }
        else if (mapLayers.get("lazer").getCell(pos.getPosX()+1, pos.getPosY()) != null) {
            rightTile = mapLayers.get("lazer").getCell(pos.getPosX()+1, pos.getPosY());
        }
        else if (mapLayers.get("push").getCell(pos.getPosX()+1, pos.getPosY()) != null) {
            rightTile = mapLayers.get("push").getCell(pos.getPosX()+1, pos.getPosY());
        }

        if (rightTile != null) rightTileType = rightTile.getTile().getId();
        else rightTileType = -1;

        return rightTileType != 32 && rightTileType != 30 && rightTileType != 24 && rightTileType != 38 && currentTileType != 2;
    }
}
