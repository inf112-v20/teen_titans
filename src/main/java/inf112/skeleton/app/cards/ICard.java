package inf112.skeleton.app.cards;

public interface ICard {

    /**
     * Each card holds a priority value. Cards with higher priority are played first.
     * @return card's priority value.
     */
    public int getPriority();

}
