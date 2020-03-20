package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import inf112.skeleton.app.cards.CardHandler;
import inf112.skeleton.app.cards.ICard;

import java.util.PriorityQueue;
import java.util.Random;

public class GameLoop{
    Random random;
    Board board;
    Player[] players;
    Robot[] robots;
    CardHandler cardHandler;
    public Thread loop;

    public GameLoop(){
        players = new Player[]{
                new Player(3, 3)
        };
        robots = new Robot[players.length];
        for(int i = 0; i < players.length; i++){
            robots[i] = players[i].getRobot();
        }

        board = new Board(robots);
        cardHandler = new CardHandler(players, board);
        random = new Random();
        createGameLoopThread();
    }

    private void createGameLoopThread() {
        loop = new Thread(() -> {
            int r = 0;
            while(true) {
                r++;
                for(PriorityQueue<ICard> round : cardHandler.getSortedCards()){

                    ICard currentCard = round.remove();
                    currentCard.action();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }

            }
        });
    }

    public Board getBoard(){
        return board;
    }



}
