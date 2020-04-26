package comp1110.ass2;

import java.util.ArrayList;
import java.util.jar.JarOutputStream;

public class Metro {
    /**
     * Task 2
     * Determine whether a piece placement is well-formed. For a piece
     * placement to be well-formed, it must:
     * - contain exactly six characters;
     * - have as its first, second, third and fourth characters letters between
     * 'a' and 'd' inclusive (tracks); and
     * - have as its fifth and sixth characters digits between 0 and 7 inclusive
     * (column and row respectively).
     *
     * @param piecePlacement A String representing the piece to be placed
     * @return True if this string is well-formed
     */

    /**
     * Two methods used to decided whether the first four character are letters
     *  between 'a' and 'd' and the last two digits are between 0 and 7
     */


    public static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        /**
         * Some fields are created below which are accessible for this method
         */
        int NUMBER_OF_CHAR=6;//used to decide the string contains exactly six characters
        boolean wellFormed=false;
        if(piecePlacement.length()==NUMBER_OF_CHAR){
            String tracks= piecePlacement.substring(0,4);
            String position =piecePlacement.substring(4,6);

            for(int char_pos =0;char_pos<=3;char_pos++){
                if(tracks.charAt(char_pos)>=97 && tracks.charAt(char_pos)<=100) {
                    wellFormed=true;
                }
                else{
                    wellFormed=false;
                    return wellFormed;
                }
            }
            for(int pos=0;pos<=1;pos++){
                if(Character.getNumericValue(position.charAt(pos))>=0&& Character.getNumericValue(position.charAt(pos))<=7){
                    wellFormed=true;
                }
                else{
                    wellFormed=false;
                    return wellFormed;
                }
            }

        }
        if(wellFormed==true){
            return true;
        }
        return false;
        /*int flag1=1; //first version of task 2
        int flag2=1;
        int flag3=1;
        if (piecePlacement.length()!=NUMBER_OF_CHAR)
        {
            flag1=0;
        }
        for (int i=0;i<4;i++)
        {
            String a=piecePlacement.substring(i,i+1);
            if (a.compareTo("a")<0||a.compareTo("d")>0)
            {
                flag2=0;
                break;
            }
        }
        for (int i=0;i<2;i++)
        {
            String a=piecePlacement.substring(i+4,i+5);
            if (a.compareTo("0")<0||a.compareTo("7")>0)
            {
                flag3=0;
                break;
            }
        }
        if (flag1==1&&flag2==1&&flag3==1)
        {
            return true;
        }
        else
        {
            return false;
        }*/


    }

    /**
     * Task 3
     * Determine whether a placement sequence string is well-formed.
     * For a placement sequence to be well-formed, it must satisfy the
     * following criteria:
     * - It must be composed of well-formed tile placements.
     * - For any piece x, there can exist no more instances of x on the board
     * than instances of x in the deck.
     *
     * @param placement A String representing the placement of all tiles on the
     *                  board
     * @return true if this placement sequence is well-formed
     */
    public static boolean isPlacementSequenceWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement sequence is well-formed
        Deck adeck=new Deck();
        Tile[] deck=adeck.getInitialDeck(); //get the initial deck which contains all tiles
        if (placement.length()%6!=0)
        {
            return false;
        }
        int numberOfPiece=placement.length()/6;
        for (int i=0;i<numberOfPiece;i++)
        {
            String testPiece=placement.substring(6*i,6*i+6);
            if (!isPiecePlacementWellFormed(testPiece)) //determine if each piece placement in the placement is valid or not
            {
                return false;
            }
        }
        int[] mark=new int[numberOfPiece]; //a marker used to mark the tile which has been tested
        ArrayList<Tile> tile=new ArrayList<>();
        for (int i=0;i<numberOfPiece;i++) //an algorithm thought of to calculate the number of each type of tile in the placement
        {
            if (mark[i]==0)
            {
                Tile newtile=new Tile(placement.substring(6*i,6*i+4),1);
            for (int j=i+1;j<numberOfPiece-1;j++)
            {
                if (placement.substring(6*j,6*j+4).equals(placement.substring(6*i,6*i+4)))
                {
                    newtile.addNumber();
                    mark[j]=1;
                }
            }
            tile.add(newtile);
            }
        }
        int[] flag=new int[tile.size()];//test if the piece is actually from deck
        for (Tile e:tile)
        {
            for(Tile f:deck)
            {
                if (e.getType().equals(f.getType())) //firstly test the tile type in the placement is from the initial deck
                {
                    int index=tile.indexOf(e);
                    flag[index]=1;//if the deck has this type of tile, mark the tile as 1
                    if (e.getNumber()>f.getNumber()) //to meet the requirement that "- For any piece x, there can exist no more instances of x on the board than instances of x in the deck."
                    {
                        return false;
                    }
                }
            }
        }
        for (int c:flag)////test if the tile is actually from deck, if one tile is not marked as 1, then this placement is not valid
        {
            if (c==0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Task 5
     * Draw a random tile from the deck.
     *
     * @param placementSequence a String representing the sequience of tiles
     *                          that have already been played
     * @param totalHands        a String representing all tiles (if any) in
     *                          all players' hands
     * @return a random tile from the deck
     */
    public static String drawFromDeck(String placementSequence, String totalHands) {
        // FIXME Task 5: draw a random tile from the deck
        int handNumber=totalHands.length()/4;
        StringBuilder atotalHands= new StringBuilder(totalHands);//use StringBuilder because want to modify String totalhands
        for (int i=0;i<handNumber;i++)
        {
            atotalHands.insert(4+6*i,"00");//add "00" to the end of each tile in hand,"00" represents they are not placed yet
        }
        System.out.println(atotalHands.toString());
        totalHands=atotalHands.toString();//convert back to String, but have "00"s after each tile
        String totalPieces=placementSequence+totalHands;//add the placementSequence and totalHands together
        System.out.println(totalPieces);
        int numberOfPiece=totalPieces.length()/6;
        int[] mark=new int[numberOfPiece];//Similar to Task 4, mark is used to mark the tile which has been tested
        ArrayList<Tile> tile=new ArrayList<>();
        for (int i=0;i<numberOfPiece;i++)//similar to the code in task 4, calculate the number of each type of tile
        {
            if (mark[i]==0)
            {
                Tile newtile=new Tile(totalPieces.substring(6*i,6*i+4),1);
                for (int j=i+1;j<numberOfPiece-1;j++)
                {
                    if (totalPieces.substring(6*j,6*j+4).equals(totalPieces.substring(6*i,6*i+4)))
                    {
                        newtile.addNumber();
                        mark[j]=1;
                    }
                }
                tile.add(newtile);
            }
        }
        Deck adeck=new Deck();//initialize a Deck in order to call the method in this class, maybe can change it to a static method later. But both looks fine to me.
        Tile[] deck=adeck.getInitialDeck();//Call the method to get the initial deck.
        for (Tile e:tile)//remove the tiles which have been placed or held in hand from the deck
        {
            for(Tile f:deck)
            {
                if (e.getType().equals(f.getType()))
                {
                    for (int i=0;i<e.getNumber();i++)
                    {
                        f.setNumber();//decrease this type of tile's number in deck by one, because it has been placed or held in hand
                    }
                }
            }
        }
        String drawnTile;
        drawnTile=adeck.isDrawnTile(deck);//adeck is the new deck which contains the tiles can be randomly selected
        return drawnTile;
    }

    /**
     * Task 6
     * Determine if a given placement sequence follows the rules of the game.
     * These rules are:
     * - No tile can overlap another tile, or any of the central stations.
     * - A tile cannot be placed next to one of the central squares unless it
     * continues or completes an existing track.
     * - If a tile is on an edge of the board, it cannot contain a track that
     * results in a station looping back to itself, UNLESS that tile could not
     * have been placed elsewhere.
     * - If a tile is on a corner of the board, it cannot contain a track that
     * links the two stations adjacent to that corner, UNLESS that tile could
     * not have been placed elsewhere.
     *
     * @param placementSequence A sequence of placements on the board.
     * @return Whether this placement string is valid.
     */
    public static boolean isPlacementSequenceValid(String placementSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        Board board = new Board();
        boolean status=board.getStatus(placementSequence);
        if(status==false){
            return false;
        }
        else
        return true;
    }

    /**
     * Task 7
     * Determine the current score for the game.
     *
     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param numberOfPlayers   The number of players in the game
     * @return an array containing the scores for all players
     */
    public static int[] getScore(String placementSequence, int numberOfPlayers) {
        // FIXME Task 7: determine the current score for the game
        return new int[0];
    }

    /**
     * Task 9
     * Given a placement sequence string, generate a valid next move.
     *
     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param piece             a four-character String representing the tile just drawn
     * @param hand              A tile in the player's hand, which they can choose to play instead of the drawn tile.
     *                          If the player does not currently hold a tile, this parameter will be null.
     * @param numberOfPlayers   The number of players in the game
     * @return A valid placement of other the drawn tile or the tile from the player's hand (if it is not empty).
     */
    public static String generateMove(String placementSequence, String piece, String hand, int numberOfPlayers) {
        // FIXME Task 9: generate a valid move
        return "";
    }
}
