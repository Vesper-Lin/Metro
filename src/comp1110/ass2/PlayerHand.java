package comp1110.ass2;

public class PlayerHand extends Player {
    private String tileInHand;
    public PlayerHand(int numberOfPlayer, int playerNumber,String tileInHand) {
        super(numberOfPlayer, playerNumber);
        this.tileInHand=tileInHand;
    }

    public String getTileInHand() {
        return tileInHand;
    }
}
