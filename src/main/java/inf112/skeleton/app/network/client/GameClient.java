package inf112.skeleton.app.network.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import inf112.skeleton.app.GameLoop;
import inf112.skeleton.app.cards.ICard;
import inf112.skeleton.app.network.PacketInfo;

import java.io.IOException;
import java.net.InetAddress;

public class GameClient {

    private GameLoop gameLoop;

    public Client client;
    private ClientListener listener;
    private int tcpPort;
    private int udpPort;

    public GameClient(InetAddress adress, GameLoop gameLoop){
        this.gameLoop = gameLoop;
        client = new Client();
        listener = new ClientListener();
        tcpPort = 54555;
        udpPort = 54333;
        listener.constructor(gameLoop, this);
        registerPacketInfo();
        client.addListener(listener);
        new Thread(client).start();
        try{
            client.connect(5000, adress, tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerPacketInfo() {
        Kryo kryo = client.getKryo();
        kryo.register(PacketInfo.Cards.class);
        kryo.register(PacketInfo.Name.class);
        kryo.register(PacketInfo.StartSignal.class);
        kryo.register(int[].class);
        kryo.register(String.class);
        kryo.register(boolean.class);
    }

    public void sendCards(ICard[] cards){
        listener.sendCards(cards);
    }

    public Client getClient(){
        return client;
    }

    public void cardsRecieved(){

    }

}
