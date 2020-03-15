package inf112.skeleton.app.cards;

import inf112.skeleton.app.Robot;

public class TurnRightCard implements ICard{

    int priority;
    Robot robot;

    public TurnRightCard(int priority, Robot robot){
        this.priority = priority;
        this.robot = robot;
    }


    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void action() {
        robot.turn(true);
    }

    @Override
    public int compareTo(ICard card) {
        if(priority > card.getPriority()) return 1;
        else if(priority == card.getPriority()) return 0;
        else return -1;
    }
}
