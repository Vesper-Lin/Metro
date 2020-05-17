package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BoardTest {
    static int LENGTH_OF_PIECE = 6;

    public static void main(String[] args) {
        Board board = new Board();
        String placementSequence = "dddd03acba57bbbb06cccc67";
        //String placementSequence = "badb73";
        boolean isEmpty = false;
        ArrayList<String> tilePlaced = new ArrayList<>();
        if (board.isEmpty(placementSequence)) {
            //return true;
            isEmpty =true;

        }

        board.slice(placementSequence, tilePlaced);
        ArrayList<String>tiles = new ArrayList<>();
        ArrayList<String> positions = new ArrayList<>();

        board.getTiles(tilePlaced,tiles);
        board.getPositions(tilePlaced, positions);
        boolean overlap = board.checkOverlap(positions);
        boolean checkCS = board.checkCS(positions);
        boolean checkEdges = board.checkEdges(positions,tiles);
        boolean cornerCheck = board.cornerCheck(positions,tiles);
        boolean neighbours = board.allHaveNeighbours(tilePlaced);
        HashMap<String, Boolean> boardMap = board.getBoardMap(placementSequence);

        if(overlap==false && checkCS ==true && checkEdges == true && cornerCheck == true && neighbours == true){
            System.out.println("All is well");
        }
        else {
            System.out.println("overlap: "+overlap);
            System.out.println("checkCS: "+checkCS);
            System.out.println("checkEdges: "+checkEdges);
            System.out.println("cornerCheck: "+cornerCheck);
            System.out.println("neighbour:" +neighbours);
        }

    }

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
            else {
                finalStatus =true;
            }
        }
        return finalStatus;
    }


    public boolean checkEdges(ArrayList<String> positions, ArrayList<String> tilePlaced) {
        boolean edgeCheck = true;
        //create a map of the board with positions as key and tile on position as value
        HashMap<String,String> boardMap = getMap(positions,tilePlaced);
        //get the edge tiles
        ArrayList<String> edgeTiles = getEdgeTiles();

        //System.out.println(edgeTiles);
        //check if any tile is placed on the edge. If yes, check for loop back
        for (String pos:positions) {
            if (edgeTiles.contains(pos)) {
                String tile = boardMap.get(pos);
                if(pos.charAt(0)==0 && tile.charAt(0)=='d'){
                    if(!lengthCheck(positions)){
                        edgeCheck = false;
                        return edgeCheck;
                    }
                }
                else if(pos.charAt(1)==7 && tile.charAt(1)=='d'){
                    if(!lengthCheck(positions)){
                        edgeCheck = false;
                        return edgeCheck;
                    }
                }
                else if(pos.charAt(0)==7 && tile.charAt(2)=='d'){
                    if(!lengthCheck(positions)){
                        edgeCheck = false;
                        return edgeCheck;
                    }

                }
                else if(pos.charAt(1)==0 && tile.charAt(3)=='d'){
                    if(!lengthCheck(positions)){
                        edgeCheck = false;
                        return edgeCheck;
                    }
                }

            }

        }


        return edgeCheck;
    }
    public boolean cornerCheck(ArrayList<String> positions, ArrayList<String> tilePlaced) {
        boolean checkCorners = false;
        //create a map of the board with positions as key and tile on position as value
        HashMap<String,String> boardMap = getMap(positions,tilePlaced);
        ArrayList<String> corners = getCorners();

        for (String pos:positions) {
            if (corners.contains(pos)) {
                String tile = boardMap.get(pos);
                if(pos.equals("00") && (tile.charAt(0)=='c'||tile.charAt(3)=='b')){
                    checkCorners = lengthCheck(positions);
                    return checkCorners;
                }
                else if(pos.equals("07")&&(tile.charAt(0)=='b'||tile.charAt(1)=='c')){
                    checkCorners = lengthCheck(positions);
                    return checkCorners;
                }
                else if(pos.equals("70")&& (tile.charAt(2)=='b'||tile.charAt(3)=='c')){
                    checkCorners = lengthCheck(positions);
                    return checkCorners;
                }
                else if(pos.equals("77") && (tile.charAt(1)=='b'||tile.charAt(2)=='c')){
                    checkCorners = lengthCheck(positions);
                    return checkCorners;
                }
                else {
                    checkCorners=true;
                }

            }
            else{
                checkCorners=true;
            }
        }

        return checkCorners;
    }

    private boolean lengthCheck(ArrayList<String> positions){
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
        for(int i =0; i<positions.size();i++){
            boardMap.put(positions.get(i),tilePlaced.get(i));
        }
        return boardMap;
    }

    public static ArrayList<String> getEdgeTiles(){
        ArrayList<String> edgeTiles = new ArrayList<>();
        for (int i =1; i<7;i++){
            String s = "0"+ i;
            String s3 = i +"7";
            String s1 = "7"+ i;
            String s2 = i +"0";

            edgeTiles.add(s);
            edgeTiles.add(s1);
            edgeTiles.add(s2);
            edgeTiles.add(s3);
        }

        return edgeTiles;
    }

    //TODO: write the comment
    public void getTiles(ArrayList<String> tilePlaced, ArrayList<String> tiles) {
        for (String s:tilePlaced) {
            tiles.add(s.substring(0,4));
        }
    }

    public boolean allHaveNeighbours(ArrayList<String> tilePlaced)
    {

        for (String placement:tilePlaced)
        {
            boolean haveNeighbours=false;
            if (!(placement.substring(4,6).contains("7")||placement.substring(4,6).contains("0")))
            {
                for (String placement2:tilePlaced)
                {
                    if (Placement.isNeighbour(placement,placement2))
                    {
                        haveNeighbours=true;
                        break;
                    }
                }
            }
            else {
                haveNeighbours=true;
            }
            if (!haveNeighbours)
            {
                return false;
            }
        }
        return true;
    }
}

