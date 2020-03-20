package inf112.skeleton.app.cards;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Board;
import inf112.skeleton.app.Player;
import inf112.skeleton.app.Pos;
import inf112.skeleton.app.Robot;

public class MoveForwardCard implements ICard{

    private int priority;
    private Player player;
    private Board board;


    public MoveForwardCard(int priority, Player player, Board board){
        this.board = board;
        this.priority = priority;
        this.player = player;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void action() {
        Pos oldPos = player.getRobot().getPos().copy();
        player.getRobot().move(1);
        board.updatePlayer(oldPos, player.getRobot());

    }

    @Override
    public int compareTo(ICard card) {
        if(priority > card.getPriority()) return 1;
        else if(priority == card.getPriority()) return 0;
        else return -1;
    }



    @Override
    public String toString(){
        return "Forward 1";
    }
}
