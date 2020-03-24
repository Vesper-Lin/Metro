package comp1110.ass2;

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

    /** this method will be used when a player draw a tile from the deck, it contains
     * a random function to randomly select a tile form the deck. When a tile is selected,
     * setNumber() is used to decrease the number of the selected tile in deck by one
     */
    public Tile isDrawnTile(Tile[] deck)
    {
        String tile;
        int b=deck.length;
        double a=b*Math.random();
        int c= (int) Math.round(a);
        tile=deck[c].getType();
        for (int i=0;i<24;i++)
        {
            if(deck[i].getType().equals(tile)&&deck[i].getNumber()>0)
            {
                deck[i].setNumber();
                return deck[i];
            }
        }
        return deck[0];
    }
}
