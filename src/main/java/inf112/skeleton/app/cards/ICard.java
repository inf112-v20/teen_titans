package inf112.skeleton.app.cards;

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


}
