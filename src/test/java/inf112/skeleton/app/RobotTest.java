package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Direction;
import inf112.skeleton.app.board.Pos;
import inf112.skeleton.app.board.Robot;
import static org.junit.Assert.*;

import inf112.skeleton.app.scenes.game.HudManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.mockito.Mockito.mock;

public class RobotTest {
    private Board board;
    private Random random;

    @Before
    public void setUp(){
        random = new Random();
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new TestpurposesApplicationListener(), conf);
        Gdx.gl = mock(GL20.class);

        board = new Board();
    }

    @Test
    public void constructorTest(){
        int xpos = random.nextInt(board.getBOARDWIDTH());
        int ypos = random.nextInt(board.getBOARDHEIGHT());
        Robot robot = new Robot(xpos, ypos, "robots/pika.png", board);

        assertEquals(robot.getPos().getPosX(), xpos);
        assertEquals(robot.getPos().getPosY(), ypos);
        assertEquals(robot.getCurrentHp(), robot.getMaxHp());
        assertEquals(robot.getCurrentState(), robot.getPlayerStates().get("alive"));
        assertEquals(robot.getDir(), Direction.NORTH);
    }

    @Test
    public void createPlayerTextureTest(){
        Robot robot = new Robot(0, 0, "robots/pika.png", board);
        assertEquals(robot.getPlayerStates().size(), 3);
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
    public void robotCheckpointTest(){
        Robot robot = new Robot(0, 0, "robots/pika.png", board);
        robot.updateCheckpoint(55);
        assertEquals(robot.getCurrentCheckpoint(), 1);
        robot.updateCheckpoint(63);
        assertEquals(robot.getCurrentCheckpoint(), 2);
        robot.updateCheckpoint(71);
        assertEquals(robot.getCurrentCheckpoint(), 3);
        robot.updateCheckpoint(79);
        assertEquals(robot.getCurrentCheckpoint(), 4);
    }

    @Test
    public void recieveHudTest(){
        Robot robot = new Robot(0, 0, "robots/pika.png", board);
        assertEquals(robot.getHud(), null);
        HudManager mockHUD = mock(HudManager.class);
        robot.recieveHud(mockHUD);
        assertNotEquals(robot.getHud(), null);
    }

    @Test
    public void turnTest(){
        Robot robot = new Robot(0, 0, "robots/pika.png", board);
        robot.turn(true);
        assertEquals(robot.getDir(), Direction.EAST);
        robot.turn(false);
        assertEquals(robot.getDir(), Direction.NORTH);
        robot.turn(false);
        assertEquals(robot.getDir(), Direction.WEST);
        robot.turn(false);
        assertEquals(robot.getDir(), Direction.SOUTH);
        robot.turn(false);
        assertEquals(robot.getDir(), Direction.EAST);
    }

}
