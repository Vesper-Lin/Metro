package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class includes many methods that will be used in Task 7 which is about getting
 * the scores of each player in the game.
 *
 * @author Jiawei Fan
 */
public class Scoring {

    public static final int NUMBER_OF_EXIT_FOR_EACH_TILE = 8;

    /**
     * This is a method that i think maybe a bit redundant at this stage as mentioned in PlayerStationAndPlacement
     * class, but basically, this method is going to get each player's information including number of player in
     * the game,player number,the staions the player owns and the placement the player has already placed. One another
     * reason to put players' placements in is based on a pizza message by the tutor saying that when calculating
     * scores, the tile should put in the start station by the player who owns the station. But Task 7 test failed if
     * consider like this, passed if not consider this. I will keep this method here at this stage.
     *
     * @param numberOfPlayer an int represents the number of players in the game
     * @param placement      a long string representing placement of tiles on the board
     * @return ArrayList<PlayerStationAndPlacement> connstains all players' information
     * @author Jiawei Fan
     */
    public static ArrayList<PlayerStationAndPlacement> getEachPlayerPlacement(int numberOfPlayer, String placement) {
        int numberOfPlacement = placement.length() / Placement.LENGTH_OF_ONE_PlACEMENT;//number of placements already placed
        int numberOfTurn = numberOfPlacement / numberOfPlayer;//number of turns each player place one tile
        int remaindarPlacementNumber = numberOfPlacement % numberOfPlayer;//extra placements
        ArrayList<PlayerStation> playerStations = PlayerStation.getPlayerStationArrayList(numberOfPlayer);
        ArrayList<PlayerStationAndPlacement> playerStationAndPlacements = new ArrayList<>();
        for (int i = 0; i < numberOfPlayer; i++) {
            ArrayList<String> playerPlacement = new ArrayList<>();
            for (int j = 0; j < numberOfTurn; j++) {
                playerPlacement.add(placement.substring(Placement.LENGTH_OF_ONE_PlACEMENT * numberOfPlayer * j + Placement.LENGTH_OF_ONE_PlACEMENT * i,
                        Placement.LENGTH_OF_ONE_PlACEMENT * numberOfPlayer * j + Placement.LENGTH_OF_ONE_PlACEMENT * i + Placement.LENGTH_OF_ONE_PlACEMENT));//get the placement of each player in each turn
            }
            if (remaindarPlacementNumber > i) {//get the extra placement which is not included in each turn
                playerPlacement.add(placement.substring(Placement.LENGTH_OF_ONE_PlACEMENT * numberOfPlayer * numberOfTurn + Placement.LENGTH_OF_ONE_PlACEMENT * i));
            }
            PlayerStationAndPlacement a = new PlayerStationAndPlacement(numberOfPlayer, i, playerStations.get(i).getStationOwned(), playerPlacement);
            playerStationAndPlacements.add(a);
        }
        return playerStationAndPlacements;
    }

    /**
     * This method will determine if the one tile is next to the edge of start station
     * if it is next to the edge sttion, it will be counted as a start tile. If the start
     * station next to the tile is owned by this player, count it as a start tile and
     * put in the arraylist.
     *
     * @param placement                 a long string representing the placement on the board
     * @param playerStationAndPlacement contains one players information including number including number of player in
     *                                  the game,player number,the staions the player owns and the placement the player has already placed.
     * @return an ArrayList<String> which contains all the start tiles of this player
     * @author Jiawei Fan
     */
    public static ArrayList<String> getStartTile(PlayerStationAndPlacement playerStationAndPlacement, String placement) {
        int[] stationOwned = playerStationAndPlacement.getStationOwned();
        ArrayList<String> startTile = new ArrayList<>();
        for (int stationNumber : stationOwned) {
            String stationCoordinate = StationNumber.fromStationNumber(stationNumber);//get coordinates of the spots on board which is next to the tested station
            for (int i = 0; i < placement.length() / Placement.LENGTH_OF_ONE_PlACEMENT; i++) {//test each tile that has been placed on the board
                if (placement.substring(Placement.LENGTH_OF_ONE_PlACEMENT * i + Placement.LENGTH_OF_TILE_TYPE, Placement.LENGTH_OF_ONE_PlACEMENT * i + Placement.LENGTH_OF_ONE_PlACEMENT).equals(stationCoordinate)) {
                    //if find a coordinate of a tile on board is placed on the spot next to the start station,get this tile as a start tile
                    startTile.add(placement.substring(Placement.LENGTH_OF_ONE_PlACEMENT * i, Placement.LENGTH_OF_ONE_PlACEMENT * i + Placement.LENGTH_OF_ONE_PlACEMENT));
                    startTile.add(stationNumber + "");//add station number after it to show this start tile is from this station
                    break;
                }
            }
        }
        return startTile;
    }

    /**
     * Given a tile,if the entry of the track is given, there will be only
     * one exit.
     * *              0    1
     * *            7 tile2(N)2
     * *            6         3
     * *               5   4
     * for example, if the known entry is 0, if the first character of the
     * string is 'a' ,which represents a straight track, the exit will be 0.
     * In this method, for a certain type of tile, all the exit entry pair
     * are considered. Then choose the specif pair from the exit entry map
     * by passing in the entry.
     *
     * @param tile  a 6-length long string representing the tile
     * @param entry a int representing the entry
     * @return a int representing the exit of the tile.
     * @author Jiawei Fan
     */
    public static int getNextExit(String tile, int entry) {
        Map<Integer, Integer> exitEntryMap = new HashMap<>();
        for (int i = 0; i < Placement.LENGTH_OF_TILE_TYPE; i++) {
            if (tile.substring(i, i + 1).equals("a")) {//firtly test type 'a',which means straight track
                int exit = (2 * i + 5) % NUMBER_OF_EXIT_FOR_EACH_TILE;//times 2 because i=0,1,2,3 represents exit 0,2,4,6,
                //+5 because it goes straight, difference should be 5
                exitEntryMap.put(exit, 2 * i);//put the both in order and reversed order in because later the entry may be odd exit,for examle.0 to 5,next time it can be 5 to 0
                exitEntryMap.put(2 * i, exit);//another reason including the reversed order is here we only use even exit to get the next exit, which is even to odd. Odd to even should also be considered.
            }
            if (tile.substring(i, i + 1).equals("b")) {
                int exit = (2 * i + 3) % NUMBER_OF_EXIT_FOR_EACH_TILE;
                exitEntryMap.put(exit, 2 * i);
                exitEntryMap.put(2 * i, exit);
            }
            if (tile.substring(i, i + 1).equals("c")) {
                int exit = 2 * i - 1;
                if (exit < 0) {
                    exit = exit + NUMBER_OF_EXIT_FOR_EACH_TILE;
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

    /**
     * This method will find the valid neightbor to the current tile. The number of neighbor will only
     * be one because there is one track. Once find the neighbor, the exit and entry of the neighbor
     * will also be founded.
     *
     * @param tile      the tile to be tested, it is composed of its placement,exit and entry. exit and entry shows the track
     * @param placement a long string representing the placement on board
     * @return TileEntryAndExit which shows the nerighbor's placement,exit and entry. exit and entry shows the track
     * @author Jiawei Fan
     */
    public static TileEntryAndExit findValidNeighbor(TileEntryAndExit tile, String placement) {
        int numberOfTiles = placement.length() / Placement.LENGTH_OF_ONE_PlACEMENT;
        for (int i = 0; i < numberOfTiles; i++) {//test each tile in the placement
            String testTile = placement.substring(Placement.LENGTH_OF_ONE_PlACEMENT * i, Placement.LENGTH_OF_ONE_PlACEMENT * i + Placement.LENGTH_OF_ONE_PlACEMENT);//get the placement of the tested tile
            if (Placement.isNeighbour(tile.getPlacement(), testTile)) {//if a neighbor is found
                String direction = Placement.getNeighbourDirection(tile.getPlacement(), testTile);//get the direction of the neighbor to the current tile by looking at their coordinates
                String nextDirection = ExitEntry.getNextDirection(tile.getExit());//get the next direction of the current track by looking at the exit of the current tile
                if (direction.equals(nextDirection)) {//if the direction of neighbor matches with the track direction,the neighbor is valid neighbor
                    int nextEntry = ExitEntry.getNextEntry(tile.getExit());//get entry number of the next tile
                    int nextExit = getNextExit(testTile, nextEntry);//get exit number of the next tile
                    return new TileEntryAndExit(testTile, nextExit);
                }
            }
        }
        return null;//if no neighbor is found, return null
    }

    /**
     * This method is to find a route/track for a spesific start tile based on the current placement
     *
     * @param startStation int representing the startStation number
     * @param placement    a long String representing the placement of tiles on board
     * @param startTile    a String representing the specific start tile
     * @return ArrayList<TileEntryAndExit> which contains all tile entry and exit information in the track
     * @author Jiawei Fan
     */
    public static ArrayList<TileEntryAndExit> findRoute(int startStation, String placement, String startTile) {
        ArrayList<TileEntryAndExit> track = new ArrayList<>();
        int startEntry = -1;//initialize with -1
        if (startStation >= 1 && startStation <= 8) {
            //for station 1 to 8 on the top, the entry of start tile will always be 0
            startEntry = 0;
        }
        if (startStation >= 9 && startStation <= 16) {
            //for station 9 to 16 on the left, the entry of start tile will always be 6
            startEntry = 6;
        }
        if (startStation >= 17 && startStation <= 24) {
            //for station 17 to 24 on the bottom, the entry of start tile will always be 4
            startEntry = 4;
        }
        if (startStation >= 25 && startStation <= 32) {
            //for station 25 to 32 on the right, the entry of start tile will always be 2
            startEntry = 2;
        }
        int nextExit = getNextExit(startTile, startEntry);//get exit of start tile
        TileEntryAndExit firstTile = new TileEntryAndExit(startTile, nextExit);//store the placement,entry,exit information of start tile in a TileEntryAndExit
        track.add(firstTile);//add start tile to the Track
        TileEntryAndExit nextTile = findValidNeighbor(firstTile, placement);//find next neighbor
        if (nextTile == null) {
            //if can not find neighbor for the start tile return the track, which is of size one
            return track;
        }
        track.add(nextTile);//add next tile to track
        while (true) {
            //keep finding neighbors until can not find more
            nextTile = findValidNeighbor(nextTile, placement);
            if (nextTile == null) {
                break;
            }
            track.add(nextTile);
        }
        return track;

    }

    /**
     * This method will check if a specific tile is next to center station or not
     * a tile which is connected to the center station needs two requirement.
     * 1.the tile is next to the center station
     * 2.the exit direction of the tile matches with the entry to the center station
     *
     * @param tileEntryAndExit a tileEntryAndExit contains information about the tile including
     *                         placement,entry and exit of the track.
     * @return true if the tile meets two requirent
     * otherwise,return false
     * @author Jiawei Fan
     */
    public static boolean isNextToCentre(TileEntryAndExit tileEntryAndExit) {
        String tile = tileEntryAndExit.getPlacement();
        int row = Integer.parseInt(tile.substring(Placement.LENGTH_OF_TILE_TYPE, Placement.LENGTH_OF_TILE_TYPE + 1));//get coordinate of row of the tile
        int col = Integer.parseInt(tile.substring(Placement.LENGTH_OF_TILE_TYPE + 1, Placement.LENGTH_OF_TILE_TYPE + 2));//get coordinate of column of the tile
        {
            if (row == 3 && col == 2 && (tileEntryAndExit.getExit() == 2 || tileEntryAndExit.getExit() == 3))//for the top left center station,exit can be 2 or 3 for tiles on (3,2)
                return true;
            if (row == 4 && col == 2 && (tileEntryAndExit.getExit() == 2 || tileEntryAndExit.getExit() == 3))//for the bottom left center station,exit can be 2 or 3 for tiles on (4,3)
                return true;
            if (row == 5 && col == 3 && (tileEntryAndExit.getExit() == 0 || tileEntryAndExit.getExit() == 1))//for the bottom right center station,exit can be 0 or 1 for tiles on (5,3)
                return true;
            if (row == 5 && col == 4 && (tileEntryAndExit.getExit() == 0 || tileEntryAndExit.getExit() == 1))//for the bottom right center station,exit can be 0 or 1 for tiles on (5,4)
                return true;
            if (row == 4 && col == 5 && (tileEntryAndExit.getExit() == 6 || tileEntryAndExit.getExit() == 7))//for the bottom right center station,exit can be 6 or 7 for tiles on (4,5)
                return true;
            if (row == 3 && col == 5 && (tileEntryAndExit.getExit() == 6 || tileEntryAndExit.getExit() == 7))//for the top right center station,exit can be 6 or 7 for tiles on (3,5)
                return true;
            if (row == 2 && col == 3 && (tileEntryAndExit.getExit() == 5 || tileEntryAndExit.getExit() == 4))//for the top left center station,exit can be 5 or 4 for tiles on (2,3)
                return true;
            return row == 2 && col == 4 && (tileEntryAndExit.getExit() == 5 || tileEntryAndExit.getExit() == 4);//for the top right center station,exit can be 5 or 4 for tiles on (2,3)
        }
    }

    /**
     * This method will check if a tile contains a track back to a edge station
     *
     * @param tileEntryAndExit a tileEntryAndExit contains information about the tile including
     *                         placement,entry and exit of the track.
     * @return true is the tile contains a track back to a edge station
     * otherwise,return false
     * @author Jiawei Fan
     */
    public static boolean isNextToEdge(TileEntryAndExit tileEntryAndExit) {
        String tile = tileEntryAndExit.getPlacement();
        int row = Integer.parseInt(tile.substring(Placement.LENGTH_OF_TILE_TYPE, Placement.LENGTH_OF_TILE_TYPE + 1));
        int col = Integer.parseInt(tile.substring(Placement.LENGTH_OF_TILE_TYPE + 1, Placement.LENGTH_OF_TILE_TYPE + 2));
        if (row == 0 && tileEntryAndExit.getExit() == 1) {//back to top edge station,exit needs to be 1
            return true;
        }
        if (col == 0 && tileEntryAndExit.getExit() == 7) {//back to left edge station,exit needs to be 7
            return true;
        }
        if (row == 7 && tileEntryAndExit.getExit() == 5) {//back to bottom edge station,exit needs to be 5
            return true;
        }
        return col == 7 && tileEntryAndExit.getExit() == 3;//back to right edge station,exit needs to be 3
    }

    /**
     * get score of one player by passing a PlayerStationAndPlacement which contains player's information including number of player in
     * the game,player number,the staions the player owns and the placement the player has already placed
     *
     * @param playerStationAndPlacement contains player's information including number of player in
     *                                  the game,player number,the staions the player owns and the placement the player has already placed
     * @param placement                 a long string of tiles placement on board
     * @return a int representing the score of the player
     * @author Jiawei Fan
     */
    public static int getScore(PlayerStationAndPlacement playerStationAndPlacement, String placement) {
        ArrayList<String> startTiles = getStartTile(playerStationAndPlacement, placement);//get possible start tile of this player
        int score = 0;//initialize score to be 0
        for (String startTile : startTiles) {//check each member in startTiles,note that it is composed of tile and station,for example,"aaaa00" "9" "bbbb01" "7".
            if (startTile.length() == Placement.LENGTH_OF_ONE_PlACEMENT) {//only test the tile but not the station number
                int index = startTiles.indexOf(startTile);
                int startStation = Integer.parseInt(startTiles.get(index + 1));//get the station number
                ArrayList<TileEntryAndExit> track = findRoute(startStation, placement, startTile);//find track of this start tile
                int plusScore = 0;//initialize to be 0
                if (track.size() == 1)//generally it, track of size one won't get scores. But it has rare situtation that a tile has track loops back to the start station
                    if (isNextToEdge(track.get(0))) {
                        plusScore = 1;//plus one score if it goes back to edge
                    }
                if (track.size() > 1) {
                    int index1 = track.size();//get the size of the track
                    TileEntryAndExit lastTile = track.get(index1 - 1);//get the last tile
                    if (isNextToCentre(lastTile)) {//check if last tile next to center station
                        plusScore = index1 * 2;
                    }
                    if (isNextToEdge(lastTile)) {//check if last tile next to edge
                        plusScore = index1;
                    }
                }
                score = score + plusScore;
            }
        }
        return score;
    }
}
