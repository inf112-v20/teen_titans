package inf112.skeleton.app.network.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.cards.CardHandler;
import inf112.skeleton.app.cards.ICard;
import inf112.skeleton.app.network.PacketInfo;
import inf112.skeleton.app.network.Translator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class GameServer implements Runnable {

    private int udp;
    private int tcp;
    private InetAddress address;

    private Server server;
    private ServerListener listener;
    private CardHandler cardHandler;

    private HashMap<Integer, String> playerNames = new HashMap<>();
    private HashMap<Integer, int[]> playerCards = new HashMap<>();

    public GameServer(){
        udp = 54333;
        tcp = 54555;
    }
    @Override
    public void run() {
        server = new Server();
        registerPacketInfo();
        listener = new ServerListener(server, this);
        server.addListener(listener);

        try {
            server.bind(tcp, udp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void gameStart(CardHandler cardHandler){
        this.cardHandler = cardHandler;
        sendDeckRecipe();
        //dealCards();
    }

    public void sendStartSignal(){
        PacketInfo.StartSignal startSignal = new PacketInfo.StartSignal();
        startSignal.signal = true;
        server.sendToAllTCP(startSignal);
    }
    private void sendDeckRecipe(){
        ICard[] deckAsArray = Translator.arrayListToArray(cardHandler.getDeck());
        int[] deckAsInts = Translator.cardsToInts(deckAsArray);
        PacketInfo.Deck deck = new PacketInfo.Deck();
        deck.cards = deckAsInts;
        server.sendToAllTCP(deck);
    }
    public void dealCards(){
        ICard[][] cards = cardHandler.dealCards();
        Connection[] connections = server.getConnections();
        for(int i = 0; i < connections.length; i++){
            int[] cardsAsInts = Translator.cardsToInts(cards[i]);
            PacketInfo.Cards hand = new PacketInfo.Cards();
            hand.cards = cardsAsInts;
            server.sendToTCP(connections[i].getID(), hand);
        }
    }

    public void distributeSortedCards(int[][] cards){
        PacketInfo.AllCards allCards = new PacketInfo.AllCards();
        allCards.allCards = cards;
        server.sendToAllTCP(allCards);
    }

    private void registerPacketInfo() {
        Kryo kryo = server.getKryo();
        kryo.register(PacketInfo.ReadySignal.class);
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
        kryo.register(String[].class);
    }
    public InetAddress getAddress(){
        return address;
    }
    public void addName(String name, int id){
        playerNames.put(id, name);
    }
    public HashMap getPlayerNames(){
        return playerNames;
    }
    public void dispose(){
        try {
            server.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPlayerCards(int id, int[] cards){
        playerCards.put(id, cards);
    }
    public HashMap getPlayerCards(){
        return playerCards;
    }
    public void clearPlayerCards(){
        playerCards.clear();
    }


}
