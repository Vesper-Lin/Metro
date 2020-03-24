package comp1110.ass2;

public class PlaceTile {

    /* This class is intended to handle the methods and checks related to the placing of tiles*/
    public String tileType;
    public String piecePlacement;
    public String placement;

    /**
     *the validity of placement depends on the current board tile placement, it
     * can only be placed on a square adjacent to another tile or the edge of
     * the board. A tile may not be placed next to one of the central station unless
     * it is also adjacent to another tile
     * A tile may not be placed so that it connects two stations with a track length
     * of 1
     */
    public boolean isPlacementValid(String piecePlacement,String placement)
    {
        return true; // when the placement is valid
    }

    /**
     *if the first four character are letters are between 'a' and 'd',return true
     * false if not. This is the precondition of the validity of the placement
     */
    public boolean isFourCharacterValid(String piecePlacement)
    {
        //String pieceType=piecePlacement.substring(0,4);
        return true;
    }

    /**
     * if the last two digits are between 0 and 7; false if not
     * This is the precondition of the validity of the placement
     */
    public boolean isTwoDigitsValid(String piecePlacement)
    {
        //String piecePosition=piecePlacement.substring(3);
        return true;
    }

    public boolean isOrientationValid() {
        //returns true if the orientation is valid
        return false;
    }

    public void getOrientation() {
        //returns orientation. Void return in signature will be updated

    }

    public String getPiecePlacementString() {
        //returns piece placement String
        return piecePlacement;
    }

    public String getPosition(String piecePlacement) {
        //returns position of the tile.
        return piecePlacement.substring(3);
    }
}
