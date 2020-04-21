package inf112.skeleton.app.board;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;



    public Direction next() {
        return values()[(ordinal()+1) % 4];
    }

    public Direction last() {
        return values()[(ordinal()+3) % 4];
    }

    public Direction opposite(){
        return values()[(ordinal()+2) % 4];
    }

}
