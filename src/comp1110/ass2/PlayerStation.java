package comp1110.ass2;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class extends Player, it has a new parameter, which is the stations that are
 * owned by this player.
 *
 * @author Jiawei Fan
 */
public class PlayerStation extends Player {
    private int[] stationOwned;

    public PlayerStation(int numberOfPlayer, int playerNumber, int[] stationOwned) {
        super(numberOfPlayer, playerNumber);
        this.stationOwned = stationOwned;
    }

    /**
     * This method is to get the stations' number that this palyer owns
     *
     * @return int[] which contains the stations' number that this palyer owns
     * @author Jiawei Fan
     */
    public int[] getStationOwned() {
        return stationOwned;
    }

    /**
     * This method will return an ArrayList of PlayerStation each contains one each players
     * information, including number of players in the game,player number and the stations owned
     * by this player by passing the number of players.
     *
     * @param numberOfPlayer an int representing the number of players
     * @return An ArrayList<PlayerStation> contains all player's info in the game
     */
    public static ArrayList<PlayerStation> getPlayerStationArrayList(int numberOfPlayer) {
        String playerKey1 = numberOfPlayer + "";//convert int to a String
        Map<String, int[]> stationMap = Station.getInitialStationMap();//get the stationmap
        ArrayList<PlayerStation> playerStations = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayer; i++) {
            //for each player,get the station owned information
            String playerKey2 = i + "";//convert int to a String
            String playerKey = playerKey1 + playerKey2;//for example "21" represents player 1 of a game played by two players
            int[] stationOwned = stationMap.get(playerKey);
            PlayerStation playerStation = new PlayerStation(numberOfPlayer, i, stationOwned);
            playerStations.add(playerStation);
        }
        return playerStations;
    }
}
