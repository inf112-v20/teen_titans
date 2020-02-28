package inf112.skeleton.app;

import static org.junit.Assert.assertTrue;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for simple App.
 */

public class AppTest {

    /**
     @Before
     public void setUp(){
     Board game = new Board();
     }
     */

    /**@Test
    public void robotTakesDamageTest(){
        Robot robot = new Robot(0, 0);
        int oldHP = robot.getCurrentHP();
        robot.takeDamage(2);
        int newHP = robot.getCurrentHP();
        assertTrue(oldHP > newHP);
    }

    @Test
    public void IllegalMoveDownBelowMapTest(){
        GameLoop game = new GameLoop();
        Board board = game.getBoard();
        Robot bob = board.getPlayers()[0];
        board.keyUp(Input.Keys.DOWN);
        Vector2 playerPos = bob.getPos();

        assertTrue(playerPos.x == 0 && playerPos.y == 0);

    }

    @Test
    public void MoveUpTest(){
        GameLoop game = new GameLoop();
        Board board = game.getBoard();
        Robot bob = board.getPlayers()[0];
        board.keyUp(Input.Keys.UP);
        Vector2 playerPos = bob.getPos();

        assertTrue(playerPos.x == 0 && playerPos.y == 1);

    }

    @Test
    public void MoveRightTest() {
        GameLoop game = new GameLoop();
        Board board = game.getBoard();
        Robot bob = board.getPlayers()[0];
        board.keyUp(Input.Keys.RIGHT);
        Vector2 playerPos = bob.getPos();

        assertTrue(playerPos.x == 1 && playerPos.y == 0);

    }

    @Test
    public void IllegalMoveLeftOutMapTest(){
        GameLoop game = new GameLoop();
        Board board = game.getBoard();
        Robot bob = board.getPlayers()[0];
        board.keyUp(Input.Keys.LEFT);
        Vector2 playerPos = bob.getPos();

        assertTrue(playerPos.x == 0 && playerPos.y == 0);

    }

    ///player position test

    ///player hp test (hvis han tar 1 damage orginal hp -1)

    ///game map test

    ///player dead, true hvis lever, false død

    ///player move, hvis han står i en posisjon så keycode, ny posisjon(keycode)

    ///illegal moves

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }*/
}
