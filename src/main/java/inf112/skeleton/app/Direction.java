package inf112.skeleton.app;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;



    public Direction next() {
        System.out.println("Ordinal = " + ordinal());
        return values()[(ordinal()+1) % 4];
    }

    public Direction last() {
        System.out.println("Ordinal = " + ordinal());
        return values()[(ordinal()+3) % 4];
    }

}
