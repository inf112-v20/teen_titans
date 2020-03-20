package inf112.skeleton.app.cards;

import inf112.skeleton.app.Board;
import inf112.skeleton.app.Player;
import inf112.skeleton.app.Robot;

public class TurnLeftCard implements ICard{

    private int priority;
    private Player player;

    public TurnLeftCard(int priority, Player player){
        this.priority = priority;
        this.player = player;
    }


    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void action() {
        player.getRobot().turn(false);
    }

    @Override
    public int compareTo(ICard card) {
        if(priority > card.getPriority()) return 1;
        else if(priority == card.getPriority()) return 0;
        else return -1;
    }


    @Override
    public String toString(){
        return "Left";
    }
}
