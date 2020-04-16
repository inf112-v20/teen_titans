package inf112.skeleton.app.cards;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.board.Robot;
import inf112.skeleton.app.player.IPlayer;

public interface ICard extends Comparable<ICard> {

    /**
     * Each card holds a priority value. Cards with higher priority are played first.
     * @return card's priority value.
     */
    int getPriority();


    /**
     * Performs action.
     */
    void action();


    /**
     * @return String representation of card.
     */
    @Override
    String toString();


    /**
     * @return title of current card's image representation
     */
    Image getImage();

    /**
     * @return card's type ID, used in Translator class
     */
    int getTypeID();


    /**
     * Sets card's player value
     */
    void setPlayer(IPlayer player);

    /**
     *
     * @return Given card's player
     */
    IPlayer getPlayer();


}
