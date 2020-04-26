package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Pos;
import inf112.skeleton.app.player.IPlayer;

public class MoveThreeForwardCard implements ICard {

    private int priority;
    private IPlayer player;
    private Board board;
    private Image image = new Image(new Texture(Gdx.files.internal("robots/bulbasaur.png")));
    private int typeID = 6;

    public MoveThreeForwardCard(int priority, IPlayer player, Board board){
        this.board = board;
        this.priority = priority;
        this.player = player;
        image.setSize(150, 100);
    }

    @Override
    public void setPlayer(IPlayer player){
        this.player = player;
    }

    @Override
    public IPlayer getPlayer(){
        return player;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void action() {
        Pos oldPos = player.getRobot().getPos().copy();
        player.getRobot().move(3);
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
        return "Forward 3";
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }

}
