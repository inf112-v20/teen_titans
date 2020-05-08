package inf112.skeleton.app.player;

import inf112.skeleton.app.board.Robot;
import inf112.skeleton.app.cards.ICard;

import java.util.ArrayList;
import java.util.Random;

public class Opponent implements IPlayer{

    private final int playerNumber;
    private final Robot robot;
    private ArrayList<ICard> cardStorage = new ArrayList<>();
    private final ArrayList<ICard> sortedCards = new ArrayList<>();
    private final Random random;

    public Opponent(Robot robot, int playerNumber){
        this.playerNumber = playerNumber;
        this.robot = robot;
        random = new Random();
    }

    @Override
    public int getPlayerNumber(){
        return playerNumber;
    }


    @Override
    public ICard[] getCardStorage(){
        ICard[] cardStorage = new ICard[9];
        for(int i = 0; i < this.cardStorage.size(); i++){
            cardStorage[i] = this.cardStorage.get(i);
        }
        return cardStorage;
    }


    @Override
    public ICard[] getSortedCards(){
        while(sortedCards.size() < 5) {
            selectCard(random.nextInt(cardStorage.size()));
        }

        ICard[]  sortedCardsArray = new ICard[5];
        for(int i = 0; i < sortedCardsArray.length; i++){
            sortedCardsArray[i] = sortedCards.get(i);
        }
        return sortedCardsArray;
    }

    @Override
    public Robot getRobot() {
        return robot;
    }

    @Override
    public void recieveCards(ArrayList<ICard> cards){
        sortedCards.clear();
        cardStorage = cards;
    }

    @Override
    public void selectCard(int i){
        if(sortedCards.contains(cardStorage.get(i))){
            sortedCards.remove(cardStorage.get(i));
        }
        else {
            if(sortedCards.size() < 5) {
                sortedCards.add(cardStorage.get(i));
            }
        }
    }
}
