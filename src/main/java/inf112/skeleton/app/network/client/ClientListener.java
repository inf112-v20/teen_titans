package inf112.skeleton.app.network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.scenes.game.GameLoop;
import inf112.skeleton.app.cards.ICard;
import inf112.skeleton.app.network.PacketInfo;
import inf112.skeleton.app.network.Translator;

public class ClientListener extends Listener {

    private GameLoop gameLoop;
    private GameClient parent;
    private Client client;

    public void constructor(GameLoop gameLoop, GameClient parent){
        this.parent = parent;
        this.client = parent.getClient();
        this.gameLoop = gameLoop;
    }

    public void sendCards(ICard[] programCards){
        PacketInfo.Cards cards = new PacketInfo.Cards();
        cards.cards = Translator.cardsToInts(programCards);
        cards.playerID = client.getID();
        client.sendTCP(cards);
    }

    public void sendName(String name){
        PacketInfo.Name namePacket = new PacketInfo.Name();
        namePacket.name = name;
        namePacket.playerID = client.getID();
        client.sendTCP(namePacket);
    }

    public void received(Connection c, Object o){
        if(!(o instanceof FrameworkMessage.KeepAlive)) {
            System.out.println("Client recieved: " + o.toString());
        }
        if(o instanceof PacketInfo.Deck){
            parent.setDeck(((PacketInfo.Deck) o).cards);
        }
        else if(o instanceof PacketInfo.Name){
            parent.addName(((PacketInfo.Name) o).playerID, ((PacketInfo.Name) o).name);
        }
        else if(o instanceof PacketInfo.NumPlayers){
            parent.setPlayerAmount(((PacketInfo.NumPlayers) o).numPlayers);
        }
        else if(o instanceof PacketInfo.StartSignal){
            parent.setStartSignal(true);
        }
        else if(o instanceof PacketInfo.Cards){
            System.out.println("Client listener: Client received hand.");
            parent.handReceived(((PacketInfo.Cards) o).cards);
        }

        else if(o instanceof PacketInfo.AllCards){
            parent.allCardsReceived(((PacketInfo.AllCards) o).allCards);
        }

    }


}
