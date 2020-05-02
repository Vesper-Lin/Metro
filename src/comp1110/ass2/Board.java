package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class Board {



String piecePlacement;
    int stations;
    int centralStations;
    int position;
    int dimension;
    String placement;
    public void resetBoard(){
        //resets the board
    }


public boolean isValid(){
        //checks if position is valid and returns true and false
    return  false;
}
public String getPlacementSequenceString(){
        //returns placement sequence string
        return placement;
}
    public int getStation(){
        //returns the Station name; identify parameter may be Player name.
        return 0;
    }
public int allocateStation(Player player){
        //allocates Stations to a player.
    return 0;
}


public boolean getStatus(String placementSequence) {
/*    - No tile can overlap another tile, or any of the central stations.
            * - A tile cannot be placed next to one of the central squares unless it
            * continues or completes an existing track.
            * - If a tile is on an edge of the board, it cannot contain a track that
            * results in a station looping back to itself, UNLESS that tile could not
 *              have been placed elsewhere.
 * - If a tile is on a corner of the board, it cannot contain a track that
            * links the two stations adjacent to that corner, UNLESS that tile could
            * not have been placed elsewhere.
 */
    //check for positions
    boolean status=true;//declare a flag to track status
    boolean[][] board =new boolean[8][8];//declare the board as empty; false= empty.
    List<String> s = new ArrayList<String>();//declare a String list to save the long string as a array of placementstrings
    //split and save the placementSequence
    int i = 0;
    while (i < placementSequence.length()) {
        s.add(placementSequence.substring(i, i + 6));
        i = i + 6;
    }
    //declare a 2D array to record the positions in the placementSequence
   int[][] positions= new int[s.size()][2];
    //Declare an array to store tile types
   String[] tiles = new String[s.size()];
    int[] num = new int[s.size()];
    int z = 0;
    //Store the values into tile array
    for (String s1 : s) {
        if (z < s.size()) {
            tiles[z] = s1.substring(0, 4);
        }
        z=z+1;
    }

    int a=0;
    int num1;//variable to record the y co-ordinate
    int num2;//variable to record the x co-ordinate
    //split and save the numerics inside the placement sequence to positions array and check with the boolean matrix
    for(int b=0;b<s.size();b++){
        num1=Integer.parseInt(s.get(a).substring(4,5));
        num2= Integer.parseInt(s.get(a).substring(5,6));
        a++;
        for(int y1=0;y1<8;y1++){
            for(int x1=0;x1<8;x1++){
                //check only for elements which are present in placement sequence
                if(y1==num2&&x1==num1){
                    //check if the location already is occupied to avoid overlap
                    if(!board[y1][x1]){
                        board[y1][x1]=true;//set as occupied
                    }
                    else{
                        status=false;//return false if overlap
                    }
                }
            }
        }
        for(int y=0;y<2;y++){
            if(y==0){
                positions[b][y]= num1;
            }
            else{
                positions[b][y]= num2;
            }
        }
    }

    //Check for tile alignments



    return status;
}
}
