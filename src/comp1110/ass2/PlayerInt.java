package comp1110.ass2;
/** Interface class the describes a player. It contains a method methodPlay
* It will be implemented by class must implement the method play method independently
* @author: Ganaraj Rao
 */
public interface PlayerInt {
    /**methodPlay method describes the behavior of any player.
    * @params: @params: placementSequence: String describing the current placement of tiles on the board
     *         totalHands: String representing all the tiles in hands oll the players.
     * @author: Ganaraj Rao
     */
    public String methodPlay(String placementSequence, String totalHands);

}
