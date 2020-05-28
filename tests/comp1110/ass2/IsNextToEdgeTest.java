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
            new TileEntryAndExit("aaaa54", 3),
            new TileEntryAndExit("bcbc73", 2),
            new TileEntryAndExit("aacb62", 4),
            new TileEntryAndExit("baac22", 8),
            new TileEntryAndExit("bcbc73", 3),
            new TileEntryAndExit("ccda12", 1),
            new TileEntryAndExit("adbb21", 3),
            new TileEntryAndExit("bcbc73", 3),
            new TileEntryAndExit("bcbc33", 4)
    };

    public static final TileEntryAndExit[] isNextToEdge = new TileEntryAndExit[]{
            new TileEntryAndExit("aaaa40", 7),
            new TileEntryAndExit("bcbc30", 7),
            new TileEntryAndExit("aacb02", 1),
            new TileEntryAndExit("baac04", 1),
            new TileEntryAndExit("bcbc47", 3),
            new TileEntryAndExit("ccda67", 3),
            new TileEntryAndExit("adbb71", 5),
            new TileEntryAndExit("bcbc73", 5),
            new TileEntryAndExit("bcbc75", 5)
    };

    public static final TileEntryAndExit[] isNextToEdgeButWrongExit = new TileEntryAndExit[]{
            new TileEntryAndExit("aaaa40", 3),
            new TileEntryAndExit("bcbc30", 6),
            new TileEntryAndExit("aacb02", 3),
            new TileEntryAndExit("baac04", 5),
            new TileEntryAndExit("bcbc47", 4),
            new TileEntryAndExit("ccda67", 6),
            new TileEntryAndExit("adbb71", 7),
            new TileEntryAndExit("bcbc73", 3),
            new TileEntryAndExit("bcbc75", 1)
    };


    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    private void test(TileEntryAndExit tile, Boolean expected) {
        boolean out = Scoring.isNextToEdge(tile);
        assertEquals("expected " + expected + ", but got " + out+" for "+tile.getPlacement()+" with exit: "+tile.getExit(), out, expected);
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
