package comp1110.ass2;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PositionTests {
    Board board = new Board();
    @Test
    //check for valid cases
    public void checkforValid(){
        ArrayList<String> validPositions = new ArrayList<>();
        validPositions.add("04");
        validPositions.add("27");
        validPositions.add("67");
        validPositions.add("57");
        validPositions.add("76");
        validPositions.add("75");
        validPositions.add("30");
        validPositions.add("40");
        ArrayList<String> tileplaced = new ArrayList<>();
        tileplaced.add("aaaa");
        tileplaced.add("bbbb");
        tileplaced.add("cccc");
        tileplaced.add("abaa");
        tileplaced.add("caaa");
        tileplaced.add("bcbb");
        tileplaced.add("cbcc");
        tileplaced.add("abba");
        test(validPositions,tileplaced,true);
    }
    //check for invalid cases
    @Test
    public void checkforInvalid(){

        //ArrayList<Object> valid = new ArrayList<>();
        ArrayList<String> invalidPositions = new ArrayList<>();
        invalidPositions.add("04");
        invalidPositions.add("27");
        invalidPositions.add("76");
        invalidPositions.add("40");
        ArrayList<String> tileplaced1 = new ArrayList<>();
        tileplaced1.add("daaa");
        tileplaced1.add("aaaa");
        tileplaced1.add("bbbb");
        tileplaced1.add("cccc");
        test(invalidPositions,tileplaced1,false);

        }
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    private void test(ArrayList<String>positions,ArrayList<String> tileplaced, Boolean expected) {
        boolean out = board.checkEdges(positions,tileplaced);
        assertEquals("Expected"+expected+"but recieved"+out,(boolean)expected,(boolean)out);
    }


}
