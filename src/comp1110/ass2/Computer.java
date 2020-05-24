package comp1110.ass2;

public class Computer {
    private int playerNumber;
    private int[] stationsOwned;
    private int playerCount;

    public Computer(int playerNumber, int[] stationsOwned,int playerCount){
        this.playerNumber = playerNumber;
        this.stationsOwned = stationsOwned;
        this.playerCount = playerCount;
    }
    public String compMeth(String placementSequence, String totalHands){

        String sr = Metro.drawFromDeck(placementSequence,totalHands);
        //System.out.println(sr);
        //System.out.println(Metro.generateMove("",sr,playerCount));
        String move = Metro.generateMove(placementSequence,sr,playerCount);
        placementSequence = placementSequence+move;
        return placementSequence;

    }
}
