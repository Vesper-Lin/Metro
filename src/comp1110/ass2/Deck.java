package comp1110.ass2;

public class Deck{
    public PieceTypeAndNumber[] getInitialDeck()
    {
        PieceTypeAndNumber[] initialDeck=new PieceTypeAndNumber[24];
        initialDeck[0]=new PieceTypeAndNumber("aacb",4);
        initialDeck[1]=new PieceTypeAndNumber("cbaa",4);
        //will finish it later
        return initialDeck;
    }

    public PieceTypeAndNumber drawTile(PieceTypeAndNumber[] deck)
    {//haven't delete the 0 object
        String piece;
        int b=deck.length;
        double a=b*Math.random();
        int c= (int) Math.round(a);
        piece=deck[c].GetType();
        for (int i=0;i<24;i++)
        {
            if(deck[i].GetType().equals(piece)&&deck[i].GetNumber()>0)
            {
                deck[i].SetNumber();
                return deck[i];
            }
        }
        return deck[0];
    }
}
