package inf112.skeleton.app;

import static org.junit.Assert.*;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Pos;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    Board testBoard;

    @Before
    public void setUp(){
        testBoard = new Board();
    }

    @Test
    public void posOutOfBoundsTest(){
        Pos pos = new Pos(-1, -1);
        assertEquals(testBoard.checkPos(pos), -1);
    }

}
