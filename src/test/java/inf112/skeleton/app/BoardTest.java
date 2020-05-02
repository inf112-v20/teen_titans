package inf112.skeleton.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Pos;
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
    }

    @Test
    public void posOutOfBoundsTest(){
        Pos pos = new Pos(-1, -1);
        assertEquals(testBoard.checkPos(pos), -1);
    }

}
