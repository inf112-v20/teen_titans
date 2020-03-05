package inf112.skeleton.app.cards;

import java.util.PriorityQueue;
import java.util.Random;

public class CardHandler {
    private ICard[] cardStorage;
    private PriorityQueue<ICard> cards;
    Random random;

    public CardHandler(int amountOfCards){
        cardStorage = new ICard[amountOfCards];
        cards = new PriorityQueue<>();
    }

    private void generateCards(){
        for(int i = 0; i < cardStorage.length; i++){
            cardStorage[i] = new BaseCard(random.nextInt(1000));
        }
    }




    public ICard nextCard() throws IllegalStateException{
        if(cards.size() < 1) throw new IllegalStateException("Out of cards");
        return cards.remove();
    }


    public int remainingCards(){
        return cards.size();
    }

}
