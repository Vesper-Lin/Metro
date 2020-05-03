package comp1110.ass2;

import org.junit.Test;

import static comp1110.ass2.Player.testPlayer;
import static org.junit.Assert.assertEquals;

/**
 * This test class is going to test whether the two parameters,
 * numberOfPlayer and playerNumber, within Player Class,
 * are valid or not.
 *
 * @author Yuxuan Lin
 */

public class PlayerTest{
    @Test
    public void player_test(){
        assertEquals(true, testPlayer(2, 1));
        assertEquals(true, testPlayer(3, 2));
        assertEquals(true, testPlayer(5, 5));
        assertEquals(true, testPlayer(6, 4));
        assertEquals(false, testPlayer(7, 2));
        assertEquals(false, testPlayer(4, 5));
        assertEquals(false, testPlayer(3, 0));
        assertEquals(false, testPlayer(0, 0));
    }
}