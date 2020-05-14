package comp1110.ass2;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Board board = new Board();
        ArrayList<String> tilePlaced = new ArrayList<>();
        board.slice("cddb77baac37adad74aacb30dbba27badb03", tilePlaced);
        System.out.println(board.allHaveNeighbours(tilePlaced));
    }


}
