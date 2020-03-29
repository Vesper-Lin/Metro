package comp1110.ass2;

public class Station {
    //represents stations on board
    private int numberOfPlayers;
    private int playerNumber;
    private int[] stationOwned;

    public Station(int numberOfPlayers, int playerNumber, int[] stationOwned) {
        this.numberOfPlayers = numberOfPlayers;
        this.playerNumber = playerNumber;
        this.stationOwned = stationOwned;
    }

    public Station[] getInitialStation() {
        Station[] InitialStation = new Station[20];
        InitialStation[0] = new Station(2, 1, new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31});
        InitialStation[1] = new Station(2, 2, new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32});
        InitialStation[2] = new Station(3, 1, new int[]{1, 4, 6, 11, 15, 20, 23, 24, 28, 31});
        InitialStation[3] = new Station(3, 2, new int[]{2, 7, 9, 12, 14, 19, 22, 27, 29, 32});
        InitialStation[4] = new Station(3, 3, new int[]{3, 5, 8, 10, 13, 18, 21, 24, 26, 30});
        InitialStation[5] = new Station(4, 1, new int[]{4, 7, 11, 16, 20, 23, 27, 32});
        InitialStation[6] = new Station(4, 2, new int[]{3, 8, 12, 15, 19, 24, 28, 31});
        InitialStation[7] = new Station(4, 3, new int[]{1, 6, 10, 13, 18, 21, 25, 30});
        InitialStation[8] = new Station(4, 4, new int[]{2, 5, 9, 14, 17, 22, 26, 29});
        InitialStation[9]=new Station(5,1, new int[]{1,5,10,14,22,28});
        InitialStation[10]=new Station(5,2, new int[]{6,12,18,23,27,32});
        InitialStation[11]=new Station(5,3, new int[]{3,7,15,19,25,29});
        InitialStation[12]=new Station(5,4, new int[]{2,9,13,21,26,30});
        InitialStation[13]=new Station(5,5, new int[]{4,8,11,20,24,31});
        InitialStation[14]=new Station(6,1, new int[]{1,5,10,19,27});
        InitialStation[15]=new Station(6,2, new int[]{2,11,18,25,29});
        InitialStation[16]=new Station(6,3, new int[]{4,8,14,21,26});
        InitialStation[17]=new Station(6,4, new int[]{6,15,20,24,31});
        InitialStation[18]=new Station(6,5, new int[]{3,9,13,23,30});
        InitialStation[19]=new Station(6,6, new int[]{7,12,22,28,32});
        return  InitialStation;
    }

    public int getNumberOfPlayers()
    {
        return numberOfPlayers;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    public int[] getStationOwned()
    {
        return stationOwned;
    }
}
