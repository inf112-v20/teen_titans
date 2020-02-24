package inf112.skeleton.app;

import com.badlogic.gdx.Input;

import java.util.Random;

public class GameLoop extends Thread{
    Random random;
    Board board;

    public GameLoop(){
        board = new Board();
        random = new Random();
        loop();
    }


    public void loop(){
        int nextMove = random.nextInt(4);
        switch(nextMove) {
            case 0:
                board.keyUp(Input.Keys.UP);
                break;
            case 1:
                board.keyUp(Input.Keys.DOWN);
                break;
            case 2:
                board.keyUp(Input.Keys.LEFT);
                break;
            case 3:
                board.keyUp(Input.Keys.RIGHT);
                break;
        }
    }

    public Board getBoard(){
        return board;
    }



}
