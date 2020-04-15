package inf112.skeleton.app.cards;

import inf112.skeleton.app.Board;
import inf112.skeleton.app.player.IPlayer;
import inf112.skeleton.app.player.Player;

import java.util.*;

public class CardHandler {

    private ArrayList<ICard> deck;
    private IPlayer[] players;
    private Random random;
    private Board board;


    public CardHandler(IPlayer[] players, Board board, boolean host){
        random = new Random();
        this.players = players;
        this.board = board;
        //createDeckFromRecipe(new int[]{95401, 85401, 45201, 27401, 37401, 47602, 47502, 87603, 22403});
        if(host) {
            createDeck();
        }
    }

    public ArrayList<ICard> getDeck(){
        return deck;
    }
    public void createDeck(){
        deck = new ArrayList<>();
        for(int i = 0; i < players.length * 5; i++){
            deck.add(new MoveForwardCard(random.nextInt(900)+100, players[0], board));
        }
        for(int i = 0; i < players.length * 2; i++){
            deck.add(new TurnRightCard(random.nextInt(900)+100, players[0]));
        }
        for(int i = 0; i < players.length * 2; i++){
            deck.add(new TurnLeftCard(random.nextInt(900)+100, players[0]));
        }
    }
    public void createDeckFromRecipe(int[] recipe){
        deck = new ArrayList<>();
        for(int newCard : recipe){
            switch(newCard % 10){
                case 1:
                    deck.add(new MoveForwardCard(newCard / 100, players[(newCard / 10) % 10], board));
                    break;
                case 2:
                    deck.add(new TurnLeftCard(newCard/100, players[(newCard / 10) % 10]));
                    break;
                case 3:
                    deck.add(new TurnRightCard(newCard/100, players[(newCard/10)%10]));
                    break;
            }
        }
    }

    public ICard[][] dealCards() {
        Collections.shuffle(deck);

        ICard[][] cardsToDeal = new ICard[players.length][9];
        for(int p = 0; p < players.length; p++) {
            for(int i = 0; i < 9; i++){
                cardsToDeal[p][i] = deck.get(9*p + i);
            }
        }
        return cardsToDeal;
    }

    private ICard[][] getPlayerCards(){
        ICard[][] individuallySortedCards = new ICard[players.length][5];
        for(int p = 0; p < players.length; p++){
            individuallySortedCards[p] = players[p].getSortedCards();
        }
        return individuallySortedCards;
    }

    private PriorityQueue<ICard>[] addCardsToPQArray(ICard[][] individuallySortedCards){
        PriorityQueue<ICard>[] cardsPQ = new PriorityQueue[5];
        for(int i = 0; i < cardsPQ.length; i++){
            cardsPQ[i] = new PriorityQueue<>();
        }
        for(int i = 0; i < 5; i++){
            PriorityQueue<ICard> currentQueue = new PriorityQueue<>();
            for(ICard[] playerChoice : individuallySortedCards){
                currentQueue.add(playerChoice[i]);
            }
            cardsPQ[i] = currentQueue;
        }
        return cardsPQ;
    }

    public PriorityQueue<ICard>[] getSortedCards(){
        return addCardsToPQArray(getPlayerCards());
    }

    public ArrayList<ICard> intsToCards(int[] hand){
        ArrayList<ICard> cards = new ArrayList<>();
        for(int num : hand){
            for(ICard card : deck){
                int typeID = num % 10;
                int playerID = (num / 10) % 10;
                int priority = num / 100;

                if(card.getPlayer().getPlayerNumber() == playerID && card.getPriority() == priority && card.getTypeID() == typeID){
                    cards.add(card);
                    break;
                }

            }
        }
        return cards;
    }


}
