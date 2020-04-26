package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    /**
     * @author Jiawei Fan
     * this method returns the initial state of the deck,all tiles are in the deck.
     * it returns an Arrarlist which contains all the tiles in the deck. Tile is
     * represented by its type.
     */
    public static ArrayList<String> getInitialDeck() {
        ArrayList<String> initialDeck = new ArrayList<>();
        //There are four copies of tile "aacb"
        initialDeck.add("aacb");
        initialDeck.add("aacb");
        initialDeck.add("aacb");
        initialDeck.add("aacb");
        //There are four copies of tile "cbaa"
        initialDeck.add("cbaa");
        initialDeck.add("cbaa");
        initialDeck.add("cbaa");
        initialDeck.add("cbaa");
        //There are four copies of tile "acba"
        initialDeck.add("acba");
        initialDeck.add("acba");
        initialDeck.add("acba");
        initialDeck.add("acba");
        //There are four copies of tile "baac"
        initialDeck.add("baac");
        initialDeck.add("baac");
        initialDeck.add("baac");
        initialDeck.add("baac");
        //There are four copies of tile "aaaa"
        initialDeck.add("aaaa");
        initialDeck.add("aaaa");
        initialDeck.add("aaaa");
        initialDeck.add("aaaa");
        //There are three copies of tile "cbcb"
        initialDeck.add("cbcb");
        initialDeck.add("cbcb");
        initialDeck.add("cbcb");
        //There are three copies of tile "bcbc"
        initialDeck.add("bcbc");
        initialDeck.add("bcbc");
        initialDeck.add("bcbc");
        //There are two copies of tile "cccc"
        initialDeck.add("cccc");
        initialDeck.add("cccc");
        //There are two copies of tile "bbbb"
        initialDeck.add("bbbb");
        initialDeck.add("bbbb");
        //There are two copies of tile "dacc"
        initialDeck.add("dacc");
        initialDeck.add("dacc");
        //There are two copies of tile "cdac"
        initialDeck.add("cdac");
        initialDeck.add("cdac");
        //There are two copies of tile "ccda"
        initialDeck.add("ccda");
        initialDeck.add("ccda");
        //There are two copies of tile "accd"
        initialDeck.add("accd");
        initialDeck.add("accd");
        //There are two copies of tile "dbba"
        initialDeck.add("dbba");
        initialDeck.add("dbba");
        //There are two copies of tile "adbb"
        initialDeck.add("adbb");
        initialDeck.add("adbb");
        //There are two copies of tile "badb"
        initialDeck.add("badb");
        initialDeck.add("badb");
        //There are two copies of tile "bbad"
        initialDeck.add("bbad");
        initialDeck.add("bbad");
        //There are two copies of tile "ddbc"
        initialDeck.add("ddbc");
        initialDeck.add("ddbc");
        //There are two copies of tile "cddb"
        initialDeck.add("cddb");
        initialDeck.add("cddb");
        //There are two copies of tile "bcdd"
        initialDeck.add("bcdd");
        initialDeck.add("bcdd");
        //There are two copies of tile "dbcd"
        initialDeck.add("dbcd");
        initialDeck.add("dbcd");
        //There are two copies of tile "adad"
        initialDeck.add("adad");
        initialDeck.add("adad");
        //There are two copies of tile "dada"
        initialDeck.add("dada");
        initialDeck.add("dada");
        //There are two copies of tile "dddd"
        initialDeck.add("dddd");
        initialDeck.add("dddd");
        return initialDeck;
    }

    /**
     * This method is to show the current deck depending on the
     * piece placement on the board and tile on the players' hand
     *
     * @param placementSequence
     * @param totalHands
     * @return {@code true} if the given String Placement has a
     * length of multiple 6, {@code false} otherwise
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
     * @author Jiawei Fan
     * this method will be used when a player draw a piece from the deck, it contains
     * a random function to randomly select a piece form the deck. It will firstly get
     * the current deck by removing the pieces that are placed on board or held on players'
     * hand from the initial hand. Then a random function will get a random index which
     * is the index of the selected piece in the current deck.
     *
     *
     * @param placementSequence
     * @param totalHands
     * @return A string which represents the tile type of the randomly selected tile
     */
    public static String drawFromCurrentDeck(String placementSequence, String totalHands )
    {
        ArrayList<String> currentDeck=getCurrentDeck(placementSequence,totalHands);//get the current deck
        Random random=new Random();//get a random object
        int index=random.nextInt(currentDeck.size());//call random method
        String selectedPiece=currentDeck.get(index);//get the selected piece depending on the random index
        return selectedPiece;
    }

}

