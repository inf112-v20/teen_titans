package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.HashMap;

public class Robot implements IRobot {

    private Direction dir;
    private Pos pos;
    private int MAXHP = 10;
    private int currentHP;
    private HashMap<String, TiledMapTileLayer.Cell> playerStates;
    private TiledMapTileLayer.Cell currentState;

    /**
     * Constructor for the robot.
     * It sets up the starting position
     * and gives it HP
     * @param xPos The horisontal starting pos
     * @param yPos The vertical starting pos
     */
    public Robot(int xPos, int yPos) {

        pos = new Pos();
        pos.setPos(xPos, yPos);

        currentHP = MAXHP;
        dir = Direction.NORTH; //Kanskje endre til en parameter for ROBOT

        createPlayerTexture("player.png");
        currentState = playerStates.get("alive");
    }

    public void createPlayerTexture(String location){
        playerStates = new HashMap<>();
        Texture playerTexture = new Texture(Gdx.files.internal(location));
        TextureRegion[][] robotStates = new TextureRegion(playerTexture).split(300,300);
        playerStates.put("alive", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][0])));
        playerStates.put("dead", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][1])));
        playerStates.put("won", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][2])));
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


    /**
     * Gives a new position for the robot
     * @param distance How far the robot moves in 'direction'
     * @return returns the new position for the robot
     */
    public void move(int distance) {
        switch (dir) {
            case NORTH:
                pos.setPosY(pos.getPosY() + distance);
                break;
            case EAST:
                pos.setPosX(pos.getPosX() + distance);
                break;
            case SOUTH:
                pos.setPosY(pos.getPosY() - distance);
                break;
            case WEST:
                pos.setPosX(pos.getPosX() - distance);
                break;
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
        switch (pushDir) {
            case NORTH:
                pos.setPosY(pos.getPosY() + 1);
                break;
            case EAST:
                pos.setPosX(pos.getPosX() + 1);
                break;
            case SOUTH:
                pos.setPosY(pos.getPosY() - 1);
                break;
            case WEST:
                pos.setPosX(pos.getPosX() - 1);
                break;
        }
    }
}
