package inf112.skeleton.app.player;

import inf112.skeleton.app.Robot;
import inf112.skeleton.app.cards.ICard;

import java.util.ArrayList;

public interface IPlayer {

    ICard[] getCardStorage();

    ICard[] getSortedCards();

    Robot getRobot();

    void recieveCards(ArrayList<ICard> cards);

    void selectCard(int i);

    int getPlayerNumber();

}
