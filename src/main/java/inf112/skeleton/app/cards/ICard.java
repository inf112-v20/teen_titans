package inf112.skeleton.app.cards;

import inf112.skeleton.app.Robot;

public interface ICard extends Comparable<ICard> {

    /**
     * Each card holds a priority value. Cards with higher priority are played first.
     * @return card's priority value.
     */
    int getPriority();

    Robot getRobot();


    /**
     * Performs action.
     */
    void action();


    /**
     * @return String representation of card.
     */
    @Override
    String toString();


}
