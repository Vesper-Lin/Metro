package comp1110.ass2;

public class Player {
    /**
     * this class contains a constructor,Player,which has two parameters,
     * the first one is the numebrOfPlayer and the second is the its number
     * For example,if the the game has two players and this player is player 1,
     * then the first parameter is 2 and the second parameter is 1.
     *
     * @author Jiawei Fan
     */

    private int numberOfPlayer;//number of players in a certain game
    private int playerNumber;//set private because it will be part of constructor later

    public Player(int numberOfPlayer, int playerNumber) {
        this.numberOfPlayer = numberOfPlayer;
        this.playerNumber = playerNumber;
    }
}
