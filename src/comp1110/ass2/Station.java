package comp1110.ass2;

import java.util.HashMap;
import java.util.Map;

public class Station {
    //represents stations on board

    /**
     * This method will return a Map which contains the information about which stations
     * are belong to which player.
     * @author Jiawei Fan
     */
    public static Map<String,int[]> getInitialStationMap() {
        Map<String,int[]> playerStationMap =new HashMap<>();
        playerStationMap.put("21",new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31});
        playerStationMap.put("22",new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32});
        playerStationMap.put("31",new int[]{1, 4, 6, 11, 15, 20, 23, 24, 28, 31});
        playerStationMap.put("32",new int[]{2, 7, 9, 12, 14, 19, 22, 27, 29, 32});
        playerStationMap.put("33",new int[]{3, 5, 8, 10, 13, 18, 21, 24, 26, 30});
        playerStationMap.put("41",new int[]{4, 7, 11, 16, 20, 23, 27, 32});
        playerStationMap.put("42",new int[]{3, 8, 12, 15, 19, 24, 28, 31});
        playerStationMap.put("43",new int[]{1, 6, 10, 13, 18, 21, 25, 30});
        playerStationMap.put("44",new int[]{2, 5, 9, 14, 17, 22, 26, 29});
        playerStationMap.put("51",new int[]{1,5,10,14,22,28});
        playerStationMap.put("52",new int[]{6,12,18,23,27,32});
        playerStationMap.put("53",new int[]{3,7,15,19,25,29});
        playerStationMap.put("54",new int[]{2,9,13,21,26,30});
        playerStationMap.put("55",new int[]{4,8,11,20,24,31});
        playerStationMap.put("61",new int[]{1,5,10,19,27});
        playerStationMap.put("62",new int[]{2,11,18,25,29});
        playerStationMap.put("63",new int[]{4,8,14,21,26});
        playerStationMap.put("64",new int[]{6,15,20,24,31});
        playerStationMap.put("65",new int[]{3,9,13,23,30});
        playerStationMap.put("66",new int[]{7,12,22,28,32});
        return playerStationMap;
    }


}
