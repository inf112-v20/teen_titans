package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class Robot {

    private enum dir {UP, RIGHT, DOWN, LEFT} //The current direction the robot is facing
    private dir direction;
    private Vector2 pos;
    private int MAXHP = 10;
    private int currentHP;
    private HashMap<String, TiledMapTileLayer.Cell> playerStates;

    /**
     * Constructor for the robot.
     * It sets up the starting position
     * and gives it HP
     * @param xPos The horisontal starting pos
     * @param yPos The vertical starting pos
     */
    public Robot(int xPos, int yPos) {
        pos = new Vector2(xPos, yPos);
        currentHP = MAXHP;
        this.direction = dir.UP; //Kanskje endre til en parameter for ROBOT
    }

    public void createPlayerTexture(String location){
        playerStates = new HashMap<>();
        Texture playerTexture = new Texture(Gdx.files.internal(location));
        TextureRegion[][] robotStates = new TextureRegion(playerTexture).split(300,300);
        playerStates.put("alive", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][0])));
        playerStates.put("dead", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][1])));
        playerStates.put("won", new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotStates[0][2])));
    }

    /**
     * returns the current position of the robot
     * @return
     */
    public Vector2 getPos() {
        return pos;
    }

    public int getMAXHP(){
        return MAXHP;
    }

    public int getCurrentHP(){
        return currentHP;
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
    //TODO Change robot state to dead and don't reset position
    public Vector2 die(){
        currentHP = 10;
        return (new Vector2(0, 0)); // test death
    }


    /**
     * Gives a new position for the robot
     * @param distance How far the robot moves in 'direction'
     * @return returns the new position for the robot
     */
    public Vector2 move(int distance) {
        switch (direction) {
            case UP:
                return (new Vector2(pos.x, pos.y + distance));
            case RIGHT:
                return (new Vector2(pos.x + distance, pos.y));
            case DOWN:
                return (new Vector2(pos.x, pos.y - distance));
            case LEFT:
                return (new Vector2(pos.x - distance, pos.y));
        }
        return pos;
    }

    /**
     * Turns the robot in a certain direction
     * @param direction The robots new rotation
     */
    //TODO Give code
    public void turn(dir direction) {

    }

    public TiledMapTileLayer.Cell getTexture(){
        return ((currentHP < 0) ? playerStates.get("dead") : playerStates.get("alive"));
    }



}
