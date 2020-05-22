package comp1110.ass2;

public class ManualPlayer{
    private int playerNumber;
    private int[] stationsOwned;
    private int playerCount;

    public ManualPlayer(int playerNumber, int[] stationsOwned,int playerCount){
        this.playerNumber = playerNumber;
        this.stationsOwned = stationsOwned;
        this.playerCount=playerCount;
    }
    public String manMeth(String placementSequence, String totalHands){
        String sr = Metro.drawFromDeck(placementSequence,totalHands);
        System.out.println(sr);
        System.out.println(Metro.generateMove(placementSequence,sr,playerCount));
        placementSequence = Metro.generateMove(placementSequence,sr,playerCount);
        return placementSequence;
    }

}
