package comp1110.ass2;

import java.util.ArrayList;

public class GameEngine {
    private static String mode;


    public GameEngine(){

    }
    public GameEngine(String mode){
        GameEngine.mode = mode;
    }

    public static void main(String[] args) {
        //Metro and PlayerStation codes are static no need to import
        int playerCount = 2;
        ArrayList<Object> players = new ArrayList<>();
        mode = "Computer";
        if(mode.equals("Computer")){
            //create instances of computer
            for(int playerNumber =0;playerNumber<playerCount-1;playerNumber++){

                players.add(new Computer(playerNumber,PlayerStation.getPlayerStationArrayList(playerCount).get(playerNumber).getStationOwned(),playerCount));
            }
            players.add(new ManualPlayer(playerCount-1,PlayerStation.getPlayerStationArrayList(playerCount).get(playerCount-1).getStationOwned(),playerCount));
        }
        if(mode.equals("Manual")){
            //create instances of manual player
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
                        placementSequence = player1.compMeth(placementSequence, "");
                        System.out.println("Movement by computer " + i + " is :" + placementSequence);
                        token = passtoken(token,playerCount);

                    }
                    else {
                        ManualPlayer player1 = (ManualPlayer) players.get(token);
                        if(Metro.isPlacementSequenceValid(player1.manMeth(placementSequence, ""))){
                            placementSequence =player1.manMeth(placementSequence, "");
                        }
                        System.out.println("Movement by manual " + i + " is :" + placementSequence);
                        token=  passtoken(token,playerCount);


                    }


            }
            win=true;
        }


    return placementSequence;
    }

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
