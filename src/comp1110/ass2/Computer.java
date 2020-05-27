package comp1110.ass2;
/**
This class reperesents a Computer it implements the PlayerInt interface.
Instance of this class, creates a Computer player instance.
@author: Ganaraj Rao
 */
public class Computer implements PlayerInt{
    private int[] stationsOwned;
    private int playerCount;


    public Computer(int playerNumber, int[] stationsOwned,int playerCount){
        this.stationsOwned = stationsOwned;
        this.playerCount = playerCount;
    }
    /**
    *This method describes the play method for a Computer player.
    * It draws a tile and places on the board using the drawfromDeck() and
    * generateMove() in Metro class.
    * @params: placementSequence: String describing the current placement of tiles on the board
    *         totalHands: String representing all the tiles in hands oll the players.
    * @returns: placementSequence: adds the move performed to the input placementSequence String and returns the
    * updated placement sequence
    * @author: Ganaraj Rao
    *
     */
    public String methodPlay(String placementSequence, String totalHands){
        //draw a tile
        String sr = Metro.drawFromDeck(placementSequence,totalHands);
        //place tile on board
        String move = Metro.generateMove(placementSequence,sr,playerCount);
        //update placementsequence
        placementSequence = placementSequence+move;
        return placementSequence;

    }
}
