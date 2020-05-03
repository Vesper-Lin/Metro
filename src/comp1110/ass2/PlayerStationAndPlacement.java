package comp1110.ass2;

import java.util.ArrayList;

/**
 * This class extends PlayerStation, one more parameter added is the playerPlacement
 * It may look a bit redundant, i think so as well. But the initial reason for making
 * this class is to store the placement information of each player, which maybe useful
 * in the later game. If it is not useful, will delete it later.
 *
 * @author Jiawei Fan
 */
public class PlayerStationAndPlacement extends PlayerStation {
    private ArrayList<String> playerPlacement;

    public PlayerStationAndPlacement(int numberOfPlayer, int playerNumber, int[] stationOwned, ArrayList<String> playerPlacement) {
        super(numberOfPlayer, playerNumber, stationOwned);
        this.playerPlacement = playerPlacement;
    }

}
