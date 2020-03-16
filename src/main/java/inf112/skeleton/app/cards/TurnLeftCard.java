package inf112.skeleton.app.cards;

import inf112.skeleton.app.Board;
import inf112.skeleton.app.Robot;

public class TurnLeftCard implements ICard{

    private int priority;
    private Robot robot;

    public TurnLeftCard(int priority, Robot robot){
        this.priority = priority;
        this.robot = robot;
    }


    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void action() {
        robot.turn(false);
    }

    @Override
    public int compareTo(ICard card) {
        if(priority > card.getPriority()) return 1;
        else if(priority == card.getPriority()) return 0;
        else return -1;
    }
}
