package inf112.skeleton.app.network;

import java.util.HashMap;

public class PacketInfo {

    public static class ReadySignal{
        public boolean ready;
        @Override
        public String toString(){return "Ready Signal Packet";}
    }

    public static class StartSignal{
        public boolean signal;
        @Override
        public String toString() {
            return "Start Signal Packet";
        }
    }

    public static class Cards{
        public int[] cards;
        public int playerID;
        @Override
        public String toString() {
            return "Cards Packet";
        }
    }

    public static class Deck{
        public int[] cards;
        @Override
        public String toString() {
            return "Deck Packet";
        }
    }

    public static class AllCards{
        public int[][] allCards;
        @Override
        public String toString() {
            return "All Cards Packet";
        }
    }

    public static class Name{
        public String name;
        public int playerID;
        @Override
        public String toString() {
            return "Name Packet";
        }
    }

    public static class NumPlayers{
        public int numPlayers;
        @Override
        public String toString() {
            return "NumPlayers Packet";
        }
    }



}
