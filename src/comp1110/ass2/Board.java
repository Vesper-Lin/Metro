package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Board {
    static int LENGTH_OF_PIECE = 6;
    /**
     * This method will divide the long placement sequense in to piece placement,
     * which is represented by a string which has length of 6. These strings are stored
     * in ArrayList tilePlaced.
     *  </>The first four characters represent its type and the last two character represent
     * its row and column.
     * @author Ganaraj Rao
     * @param placementSequence:
     * @param tilePlaced:
     * @return none
     */
    public void slice(String placementSequence, ArrayList<String> tilePlaced) {
        String s1 = placementSequence;
        if (s1.length() <= LENGTH_OF_PIECE) {
            tilePlaced.add(s1);

        } else {
            tilePlaced.add(s1.substring(0, LENGTH_OF_PIECE));
            s1 = s1.substring(LENGTH_OF_PIECE);
            slice(s1, tilePlaced);
        }
    }

    /**
     * This method will extract the positions from the piece placement string,
     * which is represented by a string which has length of 2. These strings are stored
     * in ArrayList position
     *
     * @author Ganaraj Rao
     * @param tilePlaced:
     * @param positions:
     * @return none
     */

    public void getPositions(ArrayList<String> tilePlaced, ArrayList<String> positions) {
        for (String s : tilePlaced) {
            positions.add(s.substring(4, 6));
        }
    }
    /**
     * This method will check if tiles overlap among themselves or with
     * the central Stations.
     *
     * @author Ganaraj Rao
     * @param positions:
     * @return True is there is overlap; if no overlapping false.
     */
    public boolean checkOverlap(ArrayList<String> positions) {
        boolean overlap = false;
        HashSet<String> check = new HashSet<>();
        for (String s : positions) {
            if (!check.add(s)) {
                overlap = true;
            }
            if (positions.contains("33") || positions.contains("34") || positions.contains("43") || positions.contains("44")) {
                overlap = true;
            }
        }
        return overlap;
    }

    public boolean isEmpty(String placementSequence) {
        boolean isEmpty = false;
        if (placementSequence.length() == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }
    /**
     * This method checks if the tiles are placed next to the centrals stations.
     * If they are placed, they have a corresponding neighbor, which will continue the loop.
     *
     * @author Ganaraj Rao
     * @param positions:
     * @return True is there is neighbouring tile available; if no neighbouring tile is available returns false.
     */

    public boolean checkCS(ArrayList<String> positions) {
        boolean finalStatus = false;
        HashMap<String, String[]> border = new HashMap<>();
        border.put("23", new String[]{"22", "13"});
        border.put("24", new String[]{"14", "25"});
        border.put("35", new String[]{"25", "36"});
        border.put("45", new String[]{"46", "55"});
        border.put("54", new String[]{"55", "64"});
        border.put("53", new String[]{"63", "52"});
        border.put("42", new String[]{"52", "41"});
        border.put("32", new String[]{"31", "22"});

        for (String bT : border.keySet()) {
            if (positions.contains(bT)) {
                String[] borders = border.get(bT);
                for (String pos : positions) {
                    for (String s : borders) {
                        if (s.equals(pos)) {
                            finalStatus = true;
                            break;
                        }
                    }
                }
            }
        }
        return finalStatus;
    }
}

