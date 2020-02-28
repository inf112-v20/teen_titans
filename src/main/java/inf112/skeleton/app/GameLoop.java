package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import java.util.Random;

public class GameLoop extends Thread{
    Random random;
    Board board;
    public Thread loop;

    public GameLoop() throws InterruptedException {
        board = new Board();
        random = new Random();
        createLoopThread();
    }

    public void createLoopThread() {
        loop = new Thread(() -> {
            while(true) {

                //Game logikk her inne:::::
                //Card handler
                //Movement handler
                //other


                int nextMove = random.nextInt(4);
                switch (nextMove) {
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
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        });
    }



    public Board getBoard(){
        return board;
    }



}
