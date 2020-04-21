package inf112.skeleton.app.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.HashMap;

public class Robot implements IRobot {

    Walls wall;
    private Direction dir;
    private Pos pos;
    private Pos newPos;
    private int MAXHP = 10;
    private int currentHP;
    private HashMap<String, TiledMapTileLayer.Cell> playerStates;
    private TiledMapTileLayer.Cell currentState;

    Walls walls;

    /**
     * Constructor for the robot.
     * It sets up the starting position
     * and gives it HP
     * @param xPos The horisontal starting pos
     * @param yPos The vertical starting pos
     */
    public Robot(int xPos, int yPos, String texture, Board board) {

        pos = new Pos();
        newPos = new Pos();
        pos.setPos(xPos, yPos);
        newPos.setPos(0, 0);

        currentHP = MAXHP;
        dir = Direction.NORTH; //Kanskje endre til en parameter for ROBOT

        createPlayerTexture(texture);
        currentState = playerStates.get("alive");

        walls = new Walls(board);
    }

    public void createPlayerTexture(String location){
        playerStates = new HashMap<>();
        Texture playerTexture = new Texture(Gdx.files.internal(location));
        TextureRegion[][] robotStates = new TextureRegion(playerTexture).split(300,300);
        playerStates.put("alive", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][0])));
        playerStates.put("dead", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][0])));
        playerStates.put("won", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][0])));
    }

    public TiledMapTileLayer.Cell getCurrentState() {
        return currentState;
    }

    /**
     * returns the current position of the robot
     * @return
     */
    public Pos getPos() {
        return pos;
    }

    public Direction getDir() {
        return dir;
    }

    /**
     * Call to take damage
     * @param dmg How much damage taken
     */
    //TODO Implement sources of damage
    public void takeDamage(int dmg) {
        currentHP--;
        if (currentHP <= 0) {
            die();
        }
    }

    /**
     * Kill the robot (currently resets position and health)
     * @return returns the starting position
     */

    public void die(){
        currentState = playerStates.get("dead");
        updateModel();

    }

    public void win(){
        currentState = playerStates.get("won");
        updateModel();
    }


    /** //TODO distance må alltid vere 1, visst vi går lenger bruk rekrusjon E.L.!
     *  //TODO Går vel fint å ha det sånn? Its not a bug its a feature
     *
     * Gives a new position for the robot
     * @param distance How far the robot moves in 'direction'
     * @return returns the new position for the robot
     */
    public void move(int distance) {
        newPos.setPos(pos.copy());
        switch (dir) {
            case NORTH:
                newPos.setPosY(pos.getPosY() + distance);
                break;
            case EAST:
                newPos.setPosX(pos.getPosX() + distance);
                break;
            case SOUTH:
                newPos.setPosY(pos.getPosY() - distance);
                break;
            case WEST:
                newPos.setPosX(pos.getPosX() - distance);
                break;
        }
        if(walls.wall(pos, dir)) pos.setPos(newPos);
        if(distance > 1){
            move(distance-1);
        }
    }

    /**
     * Turns the robot in a certain direction
     * @param turnRight The robots new rotation
     */
    //TODO Give code
    public void turn(boolean turnRight) {
        if (turnRight) {
            dir = dir.next();
        }
        else {
            dir = dir.last();
        }
        updateModel();
    }

    private void updateModel(){
        switch (dir) {
            case NORTH:
                currentState.setRotation(TiledMapTileLayer.Cell.ROTATE_0);
                break;
            case EAST:
                currentState.setRotation(TiledMapTileLayer.Cell.ROTATE_270);
                break;
            case SOUTH:
                currentState.setRotation(TiledMapTileLayer.Cell.ROTATE_180);
                break;
            case WEST:
                currentState.setRotation(TiledMapTileLayer.Cell.ROTATE_90);
                break;
        }
    }

    public void push(Direction pushDir) {
        newPos.setPos(pos.copy());
        switch (pushDir) {
            case NORTH:
                newPos.setPosY(pos.getPosY() + 1);
                break;
            case EAST:
                newPos.setPosX(pos.getPosX() + 1);
                break;
            case SOUTH:
                newPos.setPosY(pos.getPosY() - 1);
                break;
            case WEST:
                newPos.setPosX(pos.getPosX() - 1);
                break;
        }
        pos.setPos(newPos);
    }
}
