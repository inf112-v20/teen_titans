package inf112.skeleton.app.cards;

public class BaseCard implements ICard, Comparable<ICard> {
    private int priority;

    public BaseCard(int priority){
        this.priority = priority;
    }


    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(ICard card) {
        if(priority > card.getPriority()) return 1;
        else if(priority == card.getPriority()) return 0;
        else return -1;
    }
}
