package inf112.skeleton.app.network.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.network.PacketInfo;

import java.util.HashMap;

public class ServerListener extends Listener {
    private Server server;
    private int[] players;
    private String[] names;
    private int playerNumber;
    private HashMap<Integer, int[]> playerCards;


    public ServerListener(Server server){
        this.server = server;
        players = new int[4];
        names = new String[players.length];
        playerCards = new HashMap<>();
    }

    public void connected(Connection c){
        System.out.println("Player: " + (playerNumber + 1) + " has connected");
        players[playerNumber] = c.getID();
        playerNumber++;
        PacketInfo.NumPlayers nrOfPlayers = new PacketInfo.NumPlayers();
        nrOfPlayers.numPlayers = playerNumber;
        server.sendToAllTCP(nrOfPlayers);
    }

    public void disconnected(Connection c){
        System.out.println("Player: " + c.getID() + " has disconnected");
        playerNumber--;
        names[c.getID()] = null;
        PacketInfo.NumPlayers nrOfPlayers = new PacketInfo.NumPlayers();
        nrOfPlayers.numPlayers = playerNumber;
        server.sendToAllTCP(nrOfPlayers);

    }

    public void recieved(Connection c, Object o){
        if(o instanceof PacketInfo.Cards){
            if(playerCards.containsKey(c.getID())){
                playerCards.remove(c.getID());
            }
            playerCards.put(c.getID(), ((PacketInfo.Cards) o).cards);
            if(playerCards.size() >= playerNumber){
                server.sendToAllTCP(playerCards);
                playerCards.clear();
            }
        }


    }



}
