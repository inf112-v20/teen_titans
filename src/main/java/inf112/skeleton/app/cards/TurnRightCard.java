package inf112.skeleton.app.cards;

import inf112.skeleton.app.Player;
import inf112.skeleton.app.Robot;

public class TurnRightCard implements ICard{

    int priority;
    Player player;

    public TurnRightCard(int priority, Player robot){
        this.priority = priority;
        this.player = robot;
    }


    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Robot getRobot() { return robot; }

    @Override
    public void action() {
        player.getRobot().turn(true);
    }

    @Override
    public int compareTo(ICard card) {
        if(priority > card.getPriority()) return 1;
        else if(priority == card.getPriority()) return 0;
        else return -1;
    }
    @Override
    public String toString(){
        return "Right";
    }
}
