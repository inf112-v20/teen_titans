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


    public CardHandler(IPlayer[] players, Board board){
        random = new Random();
        this.players = players;
        this.board = board;
        createDeck();
    }

    public ArrayList<ICard> getDeck(){
        return deck;
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

    public void dealCards() {
        Collections.shuffle(deck);
        for(int p = 0; p < players.length; p++) {
            ArrayList<ICard> playerCards = new ArrayList<>();
            for(int i = p*9; i < p*9 + 9; i++){
                ICard card = deck.get(i);
                card.setPlayer(players[p]);
                playerCards.add(card);
            }
            players[p].recieveCards(playerCards);
        }
    }

    private ICard[][] getPlayerCards(){
        ICard[][] individuallySortedCards = new ICard[players.length][5];
        for(int p = 0; p < players.length; p++){
            individuallySortedCards[p] = players[p].getSortedCards();
            System.out.println(Arrays.toString(individuallySortedCards[p]));
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


}
