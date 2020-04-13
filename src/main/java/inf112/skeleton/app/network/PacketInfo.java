package inf112.skeleton.app.network;

import java.util.HashMap;

public class PacketInfo {

    public static class StartSignal{
        public boolean signal;
    }

    public static class Cards{
        public int[] cards;
        public int playerID;
    }

    public static class AllCards{
        public HashMap<Integer, int[]> allCards;
    }

    public static class Name{
        public String name;
        public int playerID;
    }

    public static class NumPlayers{
        public int numPlayers;
    }



}
