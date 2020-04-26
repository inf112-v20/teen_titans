package inf112.skeleton.app.cards;

import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.player.IPlayer;

import java.util.*;

public class CardHandler {

    private ArrayList<ICard> deck;
    private IPlayer[] players;
    private Random random;
    private Board board;
    private int[][] individuallySortedCards;

    public void setIndividuallySortedCards(int[][] cards){
        individuallySortedCards = cards;
    }
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
            deck.add(new TurnLeftCard(random.nextInt(900)+100, players[0]));
            deck.add(new UTurnCard(random.nextInt(900)+100, players[0]));
            deck.add(new MoveTwoForwardCard(random.nextInt(900)+100, players[0], board));
            deck.add(new ReverseCard(random.nextInt(900)+100, players[0], board));
        }
        for(int i = 0; i < players.length*1; i++){
            deck.add(new MoveThreeForwardCard(random.nextInt(900)+100, players[0], board));
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
                case 4:
                    deck.add(new UTurnCard(newCard/100, players[(newCard/10)%10]));
                    break;
                case 5:
                    deck.add(new MoveTwoForwardCard(newCard/100, players[(newCard/10)%10], board));
                    break;
                case 6:
                    deck.add(new MoveThreeForwardCard(newCard/100, players[(newCard/10)%10], board));
                    break;
                case 7:
                    deck.add(new ReverseCard(newCard/100, players[(newCard/10)%10], board));
                    break;
            }
        }
    }
    public ICard[][] dealCards() {
        Collections.shuffle(deck);
        ICard[][] cardsToDeal = new ICard[players.length][9];
        for(int p = 0; p < players.length; p++) {
            for(int i = 0; i < 9; i++){
                ICard card = deck.get(9*p + i);
                cardsToDeal[p][i] = card;
            }
        }
        return cardsToDeal;
    }
    public int[][] handlePlayerCards(HashMap<Integer, int[]> playerCards){
        int[][] individuallySortedCards = new int[players.length][5];
        for(int[] hand : playerCards.values()){
            individuallySortedCards[(hand[0] / 10) % 10] = hand;
        }
        return individuallySortedCards;
    }

    private PriorityQueue<ICard>[] addCardsToPQArray(ICard[][] individuallySortedCards){
        PriorityQueue<ICard>[] cardsPQ = new PriorityQueue [5];
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
        ICard[][] individuallySortedCards = new ICard[players.length][5];
        for(int i = 0; i < individuallySortedCards.length; i++){
            individuallySortedCards[i] = new ICard[5];
            ArrayList<ICard> gencards = intsToCards(this.individuallySortedCards[i]);
            for(int j = 0; j < 5; j++){
                ICard card = gencards.get(j);
                individuallySortedCards[i][j] = card;
            }
        }
        return addCardsToPQArray(individuallySortedCards);
    }
    public ArrayList<ICard> intsToCards(int[] hand){
        ArrayList<ICard> cards = new ArrayList<>();
        for(int num : hand){
            for(ICard card : deck){
                int typeID = num % 10;
                int playerID = (num / 10) % 10;
                int priority = num / 100;
                if(card.getPriority() == priority && card.getTypeID() == typeID){
                    for(IPlayer player : players){
                        if(player.getPlayerNumber() == playerID){
                            card.setPlayer(player);
                            break;
                        }
                    }
                    cards.add(card);
                    break;
                }
            }
        }

        return cards;
    }


}
