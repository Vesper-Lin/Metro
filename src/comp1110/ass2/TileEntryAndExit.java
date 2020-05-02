package comp1110.ass2;

public class TileEntryAndExit extends Tile {

    private int entry;
    private int exit;
    public TileEntryAndExit(String placement,int entry,int exit) {
        super(placement);
        this.entry=entry;
        this.exit=exit;
    }

    public int getEntry()
    {
        return entry;
    }

    public int getExit()
    {
        return exit;
    }
}
