package comp1110.ass2;

import java.util.ArrayList;
import java.util.Map;

public class PlayerStation extends Player {
    private int[] stationOwned;
    public PlayerStation(int numberOfPlayer, int playerNumber,int[] stationOwned) {
        super(numberOfPlayer, playerNumber);
        this.stationOwned=stationOwned;
    }

    public int[] getStationOwned()
    {
        return stationOwned;
    }

    public static ArrayList<PlayerStation> getPlayerStationArrayList(int numberOfPlayer)
    {
        String playerKey1=numberOfPlayer+"";
        Map<String,int[]> stationMap=Station.getInitialStationMap();
        ArrayList<PlayerStation> playerStations=new ArrayList<>();
        for (int i=1;i<=numberOfPlayer;i++)
        {
            String playerKey2=i+"";
            String playerKey=playerKey1+playerKey2;
            int[]stationOwned =stationMap.get(playerKey);
            PlayerStation playerStation=new PlayerStation(numberOfPlayer,i,stationOwned);
            playerStations.add(playerStation);
        }
        return playerStations;
    }
}
