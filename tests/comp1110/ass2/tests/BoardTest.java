import comp1110.ass2.Board;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void overLapping() {
        Board board = new Board();
        ArrayList<String> positions =new ArrayList<>();
        positions.add("34");
        boolean overlap = board.checkOverlap(positions);
        assertTrue("Expected the position to overlap central stations, but got " + overlap, overlap);
    }
}