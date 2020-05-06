package inf112.skeleton.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Pos;
import inf112.skeleton.app.board.Robot;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    Board testBoard;

    @Before
    public void setUp(){
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new TestpurposesApplicationListener(), conf);
        Gdx.gl = mock(GL20.class);
        testBoard = new Board();
        String[] textureNames = new String[]{"robots/pika.png"};
        testBoard.createRobots(1, textureNames);
    }


    @Test
    public void posOutOfBoundsTest(){
        Pos pos = new Pos(-1, -1);
        assertEquals(testBoard.checkPos(pos), -1);
    }

    @Test
    public void posInBoundsTest(){
        Pos pos = new Pos(1, 1);
        assertEquals(testBoard.checkPos(pos), 1);
    }

    @Test
    public void robotMoveTest(){
        Robot robot = testBoard.getRobots()[0];
        Pos oldPos = robot.getPos().copy();
        robot.move(1);
        testBoard.updatePlayer(oldPos, robot);
        assertTrue(!(robot.getPos().equals(oldPos)));
        assertNull(testBoard.getPlayerLayer().getCell(oldPos.getPosX(), oldPos.getPosY()));
        assertNotNull(testBoard.getPlayerLayer().getCell(robot.getPos().getPosX(), robot.getPos().getPosY()));
    }

}
