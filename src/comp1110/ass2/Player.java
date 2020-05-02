package comp1110.ass2;

public class Player {
    private int numberOfPlayer;//number of players in a certain game
    private int playerNumber;//set private because it will be part of constructor later
    private String tilesInHand;//a four character string,if the player doesn't hold a tile in hand,this string should be null
    private int[] playerOwnEdgeStation;//depends on the numbers of players in the game,it shows the stations that one player owns
    private int playerScore;//decide the current score of the player

    public Player(int numberOfPlayer, int playerNumber) {
        this.numberOfPlayer = numberOfPlayer;
        this.playerNumber = playerNumber;
    }

    public int getNumberOfPlayer()
    {
        return numberOfPlayer;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }
    public void drawTile(){
        //draws a tile from the deck
    }

    public void placeTile(){
        //places a tile; returns a tile
        Tile tile;

    }

    public void holdTile(){

    }
}
