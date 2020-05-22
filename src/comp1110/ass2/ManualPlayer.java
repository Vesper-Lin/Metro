package comp1110.ass2;

public class ManualPlayer implements PlayerInt{
    private int playerNumber;
    private int[] stationsOwned;

    public ManualPlayer(int playerNumber, int[] stationsOwned){
        this.playerNumber = playerNumber;
        this.stationsOwned = stationsOwned;
    }
    public String manMeth(String placementSequence, String totalHands){
        String sr = Metro.drawFromDeck(placementSequence,totalHands);
        System.out.println(sr);
        System.out.println(Metro.generateMove("",sr,6));
        placementSequence = Metro.generateMove("",sr,6);
        return placementSequence;
    }

}
