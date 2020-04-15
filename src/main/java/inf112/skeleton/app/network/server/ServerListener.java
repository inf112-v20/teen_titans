package inf112.skeleton.app.network.server;

import com.badlogic.gdx.Game;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.network.PacketInfo;

import java.util.HashMap;

public class ServerListener extends Listener {
    private GameServer parent;
    private Server server;
    private int[] players;
    private String[] names;
    private int playerNumber;

    public ServerListener(Server server, GameServer parent){
        this.parent = parent;
        this.server = server;
        players = new int[4];
        names = new String[players.length];
    }

    public void connected(Connection c){
        System.out.println("Player: " + (playerNumber + 1) + " has connected");
        players[playerNumber++] = c.getID();
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

    public void received(Connection c, Object o){
        System.out.println("Server recieved: " + o.toString());
        if(o instanceof PacketInfo.Name){
            String name = ((PacketInfo.Name) o).name;
            int ID = ((PacketInfo.Name) o).playerID;
            parent.addName(name, ID);
            server.sendToAllTCP(o);
        }
        else if(o instanceof PacketInfo.Cards){
            parent.addPlayerCards(c.getID(), ((PacketInfo.Cards) o).cards);
        }

    }

}
