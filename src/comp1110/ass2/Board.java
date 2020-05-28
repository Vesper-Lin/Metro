package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Board {
    static int LENGTH_OF_PIECE = 6;
    static int TOTAL_ROW = 8;
    static int TOTAL_COL = 8;

    /**
     * This method will divide the long placement sequense in to piece placement,
     * which is represented by a string which has length of 6. These strings are stored
     * in ArrayList tilePlaced.
     * </>The first four characters represent its type and the last two character represent
     * its row and column.
     *
     * @param placementSequence:
     * @param tilePlaced:
     * @return none
     * @author Ganaraj Rao
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
     * @param tilePlaced:
     * @param positions:
     * @return none
     * @author Ganaraj Rao
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
     * @param positions:
     * @return True is there is overlap; if no overlapping false.
     * @author Ganaraj Rao
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
     * @return True is there is neighbouring tile available; if no neighbouring tile is available returns false.
     * @author Ganaraj Rao
     * <p>
     * param positions:
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
            } else {
                finalStatus = true;
            }
        }
        return finalStatus;
    }


    public boolean checkEdges(ArrayList<String> positions, ArrayList<String> tilePlaced) {
        boolean edgeCheck = true;
        //create a map of the board with positions as key and tile on position as value
        HashMap<String, String> boardMap = getMap(positions, tilePlaced);
        //get the edge tiles
        ArrayList<String> edgeTiles = getEdgeTiles();

        //System.out.println(edgeTiles);
        //check if any tile is placed on the edge. If yes, check for loop back
        int tileNumber = 0;
        for (String pos : positions) {
            if (edgeTiles.contains(pos)) {
                String tile = boardMap.get(pos);
                if (tile.equals("dddd") && tileNumber == 0) {
                    edgeCheck = true;
                } else if (pos.charAt(0) == '0' && tile.charAt(0) == 'd') {
                    if (!lengthCheck(positions)) {
                        edgeCheck = false;
                        return edgeCheck;
                    }
                } else if (pos.charAt(1) == '7' && tile.charAt(1) == 'd') {
                    if (!lengthCheck(positions)) {
                        return false;
                    }
                } else if (pos.charAt(0) == '7' && tile.charAt(2) == 'd') {
                    if (!lengthCheck(positions)) {
                        edgeCheck = false;
                        return edgeCheck;
                    }


                } else if (pos.charAt(1) == '0' && tile.charAt(3) == 'd') {
                    if (!lengthCheck(positions)) {
                        edgeCheck = false;
                        return edgeCheck;
                    }
                }

            }
            tileNumber++;
        }


        return edgeCheck;
    }

    public boolean cornerCheck(ArrayList<String> positions, ArrayList<String> tilePlaced) {
        //create a map of the board with positions as key and tile on position as value
        HashMap<String, String> boardMap = getMap(positions, tilePlaced);
        ArrayList<String> corners = getCorners();
        if (tilePlaced.size() == 60) {
            return true;
        }
        for (String pos : positions) {
            if (corners.contains(pos)) {
                String tile = boardMap.get(pos);
                if (pos.equals("00") && (tile.charAt(0) == 'c' || tile.charAt(3) == 'b' || tile.charAt(0) == 'd' || tile.charAt(3) == 'd')) {
                    return false;
                } else if (pos.equals("07") && (tile.charAt(0) == 'b' || tile.charAt(1) == 'c' || tile.charAt(0) == 'd' || tile.charAt(1) == 'd')) {
                    return false;
                } else if (pos.equals("70") && (tile.charAt(2) == 'b' || tile.charAt(3) == 'c' || tile.charAt(2) == 'd' || tile.charAt(3) == 'd')) {
                    return false;
                } else if (pos.equals("77") && (tile.charAt(1) == 'b' || tile.charAt(2) == 'c' || tile.charAt(1) == 'd' || tile.charAt(2) == 'd')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean lengthCheck(ArrayList<String> positions) {
        return positions.size() == 60;

    }

    private ArrayList<String> getCorners() {
        ArrayList<String> corners = new ArrayList<>();
        corners.add("00");
        corners.add("07");
        corners.add("77");
        corners.add("71");
        return corners;

    }

    private HashMap<String, String> getMap(ArrayList<String> positions, ArrayList<String> tilePlaced) {
        HashMap<String, String> boardMap = new HashMap<>();
        for (int i = 0; i < positions.size(); i++) {
            boardMap.put(positions.get(i), tilePlaced.get(i));
        }
        return boardMap;
    }

    public static ArrayList<String> getEdgeTiles() {
        ArrayList<String> edgeTiles = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            String s = "0" + i;
            String s3 = i + "7";
            String s1 = "7" + i;
            String s2 = i + "0";

            edgeTiles.add(s);
            edgeTiles.add(s1);
            edgeTiles.add(s2);
            edgeTiles.add(s3);
        }

        return edgeTiles;
    }

    //TODO: write the comment
    public void getTiles(ArrayList<String> tilePlaced, ArrayList<String> tiles) {
        for (String s : tilePlaced) {
            tiles.add(s.substring(0, 4));
        }
    }


    /**
     * This method is to check each tile in the placement has a neighbor unless
     * it is connected to the edge station.
     *
     * @param tilePlaced A ArrayList contaning the tile that has been placed
     * @return true if all tiles on board have neighbors
     * otherwise, return false
     * @ Jiawei Fan
     */
    public boolean allHaveNeighbours(ArrayList<String> tilePlaced) {

        for (String placement : tilePlaced) {
            boolean haveNeighbours = false;
            if (!(placement.substring(4, 6).contains("7") || placement.substring(4, 6).contains("0"))) {//find the placement that is connected to edge station
                for (String placement2 : tilePlaced) {//iterate through all tiles find if test piece has neightbors
                    if (Placement.isNeighbour(placement, placement2)) {
                        haveNeighbours = true;
                        break;
                    }
                }
            } else {
                haveNeighbours = true;
            }
            if (!haveNeighbours) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, Boolean> getBoardMap(String placementSequence) {
        ArrayList<String> tilePlaced = new ArrayList<>();
        ArrayList<String> positions = new ArrayList<>();
        slice(placementSequence, tilePlaced);
        getPositions(tilePlaced, positions);
        HashMap<String, Boolean> boardMap = new HashMap<>();
        for (int row = 0; row < TOTAL_ROW; row++) {
            for (int col = 0; col < TOTAL_COL; col++) {
                String s = row + String.valueOf(col);
                boardMap.put(s, false);
            }
        }
        for (String pos : positions) {
            boardMap.put(pos, true);
        }
        return boardMap;
    }
}

