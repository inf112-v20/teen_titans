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

        robots = new Robot[]{
            new Robot(3, 3), //Player 0
        };
        players = new Player[] {
                new Player(robots[0]),
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
                    doRobotTurn(currentCard);

                    try {
                        Thread.sleep(200);
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

    private void doRobotTurn(ICard currentCard) {
        Robot currentRobot = currentCard.getRobot();
        Pos oldPos = currentRobot.getPos().copy();
        currentCard.action();
        board.updatePlayer(oldPos, currentRobot);
    }


    public Board getBoard(){
        return board;
    }



}
