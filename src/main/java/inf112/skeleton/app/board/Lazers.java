package inf112.skeleton.app.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.HashMap;

class Lazers {

    private final int x;
    private final int y;
    private final Direction dir;
    private final Board board;
    private TiledMapTileLayer.Cell playerCheck;
    private final HashMap<String, TiledMapTileLayer> mapLayers;
    private final TiledMapTileLayer playerlayer;
    private final Walls walls;
    private final Pos wallPos;


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
        switch (dir) {
            case NORTH:
                shootUp();
                break;
            case EAST:
                shootRight();
                break;
            case SOUTH:
                shootDown();
                break;
            case WEST:
                shootLeft();
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
                if (checkPlayer(x+i, y)) {
                    for (Robot robot : board.getListOfRobots()) {
                        if (robot.getPos().getPosX() == x+i && robot.getPos().getPosY() == y) {
                            robot.takeDamage(1);
                            return true;
                        }
                    }
                }
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
        return playerCheck != null;
    }

    private boolean checkWall(int x, int y, Direction dir) {
        //wallCheck = walls.mapLayers.get("wall").getCell(x,y);

        return walls.wall(new Pos(x, y), dir);
    }
}
