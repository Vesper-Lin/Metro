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
        boolean[][] board =new boolean[8][8];
        List<String> s = new ArrayList<String>();


        int i = 0;
        while (i < placementSequence.length()) {
            s.add(placementSequence.substring(i, i + 6));
            i = i + 6;
        }
        int positions [][];
        positions= new int[s.size()][2];
        String[] tiles = new String[s.size()];
        int[] num = new int[s.size()];
        int z = 0;
        for (String s1 : s) {
            if (z < s.size()) {

                tiles[z] = s1.substring(0, 4);

            }
            z=z+1;
        }
        int a=0;
        int num1;
        int num2;
        for(int b=0;b<s.size();b++){
            num1=Integer.parseInt(s.get(a).substring(4,5));
            num2= Integer.parseInt(s.get(a).substring(5,6));
            a++;
            for(int y1=0;y1<8;y1++){
                for(int x1=0;x1<8;x1++){
                    if(y1==num2&&x1==num1){
                        if(board[y1][x1]==false){
                            board[y1][x1]=true;
                        }
                        else{
                            System.out.println("fraud");;
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

        for(int i1=0;i1<8;i1++){
            for(int j1=0;j1<8;j1++){
                System.out.print(board[i1][j1]+" ");
            }
            System.out.println();
        }
    }
}
