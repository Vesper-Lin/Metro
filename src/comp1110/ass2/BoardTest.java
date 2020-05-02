package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class BoardTest {
    Tile tile;
    public BoardTest(Tile tile){
        this.tile = tile;
    }


    public static void main(String[] args) {
        String placementSequence="ccda01dada17cbcb04baac73ccda67aaaa74bcdd63accd37bbbb47bcbc46dacc40acba56acba75acba55cccc45cbcb05bbad62dbcd65ddbc76cbaa11baac06bcdd53dbba10cddb16acba72aacb64cdac26dacc41dbba60baac02cdac66ddbc71dddd12cbcb27cbaa03cccc54aaaa31dddd14dada50bcbc52aaaa25aacb42badb24baac61cbaa07aacb32adad13adad21dbcd23cddb36bbad51bbbb57adbb30accd35bcbc15aacb22aaaa77badb20cbaa70adbb00";

        List<String> s = new ArrayList<>();

//check for positions
        boolean status=true;//declare a flag to track status
        boolean[][] board =new boolean[8][8];//declare the board as empty; false= empty.
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
                        if(!board[y1][x1]&&(board[y1-1][x1]==true||board[y1][x1-1]==true)){
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


    }
}
