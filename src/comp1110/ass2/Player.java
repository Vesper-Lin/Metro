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

    /**
     * This method decides whether the two parameters, numberOfPlayer and playerNumber, within Player Class,
     * are valid or not.
     * numberOfPlayer should be between 2 and 6.
     * playerNumber should be between 1 and 6.
     * playerNumber is smaller or equal to numberOfPlayer.
     *
     * @author Yuxuan Lin
     */
    static boolean testPlayer(int numberOfPlayer, int playerNumber){
        if (numberOfPlayer>=2 && numberOfPlayer<=6){
            if (playerNumber>=1 && playerNumber<=6){
                if (playerNumber <= numberOfPlayer){
                    return true;
                }
            }
        }
        return false;
    }
}
