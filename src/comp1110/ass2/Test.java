package comp1110.ass2;

import java.util.ArrayList;

public class Test {
    public static boolean isPlacementSequenceWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement sequence is well-formed
        Deck adeck=new Deck();
        Tile[] deck=adeck.getInitialDeck();
        System.out.println(deck[0].getNumber());
        return false;
    }

    public static void main(String[] args) {
        isPlacementSequenceWellFormed("11");
        ArrayList<Integer> a=new ArrayList<>();
        a.add(1);
        a.add(2);
        for (Integer e:a)
        {
            System.out.println(e);
        }
        System.out.println(isPlacementSequenceWellFormed("ccda01dada17cbcb04baac73ccda67aaaa74bcdd63accd37bbbb47bcbc46dacc40acba56acba75acba55adad45cbcb05bbad62dbcd65ddbc76cbaa11baac06bcdd53aaaa10cddb16acba72aacb64cdac26dacc41aaaa60baac02cdac66ddbc71dddd12cbcb27cbaa03adad54aaaa31dddd14dada50bcbc52aaaa25aacb42badb24baac61cbaa07aacb32adad13adad21dbcd23cddb36bbad51bbbb57adbb30accd35bcbc15aacb22aaaa77badb20cbaa70adbb00"));
        double d=10*Math.random();
        int c= (int) d;
        System.out.println(c);
        Tile[] f=new Tile[2];
        f[0]=new Tile("acca",5);
        f[1]=new Tile("aaab",5);
        Deck adeck= new Deck();
        System.out.println(adeck.isDrawnTile(f));

    }



}
