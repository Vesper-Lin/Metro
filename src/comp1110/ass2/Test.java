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
        String ab="bcbc02cbcb67bcdd66cbaa17ddbc12ccda03dbcd37badb16cccc13dada65bbbb11aacb06dacc21dada36adbb22baac75acba04aaaa15cbaa23cdac05dddd24aacb27baac55bcbc32badb47acba26accd73bbbb45bbad64aaaa20cddb25aacb07cbcb30adad01aaaa00acba10cdac60dacc72ccda14dbba35cccc62accd71cbaa63baac56acba77cddb61dbcd54cbaa31bbad76cbcb74adad52baac51adbb42ddbc40dddd46dbba53bcbc41aacb57bcdd50aaaa70";
        //String ax="ccda57aaaa50cccc05cbcb10cddb04dbba40ddbc30bbad01dacc76baac20bcdd67cdac74accd03bbbb73bcbc47cdac15cbaa66acba41aacb13dbcd23adad22ddbc65badb32cbcb25dada14dada64acba11dbcd16cbaa21aaaa72accd24dddd55adbb26baac02aacb75adad71";
        ArrayList<PlayerStationAndPlacement> fj=Scoring.getEachPlayerPlacement(3,ab);
        PlayerStationAndPlacement fjw=fj.get(1);
        System.out.println(Scoring.findRoute(26,ab,"accd67").size());
        System.out.println(Scoring.getScore(fjw,ab));
        System.out.println(Scoring.isNextToEdge(new TileEntryAndExit("cccc57",4,3)));
    }
}
