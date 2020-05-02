package comp1110.ass2;

import java.util.ArrayList;

public class PlayerStationAndPlacement extends PlayerStation {
    private ArrayList<String> playerPlacement;
    public PlayerStationAndPlacement(int numberOfPlayer, int playerNumber, int[] stationOwned, ArrayList<String> playerPlacement) {
        super(numberOfPlayer, playerNumber, stationOwned);
        this.playerPlacement=playerPlacement;
    }

    public ArrayList<String> getPlayerPlacement() {
        return playerPlacement;
    }

    public ArrayList<String> setPlayerPlacement(String placement)
    {
        playerPlacement.add(placement);
        return playerPlacement;
    }
}
