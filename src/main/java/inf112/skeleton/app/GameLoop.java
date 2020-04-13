package inf112.skeleton.app;

import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.player.IPlayer;
import inf112.skeleton.app.player.Opponent;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.scenes.HudManager;
import java.util.PriorityQueue;
import java.util.Random;

public class GameLoop extends InputAdapter {
    HudManager hud;
    Random random;
    Board board;
    IPlayer[] players;
    int myPlayer;
    CardHandler cardHandler;
    public Thread loop;

    public GameLoop(int myPlayer){

        this.myPlayer = myPlayer;
        board = new Board(2);
        hud = new HudManager();

        players = new IPlayer[board.getRobots().length];
        players[myPlayer] = new Player(myPlayer, board.getRobots()[myPlayer], hud, board);
        for(int i = 0; i < players.length; i++){
            if(i != myPlayer){
                players[i] = new Opponent(board.getRobots()[i], i);
            }
        }

        cardHandler = new CardHandler(players, board);
        random = new Random();
        createGameLoopThread();
    }

    private void createGameLoopThread() {
        loop = new Thread(() -> {
            int r = 0;
            while(true) {
                cardHandler.dealCards();
                hud.recieveCards(players[0].getCardStorage());

                PriorityQueue<ICard>[] queues = cardHandler.getSortedCards();
                for(PriorityQueue<ICard> round : queues){
                    while(!round.isEmpty()) {
                        ICard currentCard = round.remove();
                        board.doRobotTurn(currentCard);
                    }
                }
                doGroundTileEffects();

            }
        });
    }

    private void doGroundTileEffects(){
        board.doGroundTileEffects();
    }

    public HudManager getHudManager(){
        return hud;
    }

    public Board getBoard(){
        return board;
    }

    public IPlayer[] getPlayers() {
        return players;
    }



}
