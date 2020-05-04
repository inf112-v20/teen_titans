package inf112.skeleton.app.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import inf112.skeleton.app.scenes.game.HudManager;

import java.util.HashMap;

public class Robot implements IRobot {

    Walls wall;
    private Direction dir;
    private Pos pos;
    private Pos newPos;
    private int MAXHP = 10;
    private int currentHP;
    private boolean[] checkpoints;
    private HashMap<String, TiledMapTileLayer.Cell> playerStates;
    private TiledMapTileLayer.Cell currentState;
    private HudManager hud;
    private Board board;
    private boolean dead;

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
        this.board = board;
        dead = false;

        currentHP = MAXHP;
        dir = Direction.NORTH; //Kanskje endre til en parameter for ROBOT
        checkpoints = new boolean[4];
        createPlayerTexture(texture);
        currentState = playerStates.get("alive");

        walls = board.getWalls();
    }
    public void recieveHud(HudManager hud){
        this.hud = hud;
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
    public void takeDamage(int dmg) {
        currentHP -= dmg;
        if (currentHP <= 0) {
            die();
            hud.updateHealth(0);
            return;
        }
        if(hud != null){
            hud.updateHealth(currentHP);
        }
    }

    public void updateCheckpoint(int checkpointID) {
        switch (checkpointID) {
            case 55:
                checkpoints[0] = true;
                break;
            case 63:
                if (checkpoints[0]) {
                    checkpoints[1] = true;
                }
                break;
            case 71:
                if (checkpoints[0] && checkpoints[1]) {
                    checkpoints[2] = true;
                }
                break;
            case 79:
                if (checkpoints[0] && checkpoints[1] && checkpoints[2]) {
                    checkpoints[3] = true;
                }
                break;
        }
    }

    public int getCurrentHp(){
        return currentHP;
    }

    public int getMaxHp(){
        return MAXHP;
    }

    /**
     * Kill the robot (currently resets position and health)
     * @return returns the starting position
     */

    public void die(){
        dead = true;
        currentState = playerStates.get("dead");
        updateModel(); //Updatemodel??
    }

    public void win(){
        currentState = playerStates.get("won");
        updateModel();
    }

    public void respawn(){
        Pos copy = new Pos();
        switch (getCurrentCheckpoint()) {
            case 1:
                copy.setPos(3, 8);
                break;
            case 2:
                copy.setPos(9, 7);
                break;
            case 3:
                copy.setPos(3, 2);
                break;
            case 4:
                copy.setPos(9, 2);
                break;
            default:
                copy.setPos(0, 0);
                break;
        }

        for (Robot robot : board.getRobots()) {
            if (robot.getPos().getPosX() == copy.getPosX()
            && robot.getPos().getPosY() == copy.getPosY()) {
                return;
            }
        }
        Pos temp = new Pos();
        temp.setPos(pos.getPosX(), pos.getPosY());
        pos.setPos(copy.getPosX(), copy.getPosY());
        dir = Direction.NORTH;
        updateModel();
        dead = false;
        System.out.println(temp.getPosY() + "hej");
        board.updatePlayer(temp, this);
        currentHP = MAXHP;
    }

    /**
     * Gives a new position for the robot
     * @param distance How far the robot moves in 'direction'
     * @return returns the new position for the robot
     */
    public void move(int distance) {
        newPos.setPos(pos.copy());
        switch (dir) {
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
        Pos temp = new Pos();
        temp.setPos(pos.copy());
        if (dead) return;
        if(walls.wall(pos, dir)){
            pushOther(newPos, dir);
            pos.setPos(newPos);
        }
        board.updatePlayer(temp, this);
        if(distance > 1){
            move(distance-1);
        }
    }

    /**
     * Turns the robot in a certain direction
     * @param turnRight The robots new rotation
     */
    public void turn(boolean turnRight) {
        if (turnRight) {
            dir = dir.next();
        }
        else {
            dir = dir.last();
        }
        updateModel();
    }

    public void updateModel(){
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

        if(walls.wall(pos, pushDir)) {
            pushOther(newPos, pushDir);
            pos.setPos(newPos);
        }
    }

    private void pushOther(Pos pos, Direction dir) {
        for (Robot robot : board.getRobots()) {
            if (robot.getPos().getPosX() == pos.getPosX() && robot.getPos().getPosY() == pos.getPosY()) {
                robot.push(dir);
                board.updatePlayer(pos, robot);
                System.out.println("!!!!!!!!!!!!!!!!!!!");
                return;
            }
        }
    }

    public boolean getLivingState() {
        return dead;
    }

    public int getCurrentCheckpoint() {
        int i = 0;
        while(i < checkpoints.length && checkpoints[i]){
            i++;
            System.out.println(i);
        }
        return i;
    }
}
