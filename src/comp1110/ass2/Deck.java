package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    /**
     * This method is helpful when adding the known tiles into the deck, by using a for loop, passing
     * tile type and numbers and current deck, this number of tiles will add into the deck.
     *
     * @param currentDeck      current deck which contains tiles
     * @param tileType         a 4-length long String whichs represent the type of the tile to be added
     * @param numberOfThisType the number of this type of tiles to be added
     * @return new  ArrayList<String> which contains the new added tiles at the end
     * @author Jiawei Fan
     */
    public static ArrayList<String> addIntoDeck(ArrayList<String> currentDeck, String tileType, int numberOfThisType) {
        for (int i = 0; i < numberOfThisType; i++) {
            currentDeck.add(tileType);
        }
        return currentDeck;
    }

    /**
     * @return an ArrayLsit<String> which contains all tiles in the initial deck
     * @author Jiawei Fan
     * This method returns the initial state of the deck,all tiles are in the deck.
     * it returns an Arrarlist which contains all the tiles in the deck. Tile is
     * represented by its type.
     */
    public static ArrayList<String> getInitialDeck() {
        ArrayList<String> deck = new ArrayList<>();
        //There are four copies of tile "aacb"
        addIntoDeck(deck, "aacb", 4);
        //There are four copies of tile "cbaa"
        addIntoDeck(deck, "cbaa", 4);
        //There are four copies of tile "acba"
        addIntoDeck(deck, "acba", 4);
        //There are four copies of tile "baac"
        addIntoDeck(deck, "baac", 4);
        //There are four copies of tile "aaaa"
        addIntoDeck(deck, "aaaa", 4);
        //There are three copies of tile "cbcb"
        addIntoDeck(deck, "cbcb", 3);
        //There are three copies of tile "bcbc"
        addIntoDeck(deck, "bcbc", 3);
        //There are two copies of tile "cccc"
        addIntoDeck(deck, "cccc", 2);
        //There are two copies of tile "bbbb"
        addIntoDeck(deck, "bbbb", 2);
        //There are two copies of tile "dacc"
        addIntoDeck(deck, "dacc", 2);
        //There are two copies of tile "cdac"
        addIntoDeck(deck, "cdac", 2);
        //There are two copies of tile "ccda"
        addIntoDeck(deck, "ccda", 2);
        //There are two copies of tile "accd"
        addIntoDeck(deck, "accd", 2);
        //There are two copies of tile "dbba"
        addIntoDeck(deck, "dbba", 2);
        //There are two copies of tile "adbb"
        addIntoDeck(deck, "adbb", 2);
        //There are two copies of tile "badb"
        addIntoDeck(deck, "badb", 2);
        //There are two copies of tile "bbad"
        addIntoDeck(deck, "bbad", 2);
        //There are two copies of tile "ddbc"
        addIntoDeck(deck, "ddbc", 2);
        //There are two copies of tile "cddb"
        addIntoDeck(deck, "cddb", 2);
        //There are two copies of tile "bcdd"
        addIntoDeck(deck, "bcdd", 2);
        //There are two copies of tile "dbcd"
        addIntoDeck(deck, "dbcd", 2);
        //There are two copies of tile "adad"
        addIntoDeck(deck, "adad", 2);
        //There are two copies of tile "dada"
        addIntoDeck(deck, "dada", 2);
        //There are two copies of tile "dddd"
        addIntoDeck(deck, "dddd", 2);
        return deck;
    }

    /**
     * This method is to show the current deck depending on the
     * piece placement on the board and tile on the players' hand
     *
     * @param placementSequence a string contains tiles and their placements coordinates, these tiles are drawn from deck before
     * @param totalHands        a string contains numbers of 4-character long tiles, these tile are also drawn from deck before
     * @return current deck which has removed the tiles which have been placed on board or held on hand
     * @author Jiawei Fan
     */
    public static ArrayList<String> getCurrentDeck(String placementSequence, String totalHands) {
        ArrayList<String> initialDeck = getInitialDeck();//get initial deck
        int pieceNumberOnBoard = placementSequence.length() / 6;
        int pieceNumberOnHand = totalHands.length() / 4;
        for (int i = 0; i < pieceNumberOnBoard; i++) {//remove each piece which is already put on board from the initial deck
            String testPiece = placementSequence.substring(6 * i, 6 * i + 4);
            initialDeck.remove(testPiece);
        }
        for (int i = 0; i < pieceNumberOnHand; i++) {//remove each piece which is held on players' hand from the initial deck
            String testPiece = totalHands.substring(4 * i, 4 * i + 4);
            initialDeck.remove(testPiece);
        }
        return initialDeck;//initial deck has been modified that it doesn't contain pieces
        //that are put on board or held on players' hand
    }

    /**
     * @param placementSequence a long string represents the existing placements of tils on board
     * @param totalHands        a long string represents the tiles held on players' hand
     * @return A string which represents the tile type of the randomly selected tile
     * @author Jiawei Fan
     * This method will be used when a player draw a piece from the deck, it contains
     * a random function to randomly select a piece form the deck. It will firstly get
     * the current deck by removing the pieces that are placed on board or held on players'
     * hand from the initial hand. Then a random function will get a random index which
     * is the index of the selected piece in the current deck.
     */
    public static String drawFromCurrentDeck(String placementSequence, String totalHands) {
        ArrayList<String> currentDeck = getCurrentDeck(placementSequence, totalHands);//get the current deck
        if (currentDeck.size()==0)
        {
            return "";
        }
        Random random = new Random();//get a random object
        int index = random.nextInt(currentDeck.size());//call random method
        return currentDeck.get(index);//get the selected piece depending on the random index
    }

}

