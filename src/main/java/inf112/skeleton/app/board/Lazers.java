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
    TiledMapTileLayer playerlayer;
    Walls walls;
    Pos wallPos;


    public Lazers(Pos pos, Direction dir, Board board){
        x = pos.getPosX();
        y = pos.getPosY();
        wallPos = new Pos();
        this.dir = dir;
        this.board = board;
        mapLayers = board.getTiledMapTileLayers();
        playerlayer = board.getPlayerLayer();
        walls = board.getWalls();
    }

    public void shoot(){
        hit = false;
        switch (dir) {
            case NORTH:
                hit = shootUp();
                break;
            case EAST:
                hit = shootRight();
                break;
            case SOUTH:
                hit = shootDown();
                break;
            case WEST:
                hit = shootLeft();
                break;
        }

    }

    private boolean shootUp(){
        for (int i = 0; i < board.getBOARDHEIGHT() - y; i++) {
            if (!checkWall(x, y+i, Direction.NORTH)) {
                if (checkPlayer(x, y+i)) {
                    for (Robot robot : board.getListOfRobots()) {
                        if (robot.getPos().getPosX() == x && robot.getPos().getPosY() == y+i) {
                            robot.takeDamage(1);
                            return true;
                        }
                    }
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    private boolean shootRight(){
        for (int i = 0; i < board.getBOARDWIDTH() - y; i++) {
            if (checkWall(x+i, y, Direction.EAST)) {
                if (checkPlayer(x+i, y)) {
                    for (Robot robot : board.getListOfRobots()) {
                        if (robot.getPos().getPosX() == x+i && robot.getPos().getPosY() == y) {
                            robot.takeDamage(1);
                            return true;
                        }
                    }
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    private boolean shootDown() {
        for (int i = 0; i < board.getBOARDHEIGHT() - y; i++) {
            if (!checkWall(x, y-i, Direction.NORTH)) {
                if (checkPlayer(x, y-i)) {
                    for (Robot robot : board.getListOfRobots()) {
                        if (robot.getPos().getPosX() == x && robot.getPos().getPosY() == y-i) {
                            robot.takeDamage(1);
                            return true;
                        }
                    }
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    private boolean shootLeft() {
        for (int i = 0; i < board.getBOARDWIDTH() - y; i++) {
            if (!checkWall(x-i, y, Direction.NORTH)) {
                if (checkPlayer(x-i, y)) {
                    for (Robot robot : board.getListOfRobots()) {
                        if (robot.getPos().getPosX() == x-i && robot.getPos().getPosY() == y) {
                            robot.takeDamage(1);
                            return true;
                        }
                    }
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    private boolean checkPlayer(int x, int y){
        playerCheck = mapLayers.get("playerLayer").getCell(x, y);
        if (playerCheck != null) {
            System.out.println("BOOM HEADSHOT");
            return true;
        }
        return false;
    }

    private boolean checkWall(int x, int y, Direction dir) {
        //wallCheck = walls.mapLayers.get("wall").getCell(x,y);

        return walls.wall(new Pos(x, y), dir);
    }
}
