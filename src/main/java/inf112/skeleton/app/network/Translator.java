package inf112.skeleton.app.network;

import inf112.skeleton.app.cards.ICard;

import java.util.ArrayList;

public class Translator {

    public static int[] cardsToInts(ICard[] cards){
        int[] ints = new int[cards.length];
        for(int i = 0; i < cards.length; i++){
            ints[i] = cards[i].getPriority() * 10 + cards[i].getTypeID();
        }
        return ints;
    }

    public static ICard[] arrayListToArray(ArrayList<ICard> alist){
        ICard[] list = new ICard[alist.size()];
        for(int i = 0; i < list.length; i++){
            list[i] = alist.remove(0);
        }
        return list;
    }


}
