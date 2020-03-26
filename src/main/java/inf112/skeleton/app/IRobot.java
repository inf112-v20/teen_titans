package inf112.skeleton.app;


public interface IRobot {


    void turn(boolean turnRight);

    void move(int distance);

    void die();

    void push(Direction pushDir);

    Direction getDir();


}
