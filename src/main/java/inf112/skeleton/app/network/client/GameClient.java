package inf112.skeleton.app.network.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import inf112.skeleton.app.GameLoop;
import inf112.skeleton.app.cards.ICard;
import inf112.skeleton.app.network.PacketInfo;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class GameClient {

    private GameLoop gameLoop;
    private boolean host;
    private HashMap<Integer, String> playerNames = new HashMap();
    private int playerAmount;

    public Client client = new Client();
    private ClientListener listener = new ClientListener();
    private int tcpPort = 54555;
    private int udpPort = 54333;

    private int[] deck = null;
    private boolean startSignal = false;
    private boolean activeChooseCard = false;
    private boolean activeHandleAllCards = false;


    public GameClient(boolean isHost, GameLoop gameLoop){
        this.host = isHost;
        this.gameLoop = gameLoop;
        listener.constructor(gameLoop, this);
        registerPacketInfo();
        client.addListener(listener);
        new Thread(client).start();
        if(isHost) {
            try {
                client.connect(5000, "127.0.0.1", tcpPort, udpPort);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Gdx.input.getTextInput(new Input.TextInputListener() {
                @Override
                public void input(String s) {
                    try {
                        client.connect(5000, s, tcpPort, udpPort);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void canceled() {

                }
            }, "Enter IP adress: ", "", "");
        }
        sendName();
    }

    private void sendName(){
        while(!client.isConnected()){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Gdx.input.getTextInput(new Input.TextInputListener() {
            @Override
            public void input(String s) {
                listener.sendName(s);
            }
            @Override
            public void canceled() {
                Gdx.app.exit();
            }
        }, "Enter name: ", "", "");

    }
    private void registerPacketInfo() {
        Kryo kryo = client.getKryo();
        kryo.register(PacketInfo.Cards.class);
        kryo.register(PacketInfo.Deck.class);
        kryo.register(PacketInfo.Name.class);
        kryo.register(PacketInfo.StartSignal.class);
        kryo.register(PacketInfo.NumPlayers.class);
        kryo.register(PacketInfo.AllCards.class);
        kryo.register(int[].class);
        kryo.register(String.class);
        kryo.register(boolean.class);
        kryo.register(int.class);
        kryo.register(int[][].class);
    }

    public void setDeck(int[] deck){
        this.deck = deck;
    }
    public int[] getDeck(){
        return deck;
    }

    public void setStartSignal(boolean b){
        startSignal = b;
    }
    public boolean getStartSignal() {
        return startSignal;
    }


    public void handReceived(int[] hand){
        gameLoop.getMyPlayer().recieveCards(gameLoop.getCardHandler().intsToCards(hand));
        setActiveChooseCard(true);
    }
    public void sendCards(ICard[] cards){
        listener.sendCards(cards);
    }

    public void setActiveChooseCard(boolean b){
        activeChooseCard = b;
    }
    public boolean getActiveChooseCard(){
        return activeChooseCard;
    }


    public void allCardsReceived(int[][] allCards){
        System.out.println("eigth: received " + Arrays.toString(allCards));
        gameLoop.getCardHandler().setIndividuallySortedCards(allCards);
        setActiveHandleAllCards(true);
    }

    public void setActiveHandleAllCards(boolean b){
        activeHandleAllCards = b;
    }
    public boolean getActiveHandleAllCards(){
        return activeHandleAllCards;
    }







    public Client getClient(){
        return client;
    }

    public void addName(int id, String name){
        playerNames.put(id, name);

    }
    public HashMap getPlayerNames(){
        return playerNames;
    }

    public void setPlayerAmount(int n){
        playerAmount = n;
        System.out.println("player amount changed.");
    }
    public int getPlayerAmount(){
        return playerAmount;
    }

}
