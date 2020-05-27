package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CornerTileTest {
    Board board = new Board();
    @Test
    //check for valid cases
    public void checkforValid(){
        ArrayList<String> validPositions = new ArrayList<>();
        validPositions.add("00");
        validPositions.add("07");
        validPositions.add("70");
        validPositions.add("77");
        ArrayList<String> tileplaced = new ArrayList<>();
        tileplaced.add("aaaa");
        tileplaced.add("caca");
        tileplaced.add("abaa");
        tileplaced.add("caaa");
        test(validPositions,tileplaced,true);
    }
    @Test
    //check for valid cases
    public void checkforInValid(){
        ArrayList<String> invalidPositions1 = new ArrayList<>();
        invalidPositions1.add("00");
        invalidPositions1.add("07");
        ArrayList<String> invalidPositions2 = new ArrayList<>();
        invalidPositions2.add("70");
        invalidPositions2.add("77");
        ArrayList<String> tileplaced1 = new ArrayList<>();
        tileplaced1.add("caaa");
        tileplaced1.add("cccc");
        ArrayList<String> tileplaced2 = new ArrayList<>();
        tileplaced2.add("acba");
        tileplaced2.add("abca");
        test(invalidPositions1,tileplaced1,false);
        test(invalidPositions2,tileplaced2,false);
    }


    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    private void test(ArrayList<String>positions,ArrayList<String> tileplaced, Boolean expected) {
        boolean out = board.cornerCheck(positions,tileplaced);
        assertEquals("Expected "+expected+"but recieved "+out,(boolean)expected,(boolean)out);
    }


}
