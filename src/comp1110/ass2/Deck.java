package comp1110.ass2;

import java.util.ArrayList;

public class Deck{
    /** this method returns the initial state of the deck,all tiles are in the deck.
     *  it returns an array which contains TileTypeAndNumber,which shows the name
     *  and number of a certain tile in the deck
     */
    public Tile[] getInitialDeck()
    {
        Tile[] initialDeck=new Tile[24];
        initialDeck[0]=new Tile("aacb",4);
        initialDeck[1]=new Tile("cbaa",4);
        initialDeck[2]=new Tile("acba",4);
        initialDeck[3]=new Tile("baac",4);
        initialDeck[4]=new Tile("aaaa",4);
        initialDeck[5]=new Tile("cbcb",3);
        initialDeck[6]=new Tile("bcbc",3);
        initialDeck[7]=new Tile("cccc",2);
        initialDeck[8]=new Tile("bbbb",2);
        initialDeck[9]=new Tile("dacc",2);
        initialDeck[10]=new Tile("cdac",2);
        initialDeck[11]=new Tile("ccda",2);
        initialDeck[12]=new Tile("accd",2);
        initialDeck[13]=new Tile("dbba",2);
        initialDeck[14]=new Tile("adbb",2);
        initialDeck[15]=new Tile("badb",2);
        initialDeck[16]=new Tile("bbad",2);
        initialDeck[17]=new Tile("ddbc",2);
        initialDeck[18]=new Tile("cddb",2);
        initialDeck[19]=new Tile("bcdd",2);
        initialDeck[20]=new Tile("dbcd",2);
        initialDeck[21]=new Tile("adad",2);
        initialDeck[22]=new Tile("dada",2);
        initialDeck[23]=new Tile("dddd",2);
        return initialDeck;
    }

    /**
     * this method will be used when a player draw a tile from the deck, it contains
     * a random function to randomly select a tile form the deck. When a tile is selected,
     * setNumber() is used to decrease the number of the selected tile in deck by one
     *
     * Additionally, the parameter deck is a array of tiles representing the current
     * deck condition, which may not be the initial deck.
     *
     * This method will firstly convert ths Till[] into an ArrayList, the reason to do
     * that is in consideration of the later situation when the number of a specific
     * tile type is 0, then this tile type can be easily removed from the ArrayList.
     * Here, a certain type of tile which has 0 tile has not been removed yet. This method
     * only select the tile which has number greater than 0;
     *
     *
     * @param deck
     * @return A string which represents the tile type of the randomly selected tile
     */
    public String isDrawnTile(Tile[] deck)
    {
        ArrayList<Tile> adeck=new ArrayList<>();
        for (Tile f:deck)
        {
            adeck.add(f);
        }
        int b=adeck.size();
        int flag=1;
        while (true)
        {
            double a=b*Math.random();
            int c= (int) a;
            if (adeck.get(c).getNumber()>0) //only select a certain type of tile if its number is greater than 0
            {
                String tile;
                tile = adeck.get(c).getType();
                return tile;
            }
    }
    }}

