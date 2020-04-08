package inf112.skeleton.app;

import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.scenes.HudManager;
import java.util.PriorityQueue;
import java.util.Random;

public class GameLoop extends InputAdapter {
    HudManager hud;
    Random random;
    Board board;
    Player[] players;
    Robot[] robots;
    CardHandler cardHandler;
    public Thread loop;

    public GameLoop(HudManager hud){

        board = new Board(1);
        this.hud = hud;

        players = new Player[] {
                new Player(board.getRobots()[0], hud, board),
        };

        cardHandler = new CardHandler(players, board);
        random = new Random();
        createGameLoopThread();
    }

    private void createGameLoopThread() {
        loop = new Thread(() -> {
            int r = 0;
            while(true) {
                PriorityQueue<ICard>[] queues = cardHandler.getSortedCards();
                hud.recieveCards((ICard[]) players[0].getCardStorage());


                for(PriorityQueue<ICard> round : queues){
                    if(round.size() < 0) {
                        ICard currentCard = round.remove();
                    }
                    //doRobotTurn(currentCard);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }

                doGroundTileEffects();


            }
        });
    }

    private void doGroundTileEffects(){
        board.doGroundTileEffects();
    }



    public Board getBoard(){
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }



}
