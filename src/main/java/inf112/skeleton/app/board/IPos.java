package inf112.skeleton.app.board;

import com.badlogic.gdx.math.Vector2;

public interface IPos {

    int getPosX();

    int getPosY();

    Vector2 getPos();

    void setPosX(int x);

    void setPosY(int y);

    void setPos(int x, int y);

    void setPos(Pos pos);

    Pos copy();

    String toString();
}
