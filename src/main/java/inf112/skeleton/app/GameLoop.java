package inf112.skeleton.app;

import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.network.PacketInfo;
import inf112.skeleton.app.network.client.GameClient;
import inf112.skeleton.app.network.server.GameServer;
import inf112.skeleton.app.player.IPlayer;
import inf112.skeleton.app.player.Opponent;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.scenes.HudManager;
import inf112.skeleton.app.scenes.Renderer;

import java.util.PriorityQueue;

public class GameLoop extends InputAdapter {

    private Player myPlayer;
    private int myPlayerNumber;
    private HudManager hud;
    private IPlayer[] players;

    private Renderer parent;
    private GameClient gameClient;
    private GameServer gameServer;
    private boolean host;

    private Board board;
    private CardHandler cardHandler;

    private Thread loop;
    private Thread networking;

    public GameLoop(Renderer parent, int myPlayerNumber, boolean host){
        this.parent = parent;
        this.host = host;
        this.myPlayerNumber = myPlayerNumber;
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
        waitForDeck();
    }

    private void waitForDeck(){
        while(gameClient.getDeck() == null){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cardHandler.createDeckFromRecipe(gameClient.getDeck());

    }

    private void createGameLoopThread() {
        loop = new Thread(() -> {
            int r = 0;
            while(true) { r++;
                System.out.println("____LOOP_ITERATION_GAME_ROUND_"+r+"____ \n");
                //Host sends cards
                if(host) {
                    gameServer.dealCards();
                }

                //Client waits to recieve cards
                while(!gameClient.getActiveChooseCard()){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                //Client has recievedcards. Send cards to HUD.
                hud.recieveCards(myPlayer.getCardStorage());

                //Player selects cards
                ICard[] sortedHand = myPlayer.getSortedCards();

                //Player sends cards to server
                gameClient.sendCards(sortedHand);



                //PriorityQueue<ICard>[] queues = cardHandler.getSortedCards();
                //for(PriorityQueue<ICard> round : queues){
                //    while(!round.isEmpty()) {
                //        ICard currentCard = round.remove();
                //        board.doRobotTurn(currentCard);
                //    }
                //}
                board.doGroundTileEffects();
            }
        });
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
        myPlayer = new Player(myPlayerNumber, board.getRobots()[myPlayerNumber], hud, board);
        players[myPlayer.getPlayerNumber()] = myPlayer;
        for (int i = 0; i < players.length; i++) {
            if (i != myPlayerNumber) {
                players[i] = new Opponent(board.getRobots()[i], i);
            }
        }
    }

    public Thread getGameLoopThread(){
        return loop;
    }
    public Player getMyPlayer(){
        return myPlayer;
    }
    public HudManager getHudManager(){
        return hud;
    }
    public CardHandler getCardHandler(){
        return cardHandler;
    }
    public Board getBoard(){
        return board;
    }
    public GameServer getGameServer() {
        return gameServer;
    }
    public GameClient getGameClient(){
        return gameClient;
    }
}
