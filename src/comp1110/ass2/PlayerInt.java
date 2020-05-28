package comp1110.ass2;

/**
 * Interface class the describes a player. It contains a method methodPlay
 * It will be implemented by class must implement the method play method independently
 * Jiawei suggested to delete this interface because it is not actullay useful, but
 * this class is not deleted becasue Ganaraj thinks this is his best code and after negotiation with team, this class is remained.
 *
 * @author: Ganaraj Rao
 */
public interface PlayerInt {
    /**
     * methodPlay method describes the behavior of any player.
     *
     * @params: @params: placementSequence: String describing the current placement of tiles on the board
     * totalHands: String representing all the tiles in hands oll the players.
     * @author: Ganaraj Rao
     */
    String methodPlay(String placementSequence, String totalHands);

}
