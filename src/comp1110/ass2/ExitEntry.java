package comp1110.ass2;


import java.util.HashMap;
import java.util.Map;

public enum ExitEntry {
    /**
     * tile2(N)
     * tile1(W)   tile0     tile3(E)
     * tile4(S)
     */
    EXIT_ENTRY1(7, 2, "W"),
    EXIT_ENTRY2(6, 3, "W"),
    EXIT_ENTRY3(5, 0, "S"),
    EXIT_ENTRY4(4, 1, "S"),
    EXIT_ENTRY5(3, 6, "E"),
    EXIT_ENTRY6(2, 7, "E"),
    EXIT_ENTRY7(1, 4, "N"),
    EXIT_ENTRY8(0, 5, "N");
    private int exit;
    private int entry;
    private String nextDirection;

    ExitEntry(int exit, int entry, String nextDirection) {
        this.exit = exit;
        this.entry = entry;
        this.nextDirection = nextDirection;
    }

    public static int getNextEntry(int exit) {
        for (ExitEntry n : ExitEntry.values()) {
            if (n.exit == exit) {
                return n.entry;
            }
        }
        return -1;
    }

    public static String getNextDirection(int exit) {
        for (ExitEntry n : ExitEntry.values()) {
            if (n.exit == exit) {
                return n.nextDirection;
            }
        }
        return "";
    }


}
