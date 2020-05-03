package comp1110.ass2;


public class Tile {
    //determine the numbers of pieces for this type in the deck at the beginning
    private String placement;

    public Tile(String placement) {
        this.placement = placement;
    }

    /**
     * get placement of this tile
     *
     * @return a string representing the placement
     * @author Jiawei Fan
     */
    public String getPlacement() {
        return placement;
    }
}

