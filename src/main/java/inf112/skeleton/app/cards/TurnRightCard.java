package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.player.IPlayer;

public class TurnRightCard implements ICard{

    private final int priority;
    private IPlayer player;
    private final Image image = new Image(new Texture(Gdx.files.internal("cards/TurnRightCard.png")));
    private final int typeID = 3;
    private final boolean RIGHT = true;

    public TurnRightCard(int priority, IPlayer robot){
        this.priority = priority;
        this.player = robot;
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
        player.getRobot().turn(RIGHT);
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

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
