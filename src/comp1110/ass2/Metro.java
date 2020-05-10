package comp1110.ass2;

import java.util.ArrayList;

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
     * between 'a' and 'd' and the last two digits are between 0 and 7
     */


    public static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        /**
         * Some fields are created below which are accessible for this method
         * @param piecePlacement: a 6 character long string
         * @return True if well formed
         *          False if not well formed
         */
        int number_of_char = 6;//used to decide the string contains exactly six characters
        boolean wellFormed = false;
        if (piecePlacement.length() == number_of_char) {
            String tracks = piecePlacement.substring(0, 4);
            String position = piecePlacement.substring(4, 6);

            for (int char_pos = 0; char_pos <= 3; char_pos++) {
                if (tracks.charAt(char_pos) >= 97 && tracks.charAt(char_pos) <= 100) {
                    wellFormed = true;
                } else {
                    wellFormed = false;
                    return wellFormed;
                }
            }
            for (int pos = 0; pos <= 1; pos++) {
                if (Character.getNumericValue(position.charAt(pos)) >= 0 && Character.getNumericValue(position.charAt(pos)) <= 7) {
                    wellFormed = true;
                } else {
                    wellFormed = false;
                    return wellFormed;
                }
            }

        }
        return wellFormed;

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
     * @author Jiawei Fan
     */
    public static boolean isPlacementSequenceWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement sequence is well-formed
        if (Placement.isPlacementWellFormed(placement)) {//after checking that each piece in the placement is valid. Check the requirement that for each piece,
            //there can be more pieces on the board than on in the deck
            return Placement.noMoreInstance(placement);
        } else {// return false if the placement is not well formed
            return false;
        }
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
     * @author Jiawei Fan
     */
    public static String drawFromDeck(String placementSequence, String totalHands) {
        // FIXME Task 5: draw a random tile from the deck
        return Deck.drawFromCurrentDeck(placementSequence, totalHands);
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
     * @author Ganaraj Rao
     */
    public static boolean isPlacementSequenceValid(String placementSequence) {
        // FIXME Task 6: determine whether a placement sequence is valid
        Board board = new Board();
        ArrayList<String> tilePlaced = new ArrayList<>();
        if (board.isEmpty(placementSequence)) {
            return true;
        }

        board.slice(placementSequence, tilePlaced);
        ArrayList<String> positions = new ArrayList<>();
        //board.getTiles(tilePlaced,tiles);
        board.getPositions(tilePlaced, positions);
        boolean overlap = board.checkOverlap(positions);
        boolean checkCS = board.checkCS(positions);

        //checkpoint
        return !overlap && checkCS;
    }

    /**
     * Task 7
     * Determine the current score for the game.
     *
     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param numberOfPlayers   The number of players in the game
     * @return an array containing the scores for all players
     * @author Jiawei Fan
     */
    public static int[] getScore(String placementSequence, int numberOfPlayers) {
        int[] scoreArray = new int[numberOfPlayers];
        ArrayList<PlayerStationAndPlacement> placementArray = Scoring.getEachPlayerPlacement(numberOfPlayers, placementSequence);//get each player's information including number of player in
        // the game,player number,the staions the player owns and the placement the player has already placed
        int index = 0;
        for (PlayerStationAndPlacement x : placementArray) {//test each player's information
            int score;
            score = Scoring.getScore(x, placementSequence);//get score of each player
            scoreArray[index] = score;
            index++;
        }
        return scoreArray;
    }

    /**
     * Task 9
     * Given a placement sequence string, generate a valid next move.
     *
     * @param placementSequence a String representing the sequence of piece placements made so far in the game
     * @param piece             a four-character String representing the tile to be placed
     * @param numberOfPlayers   The number of players in the game
     * @return A valid placement of the given tile
     */
    public static String generateMove(String placementSequence, String piece, int numberOfPlayers) {
        // FIXME Task 9: generate a valid move
        return "";
    }
}
