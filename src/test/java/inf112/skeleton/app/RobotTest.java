package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Robot;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class RobotTest {
    private Board board;

    @Before
    public void setUp(){
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new TestpurposesApplicationListener(), conf);
        Gdx.gl = mock(GL20.class);

        board = new Board();
    }

    @Test
    public void takeDamageTest(){
        Robot robot = new Robot(0, 0, "robots/pika.png", board);
        robot.takeDamage(1);
        assertEquals(robot.getMaxHp(), robot.getCurrentHp()+1);
    }

    @Test
    public void newRobotNoCheckpointsTest(){
        Robot robot = new Robot(0, 0, "robots/pika.png", board);
        assertEquals(robot.getCurrentCheckpoint(), 0);
    }

    @Test
    public void robotCheckpoint(){
        Robot robot = new Robot(0, 0, "robots/pika.png", board);

        robot.updateCheckpoint(55);
        assertEquals(robot.getCurrentCheckpoint(), 1);

        robot.updateCheckpoint(63);
        assertEquals(robot.getCurrentCheckpoint(), 2);

    }
}
