package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This test class is going to test three different cases
 * 1.the coordinates of the tile indicates that tiles are not next to edge station
 * 2.The tiles are next to edge station and exit also matches
 * 3.The tils are next to edge station but exit does not match,which means track does not end at an edge station
 *
 * @author Jiawei Fan
 */
public class IsNextToEdgeTest {
    public static final TileEntryAndExit[] notNextToEdge = new TileEntryAndExit[]{
            new TileEntryAndExit("aaaa54", 4, 3),
            new TileEntryAndExit("bcbc73", 3, 2),
            new TileEntryAndExit("aacb62", 1, 4),
            new TileEntryAndExit("baac22", 3, 8),
            new TileEntryAndExit("bcbc73", 2, 3),
            new TileEntryAndExit("ccda12", 4, 1),
            new TileEntryAndExit("adbb21", 6, 3),
            new TileEntryAndExit("bcbc73", 2, 3),
            new TileEntryAndExit("bcbc33", 2, 4)
    };

    public static final TileEntryAndExit[] isNextToEdge = new TileEntryAndExit[]{
            new TileEntryAndExit("aaaa40", 4, 7),
            new TileEntryAndExit("bcbc30", 2, 7),
            new TileEntryAndExit("aacb02", 2, 1),
            new TileEntryAndExit("baac04", 4, 1),
            new TileEntryAndExit("bcbc47", 8, 3),
            new TileEntryAndExit("ccda67", 6, 3),
            new TileEntryAndExit("adbb71", 2, 5),
            new TileEntryAndExit("bcbc73", 4, 5),
            new TileEntryAndExit("bcbc75", 6, 5)
    };

    public static final TileEntryAndExit[] isNextToEdgeButWrongExit = new TileEntryAndExit[]{
            new TileEntryAndExit("aaaa40", 4, 3),
            new TileEntryAndExit("bcbc30", 3, 6),
            new TileEntryAndExit("aacb02", 1, 3),
            new TileEntryAndExit("baac04", 3, 5),
            new TileEntryAndExit("bcbc47", 2, 4),
            new TileEntryAndExit("ccda67", 4, 6),
            new TileEntryAndExit("adbb71", 6, 7),
            new TileEntryAndExit("bcbc73", 2, 3),
            new TileEntryAndExit("bcbc75", 2, 1)
    };


    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    private void test(TileEntryAndExit tile, Boolean expected) {
        boolean out = Scoring.isNextToEdge(tile);
        assertEquals("expected " + expected + ", but got " + out+" for "+tile.getPlacement()+" with exit: "+tile.getExit(), out, (boolean) expected);
    }

    @Test
    public void testNotNextEdge() {
        for (TileEntryAndExit tile : notNextToEdge) {
            test(tile, false);
        }
    }

    @Test
    public void testIsNextEdge() {
        for (TileEntryAndExit tile : isNextToEdge) {
            test(tile, true);
        }
    }

    @Test
    public void testIsNextEdgeButWrongExit() {
        for (TileEntryAndExit tile : isNextToEdgeButWrongExit) {
            test(tile, false);
        }
    }
}
