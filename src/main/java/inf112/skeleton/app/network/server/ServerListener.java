package inf112.skeleton.app.network.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.network.PacketInfo;


public class ServerListener extends Listener {
    private GameServer parent;
    private Server server;
    private int[] players;
    private String[] names;
    private String[] models;
    private boolean[] readies;
    private int playerNumber;

    public ServerListener(Server server, GameServer parent){
        this.parent = parent;
        this.server = server;
        players = new int[4];
        names = new String[players.length];
        models = new String[players.length];
        readies = new boolean[players.length];

        //PER NÅ: alle 4 spillere må joine for å starte spillet. Lag system for å starte spill med færre spillere
        for(int i = 0; i < readies.length; i++){
            readies[i] = false;
        }
        if(!readies[0]) System.out.println("IN SERVERLISTENER");
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
        if(!(o instanceof FrameworkMessage.KeepAlive)) {
            System.out.println("Server recieved: " + o.toString());
        }
        if(o instanceof PacketInfo.Name){
            String name = ((PacketInfo.Name) o).name;
            for(int i = 0; i < players.length; i++){
                if(names[i] != null){
                    PacketInfo.Name namePacket = new PacketInfo.Name();
                    namePacket.name = names[i];
                    namePacket.playerID = players[i];
                    server.sendToTCP(c.getID(), namePacket);
                }
                if(c.getID() == players[i]){
                    names[i] = name;
                    break;
                }
            }
            server.sendToAllTCP(o);
        }
        else if(o instanceof PacketInfo.ReadySignal){
            for(int i = 0; i < players.length; i++){
                if(players[i] == c.getID()){
                    readies[i] = ((PacketInfo.ReadySignal) o).ready;
                    models[i] = ((PacketInfo.ReadySignal) o).model;
                }
            }
            if(allTrue()){
                parent.sendStartSignal();
            }
        }
        else if(o instanceof PacketInfo.Cards){
            parent.addPlayerCards(c.getID(), ((PacketInfo.Cards) o).cards);
        }
    }


    private boolean allTrue(){
        for(int i = 0; i < readies.length; i++){
            System.out.println(players[i]);
            if(!readies[i] && players[i] != 0) return false;
        }
        return true;
    }

    public String[] getModels(){
        return models;
    }
}
