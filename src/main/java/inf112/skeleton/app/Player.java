package inf112.skeleton.app;

import inf112.skeleton.app.cards.ICard;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private Robot robot;
    private ArrayList<ICard> cardStorage;
    private ArrayList<ICard> sortedCards;
    Scanner scanner = new Scanner(System.in);

    public Player(Robot robot){
        this.robot = robot;
    }


    public void recieveCards(ArrayList<ICard> cards){
        cardStorage = cards;
        sortCards();
    }

    private void sortCards(){
        while(cardStorage.size() > 0){
            for(ICard card : cardStorage){
                System.out.println(card.toString());
            }
            System.out.println("Next card to play: (0 - " + (cardStorage.size() - 1) + ")");
            int cardToPlay = scanner.nextInt();
            sortedCards.add(cardStorage.remove(cardToPlay));
        }
    }


    public ArrayList<ICard> getSortedCards(){
        return sortedCards;
    }


    public Robot getRobot(){
        return robot;
    }
}
