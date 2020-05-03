package comp1110.ass2;


import java.util.ArrayList;

public class Placement {
    /* This class is intended to handle the methods and checks related to the placing of tiles*/
    public static String piecePlacement;
    public static final int LENGTH_OF_ONE_PlACEMENT = 6;
    public static final int LENGTH_OF_TILE_TYPE = 4;

    /**
     * This method is to determine if the placement has a length of
     * multiple of 6 or not.
     *
     * @param placement a string representing the placement
     * @return {@code true} if the given String Placement has a
     * length of multiple 6, {@code false} otherwise
     * @author Jiawei Fan
     */
    public static boolean isPlacementLengthValid(String placement) {
        return placement.length() % 6 == 0;
    }

    /**
     * This method is to determine if the length of the placement is
     * valid and if each piece in the placement is well formed or not.
     *
     * @param placement a string representing the placement
     * @return {@code true} if the each piecePlacement is well formed
     * {@code false} otherwise
     * @author Jiawei Fan
     */
    public static boolean isPlacementWellFormed(String placement) {
        if (isPlacementLengthValid(placement)) {
            for (int i = 0; i < placement.length() / LENGTH_OF_ONE_PlACEMENT; i++) {
                String testPiece = placement.substring(LENGTH_OF_ONE_PlACEMENT * i, LENGTH_OF_ONE_PlACEMENT * i + LENGTH_OF_ONE_PlACEMENT);
                if (!Metro.isPiecePlacementWellFormed(testPiece))
                //determine if each piece placement in the placement is valid or not
                {
                    return false;
                }
            }
            return true;//if each piece pass the test, then each piece is well formed
        } else {
            return false;
        }
    }

    /**
     * This method is to check if the placement is valid or not regarding
     * the pieces in it. For any piece x in the placement, there can be
     * no more piece in the board than in the deck.
     *
     * @param placement a string representing the placement
     * @return {@code true} if For any piece x, there can exist no more
     * instances of x on the board than instances of x in the deck.
     * {@code false} otherwise
     * @author Jiawei Fan
     */
    public static boolean noMoreInstance(String placement) {
        ArrayList<String> initialDeck = Deck.getInitialDeck();
        int numberOfPiece = placement.length() / LENGTH_OF_ONE_PlACEMENT;
        for (int i = 0; i < numberOfPiece; i++) {//test each piece to ensure that there are no more instances of one piece on the board than in the deck
            String testPiece = placement.substring(LENGTH_OF_ONE_PlACEMENT * i, LENGTH_OF_ONE_PlACEMENT * i + LENGTH_OF_TILE_TYPE);
            if (initialDeck.indexOf(testPiece) < 0) {// if there is no this piece in the initial deck return false
                return false;
            } else {// once test one piece in the placement,remove this piece from the deck
                initialDeck.remove(testPiece);
            }
        }
        return true;
    }

    /**
     * This method will determine if two tiles are next to each other or not
     * each tile is represented by a string which has length of 6. The first
     * four characters represent its type and the last two character represent
     * its row and column.
     *
     * @param tile1 a string of length 6 representing the placement of a tile
     * @param tile2 a string of length 6 representing the placement of a tile
     * @return return true if two tiles are neighbours of each other
     * otherwise,return false
     * @author Jiawei Fan
     */
    public static boolean isNeighbour(String tile1, String tile2) {
        int row1 = Integer.parseInt(tile1.substring(LENGTH_OF_TILE_TYPE, LENGTH_OF_TILE_TYPE + 1));
        int col1 = Integer.parseInt(tile1.substring(LENGTH_OF_TILE_TYPE + 1, LENGTH_OF_TILE_TYPE + 2));
        int row2 = Integer.parseInt(tile2.substring(LENGTH_OF_TILE_TYPE, LENGTH_OF_TILE_TYPE + 1));
        int col2 = Integer.parseInt(tile2.substring(LENGTH_OF_TILE_TYPE + 1, LENGTH_OF_TILE_TYPE + 2));
        if (row1 == row2 && col1 == col2) {
            //first test if their coordinates are the same, if they overlap, return is false
            return false;
        }
        if (row1 == row2 || col1 == col2) {
            //when row or col of them are equal
            //And their distance is exactluy one
            return Math.abs(row1 - row2) == 1 || Math.abs(col1 - col2) == 1;
        }
        return false;
    }

    /**
     * This method is in the condition that two tiles are neighbors of each other. It will be
     * called after isNeighbour method returns true later in another main.
     * By passing the placement of two string, this method will return the direction
     * that the second tile is on the first tile
     *
     * @param tile     a 6-length long string representing the placement of the first tile
     * @param testTile a 6-length long string represeting the placement of the second tile
     * @return a one-length long string representing the direction
     * @author Jiawei Fan
     */
    public static String getNeighbourDirection(String tile, String testTile) {
        int row1 = Integer.parseInt(tile.substring(LENGTH_OF_TILE_TYPE, LENGTH_OF_TILE_TYPE + 1));
        int col1 = Integer.parseInt(tile.substring(LENGTH_OF_TILE_TYPE + 1, LENGTH_OF_TILE_TYPE + 2));
        int row2 = Integer.parseInt(testTile.substring(LENGTH_OF_TILE_TYPE, LENGTH_OF_TILE_TYPE + 1));
        int col2 = Integer.parseInt(testTile.substring(LENGTH_OF_TILE_TYPE + 1, LENGTH_OF_TILE_TYPE + 2));
        if (row2 - row1 == -1) {
            return "N";
        }
        if (row2 - row1 == 1) {
            return "S";
        }
        if (col2 - col1 == -1) {
            return "W";
        }
        if (col2 - col1 == 1) {
            return "E";
        }
        return "";//two tiles are not neightbors, actually this will never be reached becaue isNeighbor method will be called as the precondition of this method in another main.
    }
}
