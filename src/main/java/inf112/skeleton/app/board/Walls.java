package inf112.skeleton.app.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.HashMap;

public class Walls {

    HashMap<String, TiledMapTileLayer> mapLayers;
    int currentTileType = -1;
    int upTileType   = -1;
    int leftTileType = -1;
    int rightTileType = -1;
    int downTileType = -1;
    TiledMapTileLayer.Cell wallTile;
    TiledMapTileLayer.Cell lazerTile;
    TiledMapTileLayer.Cell pusherTile;
    TiledMapTileLayer.Cell upTile;
    TiledMapTileLayer.Cell leftTile;
    TiledMapTileLayer.Cell rightTile;
    TiledMapTileLayer.Cell downTile;

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
        System.out.println("upTileType: " + upTileType);
        System.out.println("currentTileType: " + currentTileType);

        //Her er bare veggene vi bruker no, kan utvides med flere
        if (currentTileType == 24 || currentTileType == 31 || currentTileType == 9 || upTileType == 32 || upTileType == 29) {
            System.out.println("kræsj");
            return false;
        }

        return true;
    }

    private boolean checkLegalDown(Pos pos) {

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

        //Her er bare veggene vi bruker no, kan utvides med flere
        if (downTileType == 24 || downTileType == 31 || downTileType == 9 || currentTileType == 32 || currentTileType == 29) {
            return false;
        }

        return true;
    }

    private boolean checkLegalLeft(Pos pos) {

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

        //Her er bare veggene vi bruker no, kan utvides med flere
        if (currentTileType == 32 || currentTileType == 30 || currentTileType == 24 || currentTileType == 38 || leftTileType == 2) {
            return false;
        }

        return true;
    }

    private boolean checkLegalRight(Pos pos) {
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

        //Her er bare veggene vi bruker no, kan utvides med flere
        if (rightTileType == 32 || rightTileType == 30 || rightTileType == 24 || rightTileType == 38 || currentTileType == 2) {
            return false;
        }

        return true;
    }
}
