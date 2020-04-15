package inf112.skeleton.app.board;

import com.badlogic.gdx.math.Vector2;

public class Pos implements IPos {

    private int x;
    private int y;
    private Vector2 pos;

    @Override
    public int getPosX() {
        return x;
    }

    @Override
    public int getPosY() {
        return y;
    }

    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public void setPosX(int x) {
        this.x = x;
        pos.x = x;
    }

    @Override
    public void setPosY(int y) {
        this.y = y;
        pos.y = y;
    }

    @Override
    public void setPos(int x, int y) {
        pos = new Vector2(x, y);
        this.x = x;
        this.y = y;
    }

    public Pos copy(){
        Pos copy = new Pos();
        copy.setPos(x, y);
        return copy;
    }

    @Override
    public String toString() {
        return "(" + getPosX() + ", " + getPosY() + ")";
    }


}
