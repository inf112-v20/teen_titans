package inf112.skeleton.app.network.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.network.PacketInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GameServer implements Runnable {

    private int udp;
    private int tcp;
    private InetAddress address;

    private Server server;
    private ServerListener listener;

    public GameServer(int udp, int tcp){
        this.udp = udp;
        this.tcp = tcp;
    }
    public GameServer(){
        udp = 54333;
        tcp = 54555;
    }

    private void registerPacketInfo() {
        Kryo kryo = server.getKryo();
        kryo.register(PacketInfo.Cards.class);
        kryo.register(PacketInfo.Name.class);
        kryo.register(PacketInfo.StartSignal.class);
        kryo.register(int[].class);
        kryo.register(String.class);
        kryo.register(boolean.class);
    }


    @Override
    public void run() {
        server = new Server();
        listener = new ServerListener(server);
        server.addListener(listener);

        try {
            server.bind(tcp, udp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        registerPacketInfo();
        server.start();
        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public InetAddress getAddress(){
        return address;
    }

    public void dispose(){
        try {
            server.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
