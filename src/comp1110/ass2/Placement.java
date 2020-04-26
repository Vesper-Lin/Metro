package comp1110.ass2;


import java.util.ArrayList;

public class Placement {

    /* This class is intended to handle the methods and checks related to the placing of tiles*/
    public static String piecePlacement;


    /**
     * This method is to determine if the placement has a length of
     * multiple of 6 or not.
     * @author Jiawei Fan
     * @param placement
     * @return  {@code true} if the given String Placement has a
     *          length of multiple 6, {@code false} otherwise
     */
    public static boolean isPlacementLengthValid(String placement) {
        return placement.length() % 6 == 0;
    }

    /**
     * This method is to determine if the length of the placement is
     * valid and if each piece in the placement is well formed or not.
     * @author Jiawei Fan
     * @param placement
     * @return {@code true} if the each piecePlacement is well formed
     *          {@code false} otherwise
     */
    public static boolean isPlacementWellFormed(String placement)
    {
        if (isPlacementLengthValid(placement))
        {
            for (int i=0;i<placement.length()/6;i++)
            {
                String testPiece=placement.substring(6*i,6*i+6);
                if (!Metro.isPiecePlacementWellFormed(testPiece))
                //determine if each piece placement in the placement is valid or not
                {
                    return false;
                }
            }
            return true;//if each piece pass the test, then each piece is well formed
        }
        else
        {
            return false;
        }
    }

    /**
     * This method is to check if the placement is valid or not regarding
     * the pieces in it. For any piece x in the placement, there can be
     * no more piece in the board than in the deck.
     * @author Jiawei Fan
     * @param placement
     * @return {@code true} if For any piece x, there can exist no more
     *          instances of x on the board than instances of x in the deck.
     *          {@code false} otherwise
     */
    public static boolean noMoreInstance(String placement)
    {
        ArrayList<String> initialDeck=Deck.getInitialDeck();
        int numberOfPiece=placement.length()/6;
        for (int i=0;i<numberOfPiece;i++)
        {//test each piece to ensure that there are no more instances of one piece on the board than in the deck
            String testPiece=placement.substring(6*i,6*i+4);
            if (initialDeck.indexOf(testPiece)<0)
            {// if there is no this piece in the initial deck return false
                return false;
            }
            else
            {// once test one piece in the placement,remove this piece from the deck
                initialDeck.remove(testPiece);
            }
        }
        return true;
    }

    /**
     * This method will determine if two tiles are next to each other or not
     * each tile is represented by a string which has length of 6.
     * @author Jiawei Fan
     * @param tile1
     * @param tile2
     * @return return true if two tiles are neighbours of each other
     *         otherwise,return false
     */
    public static boolean isNeighbour(String tile1,String tile2) {
        int row1 = Integer.parseInt(tile1.substring(4, 5));
        int col1 = Integer.parseInt(tile1.substring(5, 6));
        int row2 = Integer.parseInt(tile2.substring(4, 5));
        int col2 = Integer.parseInt(tile2.substring(5, 6));
        if (row1 == row2 && col1 == col2) {
            return false;
        }
        if (row1 == row2 || col1 == col2) {
            if (Math.abs(row1 - row2) == 1 || Math.abs(col1 - col2) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will determine if the one piecePlacement is from the edge
     * station or not. This method will be beneficial for scoring later because
     * scoring is from edge station.
     * @Author Jiawei Fan
     * @param placement
     * @param piecePlacement
     * @return return true if the tile is placed from a edge station
     *         return false if the tile is not connected to the edge station or
     *         it is connected to the edge station but it is connected to the
     *         edge station because it is placed on a square adjacent to another
     *         tile.
     */
    public static boolean isFromStartStation(String placement,String piecePlacement)
    {
        int row = Integer.parseInt(piecePlacement.substring(4, 5));
        int col = Integer.parseInt(piecePlacement.substring(5, 6));
        if (row==0||row==7||col==0||col==7)
        {//then this tile is placed on the edge of the board
            int index=placement.indexOf(piecePlacement);
            int numberOfEarlierTiles=index/6;
            for (int i=0;i<numberOfEarlierTiles;i++)
            {
                String testPiecePlacement=placement.substring(6*i,6*i+6);//test each piece placement before this placement
                if (isNeighbour(testPiecePlacement,piecePlacement))//determine if the placement has neighbor or not
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * if the first four character are letters are between 'a' and 'd',return true
     * false if not. This is the precondition of the validity of the placement
     */
    public boolean isFourCharacterValid(String piecePlacement) {
        //String pieceType=piecePlacement.substring(0,4);
        return true;
    }

    /**
     * if the last two digits are between 0 and 7; false if not
     * This is the precondition of the validity of the placement
     */
    public boolean isTwoDigitsValid(String piecePlacement) {
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
