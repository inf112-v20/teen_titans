package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.network.client.GameClient;
import inf112.skeleton.app.network.server.GameServer;
import inf112.skeleton.app.player.IPlayer;
import inf112.skeleton.app.player.Opponent;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.scenes.HudManager;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.PriorityQueue;
import java.util.Random;

public class GameLoop extends InputAdapter {

    private int myPlayer;
    private HudManager hud;
    private IPlayer[] players;
    private GameClient gameClient;
    private GameServer gameServer;
    private boolean host;

    private Board board;
    private CardHandler cardHandler;

    private Thread loop;
    private Thread networking;

    public GameLoop(int myPlayer, boolean host){
        this.host = host;
        this.myPlayer = myPlayer;
        board = new Board();
        hud = new HudManager();
        createNetworking();
        createGameLoopThread();
    }

    public void create(int playerAmount){
        board.createRobots(playerAmount);
        createPlayers();
        cardHandler = new CardHandler(players, board, host);
        if(host){
            gameServer.gameStart(cardHandler);
        }
        else{
            waitForDeck();
        }
    }

    private void waitForDeck(){
        while(cardHandler.getDeck() == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createGameLoopThread() {
        loop = new Thread(() -> {
            int r = 0;
            while(true) {
                cardHandler.dealCards();

                hud.recieveCards(players[myPlayer].getCardStorage());
                PriorityQueue<ICard>[] queues = cardHandler.getSortedCards();
                for(PriorityQueue<ICard> round : queues){
                    while(!round.isEmpty()) {
                        ICard currentCard = round.remove();
                        board.doRobotTurn(currentCard);
                    }
                }
                board.doGroundTileEffects();
            }
        });
    }

    public Thread getGameLoopThread(){
        return loop;
    }

    public void createNetworkingThread(){
        networking = new Thread(() ->{
            if(host){
                gameServer = new GameServer();
                gameServer.run();
                gameClient = new GameClient(host, this);
            }

            else{
                gameClient = new GameClient(host,this);
                //do not create server.
            }
        });
        networking.start();
    }

    private void createNetworking(){
        if(host){
            gameServer = new GameServer();
            gameServer.run();
            gameClient = new GameClient(host, this);
        }
        else{
            gameClient = new GameClient(host, this);
        }
    }


    private void createPlayers() {
        players = new IPlayer[board.getRobots().length];
        players[myPlayer] = new Player(myPlayer, board.getRobots()[myPlayer], hud, board);
        for (int i = 0; i < players.length; i++) {
            if (i != myPlayer) {
                players[i] = new Opponent(board.getRobots()[i], i);
            }
        }
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

    public GameServer getGameServer() {
        return gameServer;
    }
    public GameClient getGameClient(){
        return gameClient;
    }

    public CardHandler getCardHandler() {
        return cardHandler;
    }
}
