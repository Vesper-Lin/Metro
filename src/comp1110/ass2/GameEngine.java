package comp1110.ass2;

import java.util.ArrayList;
/**
* This class describe the class that will run the game. It creates objects of the players, both computer and manual and enable gaming.
*This class is however, incomplete due to integration issues with Viewer class.
* @author: Ganaraj Rao
 */
public class GameEngine {
    private static String mode;


    public GameEngine(){

    }
    public GameEngine(String mode){
        GameEngine.mode = mode;
    }

    public static void main(String[] args) {
        int playerCount = 2;
        ArrayList<Object> players = new ArrayList<>();
        mode = "Computer";
        if(mode.equals("Computer")){
            //create n-1 computer players
            for(int playerNumber =0;playerNumber<playerCount-1;playerNumber++){

                players.add(new Computer(playerNumber,PlayerStation.getPlayerStationArrayList(playerCount).get(playerNumber).getStationOwned(),playerCount));
            }
            //create one manual player
            players.add(new ManualPlayer(playerCount-1,PlayerStation.getPlayerStationArrayList(playerCount).get(playerCount-1).getStationOwned(),playerCount));
        }
        if(mode.equals("Manual")){
            //create instances of n manual player
            for(int playerNumber =0;playerNumber<playerCount;playerNumber++){

                players.add(new ManualPlayer(playerNumber,PlayerStation.getPlayerStationArrayList(playerCount).get(playerNumber).getStationOwned(),playerCount));
            }
        }
        //method to load deck
        ArrayList<String> initialDeck = Deck.getInitialDeck();


        //method to run the game
        String finalBoardSequence = play(initialDeck,playerCount,players);

        // method to store final result
        int[] scores = Metro.getScore(finalBoardSequence, playerCount);
        for(int player =0;player<playerCount;player++){
            System.out.println("Score for player number "+player+" is: "+scores[player]);
        }


    }
/** This method describes the play method, which will run the game at backend.
 * @params: initialDeck: Represents the initial deck of cards
 *          playercount: total number of players
 *          players: The list containing the player class objects
 * @returns: placementSequence: String that represents the current state of the board.
 * @author: Ganaraj Rao
 *
 *
 */
    private static String play(ArrayList<String> initialDeck, int playerCount, ArrayList<Object> players) {

        int token =Token.getToken();
        System.out.println(token);
        System.out.println(players);
        boolean win=false;
        String placementSequence ="";
        while(win==false){

            for(int i =0;i<60;i++){

                    if (players.get(token) instanceof Computer) {
                        Computer player1 = (Computer) players.get(token);
                        placementSequence = player1.methodPlay(placementSequence, "");
                        System.out.println("Movement by computer " + i + " is :" + placementSequence);
                        token = passtoken(token,playerCount);

                    }
                    else {
                        ManualPlayer player1 = (ManualPlayer) players.get(token);
                        if(Metro.isPlacementSequenceValid(player1.methodPlay(placementSequence, ""))){
                            placementSequence =player1.methodPlay(placementSequence, "");
                        }
                        System.out.println("Movement by manual " + i + " is :" + placementSequence);
                        token=  passtoken(token,playerCount);


                    }


            }
            win=true;
        }


    return placementSequence;
    }
    /**
     * This method updates the token value, effectively passes the control to next player in round robin fashion.
     * Once a full circle is completed it resets the token to initial state.
     *
     * @author Ganaraj Rao
     *
     * @param token: representing the token for contolling the game
     * @param playerCount: total number of players
     * @return token: updates the token value and returns it
     */
    private static int passtoken(int token, int playerCount) {
        if(token<playerCount-1){
            token =token+1;
        }
        else{
            token=0;
        }
        return token;
    }


}
