package inf112.skeleton.app.network;

import inf112.skeleton.app.cards.ICard;

public class Translator {

    public static int[] cardsToInts(ICard[] cards){
        int[] ints = new int[cards.length];
        for(int i = 0; i < cards.length; i++){
            ints[i] = cards[i].getPriority() * 10 + cards[i].getTypeID();
        }
        return ints;
    }


}
