package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public interface IRobot {


    void turn();

    Pos move();

    void die();

    void push();


}
