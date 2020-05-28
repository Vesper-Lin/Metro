package comp1110.ass2;

/**
 * This class extends Tile.class and it is used to add in tile exit and entry information.
 *
 * @author Jiawei Fan
 */
public class TileEntryAndExit extends Tile {

    private int exit;

    public TileEntryAndExit(String placement, int exit) {
        super(placement);
        this.exit = exit;
    }

    /**
     * get exit of this tile
     *
     * @return an int representing the exit
     * @author Jiawei Fan
     */
    public int getExit() {
        return exit;
    }
}
