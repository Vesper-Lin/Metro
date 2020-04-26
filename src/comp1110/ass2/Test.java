package comp1110.ass2;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Test {
    public static boolean isPlacementSequenceWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement sequence is well-formed
        Deck adeck = new Deck();
        ArrayList a = adeck.getInitialDeck();

        return false;
    }

    public static void main(String[] args) {
        Deck adeck = new Deck();
        ArrayList a = adeck.getInitialDeck();
        System.out.println(a.size());
        a.remove("aacb");
        System.out.println(a.size());
        a.remove("aacb");
        System.out.println(a.size());
        a.remove("qweqeqwe");
        System.out.println(a.size());
        System.out.println(Placement.isPlacementLengthValid("aaaa11bbbb22"));
        System.out.println(Placement.isPlacementWellFormed("aaaa11bbbb22"));
        System.out.println(Placement.noMoreInstance("dddd11"));
        System.out.println(Placement.noMoreInstance("dddd11dddd22"));
        System.out.println(Placement.noMoreInstance("dddd11dddd22dddd33"));
        Random v=new Random();
        System.out.println(v.nextInt(2));
        System.out.println(Placement.isNeighbour("aaaa60","aaaa70"));
        String ab="cbcb05cbaa73bcdd57bbad63ccda04bcbc02badb17ddbc76aaaa07bbbb01dada66cdac53acba12";
        String ac="ccda04";
        System.out.println(Placement.isFromStartStation(ab,ac));

    }
}
