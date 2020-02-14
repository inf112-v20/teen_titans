package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class Robot extends InputAdapter {
    private enum dir {UP, RIGHT, DOWN, LEFT}
    private dir direction;
    private int xPos;
    private int yPos;
    private int MAXHP = 10;
    private int currentHP;

    public void Robot(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        currentHP = MAXHP;
        this.direction = dir.UP; //Kanskje endre til en parameter for ROBOT
    }

    public Vector2 getPos() {
        return (new Vector2((float) xPos, (float) yPos));
    }

    public void takeDamage() {
        currentHP--;
        if (currentHP <= 0) {
            die();
        }
    }

    public void die(){
        currentHP = 0;
        //Do something dead
    }

    public void testMove(int distance) {


    }

    public void move(int distance) {
        switch (direction) {
            case UP:
                yPos += distance;
                break;
            case RIGHT:
                xPos += distance;
                break;
            case DOWN:
                yPos -= distance;
                break;
            case LEFT:
                xPos -= distance;
                break;
        }
    }

    public void turn(dir direction) {

    }



}
