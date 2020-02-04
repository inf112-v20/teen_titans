package gamemap;

public class GameMap<E>{
    private E[][] map;

    public GameMap(int width, int height){
        map = (E[][]) new Object[width][height];
    }



    public void set(E elem, int x, int y) {
        map[x][y] = elem;
    }

    public Object get(int x, int y) {
        return map[x][y];
    }

    public void remove(int x, int y) {
        map[x][y] = null;
    }
}
