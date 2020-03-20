package inf112.skeleton.app.cards;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Board;
import inf112.skeleton.app.Pos;
import inf112.skeleton.app.Robot;

public class MoveForwardCard implements ICard{

    private int priority;
    private Robot robot;
    private Board board;


    public MoveForwardCard(int priority, Robot robot, Board board){
        this.board = board;
        this.priority = priority;
        this.robot = robot;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Robot getRobot() {return robot; }

    @Override
    public void action() {

        robot.move(1);

    }

    @Override
    public int compareTo(ICard card) {
        if(priority > card.getPriority()) return 1;
        else if(priority == card.getPriority()) return 0;
        else return -1;
    }
}
