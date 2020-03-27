package inf112.skeleton.app.cards;

import inf112.skeleton.app.Board;
import inf112.skeleton.app.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class CardHandler {
    private PriorityQueue<ICard>[] cardsPQ;
    private Player[] players;
    private ArrayList<ICard>[] individuallySortedCards;
    private Random random;
    private Board board;

    public CardHandler(Player[] players, Board board){
        random = new Random();
        this.players = players;
        this.board = board;
        individuallySortedCards = new ArrayList[players.length];


        cardsPQ = new PriorityQueue[9];
        for(int i = 0; i < cardsPQ.length; i++){
            cardsPQ[i] = new PriorityQueue<>();
        }
    }

    private void dealCards(){
        for(int p = 0; p < players.length; p++) {
            ArrayList<ICard> playerCards = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                playerCards.add(new TurnRightCard(random.nextInt(1000), players[p]));
            }
            for (int i = 0; i < 3; i++) {
                int priority = random.nextInt(1000);
                playerCards.add(new TurnLeftCard(random.nextInt(1000), players[p]));
            }
            for (int i = 0; i < 3; i++) {
                playerCards.add(new MoveForwardCard(random.nextInt(1000), players[p], board));
            }

            //TODO Send cards to players, have players select order of which cards should be played.
            Collections.shuffle(playerCards);
            individuallySortedCards[p] = playerCards;

        }
    }


    private void addCardsToPQArray(){
        for(int i = 0; i < cardsPQ.length; i++){
            PriorityQueue<ICard> currentQueue = new PriorityQueue<>();

            for(int j = 0; j < individuallySortedCards.length; j++){
                if(individuallySortedCards[j].size() > 0) {
                    currentQueue.add(individuallySortedCards[j].remove(0));
                }
            }
            cardsPQ[i] = currentQueue;
        }
    }


    public PriorityQueue<ICard>[] getSortedCards(){
        for(PriorityQueue<ICard> queue : cardsPQ){
            queue.clear();
        }
        dealCards();
        addCardsToPQArray();

        return cardsPQ;
    }


}
