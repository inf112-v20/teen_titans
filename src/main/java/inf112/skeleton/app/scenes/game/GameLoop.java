package inf112.skeleton.app.scenes.game;

import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Robot;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.network.client.GameClient;
import inf112.skeleton.app.network.server.GameServer;
import inf112.skeleton.app.player.IPlayer;
import inf112.skeleton.app.player.Opponent;
import inf112.skeleton.app.player.Player;

import java.util.Arrays;
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
    private int totalRound;
    private CardHandler cardHandler;

    private Thread loop;

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
        board.createRobots(playerAmount, gameClient.getModels());
        createPlayers();
        cardHandler = new CardHandler(players, board, host);
        hud.createImages(gameClient.getModels());
        if(host){
            gameServer.gameStart(cardHandler);
        }
        waitForDeck();
    }

    private void waitForDeck(){
        while(gameClient.getDeck() == null){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cardHandler.createDeckFromRecipe(gameClient.getDeck());
    }

    private void createGameLoopThread() {
        loop = new Thread(() -> {
            int r = 0;
            while(true) {
                r++;
                System.out.println("____LOOP_ITERATION_GAME_ROUND_"+r+"____ \n");

                /** Creates display to show winner of game **/
                if(gameClient.gameOver()){
                    hud.setWinner(gameClient.getGameWinner());
                    break;
                }

                /**  Host sends cards  check**/
                if(host && !gameClient.gameOver()) {
                    gameServer.dealCards();
                }
                /**  Client waits to recieve cards  check**/
                while(!gameClient.getActiveChooseCard()){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /**  Client has recievedcards. Send cards to HUD.  check**/
                /**  Player selects cards  check**/
                ICard[] sortedHand = myPlayer.getSortedCards();
                /**  Player sends cards to server check**/
                gameClient.sendCards(sortedHand);
                if(host){
                    /** Host waits for all players to send cards... **/
                    while(gameServer.getPlayerCards().size() < players.length){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    /**  ...and then sends cards back to players, clears player cards from storage  **/
                    int[][] allPlayerCards = cardHandler.handlePlayerCards(gameServer.getPlayerCards());
                    gameServer.distributeSortedCards(allPlayerCards);
                    gameServer.clearPlayerCards();
                }
                /**  Client waits to recieve all player cards from server  **/
                while(!gameClient.getActiveHandleAllCards()){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /**  Cards recieved, perform card tasks.  **/
                PriorityQueue<ICard>[] queues = cardHandler.getSortedCards();

                for(PriorityQueue<ICard> round : queues){
                    while(!round.isEmpty()) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ICard currentCard = round.remove();
                        board.doRobotTurn(currentCard);

                    }

                    /**  Do board tile effects  **/
                    board.doGroundTileEffects(++totalRound);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        //Do nothing
                    }
                }
                /** Between rounds **/
                if (!board.getDeadRobots().isEmpty()) {
                    board.getDeadRobots().get(0).respawn();
                    board.getDeadRobots().remove(0);
                }
                resetAfterRound();
            }
        });
        loop.setName("Main Game Loop Thread");
    }
    private void resetAfterRound(){
        if(myPlayer.checkWinCondition()){
            gameClient.sendGameOverSignal();
        }
        gameClient.setActiveChooseCard(false);
        gameClient.setActiveHandleAllCards(false);
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
