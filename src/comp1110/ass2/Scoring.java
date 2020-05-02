package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scoring {
    public static ArrayList<PlayerStationAndPlacement> getEachPlayerPlacement(int numberOfPlayer,String placement)
    {
        int numberOfPlacement=placement.length()/Placement.LENGTH_OF_ONE_PlACEMENT;
        int numberOfTurn=numberOfPlacement/numberOfPlayer;
        int remaindarPlacementNumber=numberOfPlacement%numberOfPlayer;
        ArrayList<PlayerStation> playerStations=PlayerStation.getPlayerStationArrayList(numberOfPlayer);
        ArrayList<PlayerStationAndPlacement> playerStationAndPlacements=new ArrayList<>();
        for (int i=0;i<numberOfPlayer;i++)
        {
            ArrayList<String> playerPlacement=new ArrayList<>();
            for (int j=0;j<numberOfTurn;j++)
            {
                playerPlacement.add(placement.substring(Placement.LENGTH_OF_ONE_PlACEMENT*numberOfPlayer*j+Placement.LENGTH_OF_ONE_PlACEMENT*i,
                        Placement.LENGTH_OF_ONE_PlACEMENT*numberOfPlayer*j+Placement.LENGTH_OF_ONE_PlACEMENT*i+6));
            }
            if (remaindarPlacementNumber>i)
            {
                playerPlacement.add(placement.substring(6*numberOfPlayer*numberOfTurn+Placement.LENGTH_OF_ONE_PlACEMENT*i));
            }
            PlayerStationAndPlacement a=new PlayerStationAndPlacement(numberOfPlayer,i,playerStations.get(i).getStationOwned(),playerPlacement);
            playerStationAndPlacements.add(a);
        }
        return playerStationAndPlacements;
    }

    /**
     * This method will determine if the one piecePlacement is from the edge
     * station or not. This method will be beneficial for scoring later because
     * scoring is from edge station.
     * @author Jiawei Fan
     * @param
     * @param
     * @return return true if the tile is placed from a edge station and it is
     *         placed by the owner of the station.
     *         return false if the tile is not connected to the edge station or
     *         it is not placed by the owener of the station
     */
    public static ArrayList<String> getStartTile(PlayerStationAndPlacement playerStationAndPlacement,String placement)
    {
        int[] stationOwned=playerStationAndPlacement.getStationOwned();
        ArrayList<String> startTile=new ArrayList<>();
        for (int stationNumber:stationOwned)
        {
            String stationCoordinate=StationNumber.fromStationNumber(stationNumber);
            for (int i=0;i<placement.length()/6;i++)
            {
                if (placement.substring(6*i+4,6*i+6).equals(stationCoordinate))
                {
                    startTile.add(placement.substring(6*i,6*i+6));
                    startTile.add(stationNumber+"");
                    break;
                }
            }
        }
        return startTile;
    }

    public static int getNextExit(String tile, int entry) {
        Map<Integer, Integer> exitEntryMap = new HashMap<>();
        for (int i = 0; i < Placement.LENGTH_OF_TILE_TYPE; i++) {
            if (tile.substring(i, i + 1).equals("a")) {
                int exit = (2 * i + 5) % 8;
                exitEntryMap.put(exit, 2 * i);
                exitEntryMap.put(2 * i, exit);
            }
            if (tile.substring(i, i + 1).equals("b")) {
                int exit = (2 * i + 3) % 8;
                exitEntryMap.put(exit, 2 * i);
                exitEntryMap.put(2 * i, exit);
            }
            if (tile.substring(i, i + 1).equals("c")) {
                int exit = 2 * i - 1;
                if (exit < 0) {
                    exit = exit + 8;
                }
                exitEntryMap.put(exit, 2 * i);
                exitEntryMap.put(2 * i, exit);
            }
            if (tile.substring(i, i + 1).equals("d")) {
                int exit = 2 * i + 1;
                exitEntryMap.put(exit, 2 * i);
                exitEntryMap.put(2 * i, exit);
            }
        }
        return exitEntryMap.get(entry);
    }

    public static TileEntryAndExit  findValidNeighbor(TileEntryAndExit tile,String placement)
    {
        int numberOfTiles=placement.length()/Placement.LENGTH_OF_ONE_PlACEMENT;
        for (int i=0;i<numberOfTiles;i++)
        {
            String testTile=placement.substring(Placement.LENGTH_OF_ONE_PlACEMENT*i,Placement.LENGTH_OF_ONE_PlACEMENT*i+Placement.LENGTH_OF_ONE_PlACEMENT);
            if (Placement.isNeighbour(tile.getPlacement(),testTile))
            {
               String direction=Placement.getNeighbourDirection(tile.getPlacement(),testTile);
               String nextDirection=ExitEntry.getNextDirection(tile.getExit());
               if (direction.equals(nextDirection))
               {
                   int nextEntry=ExitEntry.getNextEntry(tile.getExit());
                   int nextExit=getNextExit(testTile,nextEntry);
                   return new TileEntryAndExit(testTile,nextEntry,nextExit);
               }
            }
        }
        return null;
    }

    public static ArrayList<TileEntryAndExit> findRoute(int startStation,String placement,String startTile)
    {
        ArrayList<TileEntryAndExit> track=new ArrayList<>();
        int startEntry=-1;
        if (startStation>=1&&startStation<=8)
        {
            startEntry=0;
        }
        if (startStation>=9&&startStation<=16)
        {
            startEntry=6;
        }
        if (startStation>=17&&startStation<=24)
        {
            startEntry=4;
        }
        if (startStation>=25&&startStation<=32)
        {
            startEntry=2;
        }
        int nextExit=getNextExit(startTile,startEntry);
        TileEntryAndExit firstTile=new TileEntryAndExit(startTile,startEntry,nextExit);
        track.add(firstTile);
        TileEntryAndExit nextTile=findValidNeighbor(firstTile,placement);
        if (nextTile==null)
        {
            return track;
        }
        track.add(nextTile);
        while(true)
        {
            nextTile=findValidNeighbor(nextTile,placement);
            if (nextTile==null)
            {
                break;
            }
            track.add(nextTile);
        }
        return track;

    }
    public static boolean isNextToCentre(TileEntryAndExit tileEntryAndExit)
    {
        String tile=tileEntryAndExit.getPlacement();
        int row = Integer.parseInt(tile.substring(Placement.LENGTH_OF_TILE_TYPE, Placement.LENGTH_OF_TILE_TYPE+1));
        int col = Integer.parseInt(tile.substring(Placement.LENGTH_OF_TILE_TYPE+1, Placement.LENGTH_OF_TILE_TYPE+2));
        {
            if (row==3&&col==2&&(tileEntryAndExit.getExit()==2||tileEntryAndExit.getExit()==3)) return true;
            if (row==4&&col==2&&(tileEntryAndExit.getExit()==2||tileEntryAndExit.getExit()==3)) return true;
            if (row==5&&col==3&&(tileEntryAndExit.getExit()==0||tileEntryAndExit.getExit()==1)) return true;
            if (row==5&&col==4&&(tileEntryAndExit.getExit()==0||tileEntryAndExit.getExit()==1)) return true;
            if (row==4&&col==5&&(tileEntryAndExit.getExit()==6||tileEntryAndExit.getExit()==7)) return true;
            if (row==3&&col==5&&(tileEntryAndExit.getExit()==6||tileEntryAndExit.getExit()==7)) return true;
            if (row==2&&col==3&&(tileEntryAndExit.getExit()==5||tileEntryAndExit.getExit()==4)) return true;
            return row == 2 && col == 4 && (tileEntryAndExit.getExit() == 5 || tileEntryAndExit.getExit() == 4);
        }
    }

    public static boolean isNextToEdge(TileEntryAndExit tileEntryAndExit)
    {
        String tile=tileEntryAndExit.getPlacement();
        int row = Integer.parseInt(tile.substring(Placement.LENGTH_OF_TILE_TYPE, Placement.LENGTH_OF_TILE_TYPE+1));
        int col = Integer.parseInt(tile.substring(Placement.LENGTH_OF_TILE_TYPE+1, Placement.LENGTH_OF_TILE_TYPE+2));
        if (row==0&&tileEntryAndExit.getExit()==1)
        {
            return true;
        }
        if (col==0&&tileEntryAndExit.getExit()==7)
        {
            return true;
        }
        if (row==7&&tileEntryAndExit.getExit()==5)
        {
            return true;
        }
        return col == 7 && tileEntryAndExit.getExit() == 3;
    }

    public static int getScore(PlayerStationAndPlacement playerStationAndPlacement,String placement)
    {
        ArrayList<String> startTiles=getStartTile(playerStationAndPlacement,placement);
        int score = 0;
        for (String startTile:startTiles)
        {
            if (startTile.length()==6)
            {
                int index=startTiles.indexOf(startTile);
                int startStation=Integer.parseInt(startTiles.get(index+1));
                ArrayList<TileEntryAndExit> track=findRoute(startStation,placement,startTile);
                int plusScore = 0;
                if (track.size()==1)
                    if(isNextToEdge(track.get(0)))
                    {
                        plusScore=1;
                    }
                if (track.size()>1) {
                    int index1 = track.size();
                    TileEntryAndExit lastTile=track.get(index1-1);

                    if (isNextToCentre(lastTile))
                    {
                        plusScore=index1*2;
                    }
                    if (isNextToEdge(lastTile))
                    {
                        plusScore=index1;
                    }
                }
                score=score+plusScore;
            }
        }
        return score;
    }
}
