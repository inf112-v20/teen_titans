package inf112.skeleton.app.network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.GameLoop;
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

    public void recieved(Connection c, Object o){
        if(o instanceof PacketInfo.Cards){
            //Cards recieved, alert gameloop.
        }

    }


}
