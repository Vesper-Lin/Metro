package comp1110.ass2;

public class TileEntryAndExit extends Tile {

    private int entry;
    private int exit;

    public TileEntryAndExit(String placement, int entry, int exit) {
        super(placement);
        this.entry = entry;
        this.exit = exit;
    }

    /**
     * get entry of this tile
     *
     * @return an int representing the entry
     * @author Jiawei Fan
     */
    public int getEntry() {
        return entry;
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
