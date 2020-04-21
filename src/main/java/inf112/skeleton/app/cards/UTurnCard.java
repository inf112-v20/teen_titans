package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.player.IPlayer;

public class UTurnCard implements ICard {
    private int priority;
    private IPlayer player;
    private Image image = new Image(new Texture(Gdx.files.internal("cards/UTurnCard.png")));
    private int typeID = 4;
    private final boolean LEFT = false;

    public UTurnCard(int priority, IPlayer player){
        this.priority = priority;
        this.player = player;
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
        player.getRobot().turn(LEFT);
        player.getRobot().turn(LEFT);
    }

    @Override
    public int compareTo(ICard card) {
        if(priority > card.getPriority()) return 1;
        else if(priority == card.getPriority()) return 0;
        else return -1;
    }

    @Override
    public String toString(){
        return "U-Turn";
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
