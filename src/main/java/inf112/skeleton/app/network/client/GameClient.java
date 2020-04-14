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

    public Client client;
    private ClientListener listener;
    private int tcpPort;
    private int udpPort;
    private int[] deck;

    public GameClient(boolean isHost, GameLoop gameLoop){
        this.host = isHost;
        this.gameLoop = gameLoop;
        client = new Client();
        listener = new ClientListener();
        tcpPort = 54555;
        udpPort = 54333;

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

    public void setDeck(int[] deck){
        this.deck = deck;
        if(!host) {
            gameLoop.getCardHandler().createDeckFromRecipe(deck);
        }
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
        kryo.register(HashMap.class);
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

    public void sendCards(ICard[] cards){
        listener.sendCards(cards);
    }

    public Client getClient(){
        return client;
    }

    public void addName(int id, String name){
        playerNames.put(id, name);

        System.out.println("--player names--");
        System.out.println(Arrays.toString(playerNames.values().toArray()));
        System.out.println("");
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
