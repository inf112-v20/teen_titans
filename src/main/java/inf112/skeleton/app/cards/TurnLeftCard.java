package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.Player;
import inf112.skeleton.app.Robot;

public class TurnLeftCard implements ICard{

    private int priority;
    private Player player;
    private Image image = new Image(new Texture(Gdx.files.internal("TurnLeftCard.png")));

    public TurnLeftCard(int priority, Player player){
        this.priority = priority;
        this.player = player;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Robot getRobot() { return player.getRobot(); }

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

    @Override
    public Image getImage() {
        return image;
    }

}
