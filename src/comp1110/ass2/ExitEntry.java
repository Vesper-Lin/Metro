package comp1110.ass2;

/**
 * This is an enum which contains all the situation that two tiles are connected to each other
 * There are three parameters, the first one is exit of the first tile, the second one is the
 * entry of the next tile.
 * The diagram below shows all possible placements of two neighbor tiles. If tile0 is the first
 * tile and tile2 is the second tile, tile is on the north of tile0, then if the exit of tile0
 * is 0, the entry if tile2 must be 5, if the exit of tile0 is 1, then the entry of tile2 must
 * be 4. For south east and west situation, the cases are similiar.
 *
 * @author Jiawei Fan
 * *              0    1
 * *            7 tile2(N)2
 * *            6         3
 * *              5    4
 * *   0   1      0    1       0    1
 * *7 tile1(W)2 7  tile0  2 7  tile4(E) 2
 * *6         3 6         3 6           3
 * *   5   4      5    4       5    4
 * *              0    1
 * *           7 tile3(S)2
 * *           6         3
 * *              5    4
 */
public enum ExitEntry {
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

    /**
     * This method is able to tell whats the next entry, for loop
     * is used to check each in ExitEntry enum.
     *
     * @param exit ths exit of the current tile
     * @return a int which represent the next entry, -1 is returns
     * if pass in a wrong exit number.
     * @author Jiawei Fan
     */
    public static int getNextEntry(int exit) {
        for (ExitEntry n : ExitEntry.values()) {
            if (n.exit == exit) {
                return n.entry;
            }
        }
        return -1;
    }

    /**
     * This method is able to tell what the next direction of tile is by
     * passing in the exit numebr.
     *
     * @param exit int fomr 0-8 represents the exit number
     * @return a string which represents the direction of next tile,
     * "N" represents north, "S" represents south,"W" represents west,
     * "E" represents east.
     * @author Jiawei Fan
     */
    public static String getNextDirection(int exit) {
        for (ExitEntry n : ExitEntry.values()) {
            if (n.exit == exit) {
                return n.nextDirection;
            }
        }
        return "";
    }


}
