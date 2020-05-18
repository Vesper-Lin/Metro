package comp1110.ass2;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        Board board = new Board();
        ArrayList<String> tilePlaced = new ArrayList<>();
        board.slice("cddb77baac37adad74aacb30dbba27badb03", tilePlaced);
        System.out.println(board.allHaveNeighbours(tilePlaced));

        System.out.println(Placement.getSpareStartStation("aaaa07aaaa06", 6));
        System.out.println(Station.getBoardCoordinates());
        String a = "1111";
        System.out.println(Placement.getValidMovePlace("aaaa00", "aaaa", 3));
        System.out.println(Placement.isLoopBacktoEdge("cccd10"));
        System.out.println(Placement.hasNeighbor("cccc24", "aaaa11bbbb23"));
        System.out.println(Placement.isNeighbour("bbbb23", "cccc24"));
        System.out.println(Placement.isSimpleValid("aaaa10bbbb11", "dddd12"));
        System.out.println(Placement.getSpareStartStation("aaaa01bbbb02", 2));
        //getValidMovePlace
        System.out.println(Placement.getValidMovePlace("", "cccc", 2));
        System.out.println(Placement.getFinalValidPlace("", "cccc", 2));
        //bbbb03dddd13accd04dbcd75adbb02bcbc60cbaa76aacb06dbba16cddb14aaaa17adad61cbcb05ccda51
        //cccc77
    }


}
