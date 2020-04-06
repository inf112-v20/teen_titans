package inf112.skeleton.app.cards;

import inf112.skeleton.app.Board;
import inf112.skeleton.app.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class CardHandler {
    private ArrayList<ICard> deck;

    private PriorityQueue<ICard>[] cardsPQ;
    private Player[] players;
    private ICard[][] individuallySortedCards;
    private Random random;
    private Board board;

    public CardHandler(Player[] players, Board board){
        random = new Random();
        this.players = players;
        this.board = board;
        createDeck();
        individuallySortedCards = new ICard[players.length][5];


        cardsPQ = new PriorityQueue[5];
        for(int i = 0; i < cardsPQ.length; i++){
            cardsPQ[i] = new PriorityQueue<>();
        }
    }

    public void createDeck(){
        deck = new ArrayList<>();
        for(int i = 0; i < players.length * 5; i++){
            deck.add(new MoveForwardCard(random.nextInt(1000), players[0], board));
        }
        for(int i = 0; i < players.length * 2; i++){
            deck.add(new TurnRightCard(random.nextInt(1000), players[0]));
        }
        for(int i = 0; i < players.length * 2; i++){
            deck.add(new TurnLeftCard(random.nextInt(1000), players[0]));
        }
    }

    private void dealCards(){
        Collections.shuffle(deck);
        for(int p = 0; p < players.length; p++) {
            ArrayList<ICard> playerCards = new ArrayList<>();
            for(int i = p*players.length; i < p*players.length + 9; i++){
                playerCards.add(deck.get(i));
            }
            players[p].recieveCards(playerCards);
            ICard[] sortedCards = players[p].getSortedCards();
            individuallySortedCards[p] = sortedCards;
        }
    }


    private void addCardsToPQArray(){
        for(int i = 0; i < 5; i++){
            PriorityQueue<ICard> currentQueue = new PriorityQueue<>();
            for(ICard[] playerChoice : individuallySortedCards){
                currentQueue.add(playerChoice[i]);
            }
            cardsPQ[i] = currentQueue;
        }

    }


    public PriorityQueue<ICard>[] getSortedCards(){
        dealCards();
        addCardsToPQArray();

        return cardsPQ;
    }


}
