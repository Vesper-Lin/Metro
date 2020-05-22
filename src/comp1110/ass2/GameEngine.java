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
        //method to start game

        play(initialDeck,playerCount,players);
        //method to run the game
        // method to store final result


    }

    private static void play(ArrayList<String> initialDeck, int playerCount, ArrayList<Object> players) {

        int token =Token.getToken();
        System.out.println(token);
        System.out.println(players);
        boolean win=false;
        String placementSequence ="";
        while(win==false){

            for(int i =0;i<60;i++){

                    if (players.get(token) instanceof Computer) {
                        Computer player1 = (Computer) players.get(token);
                        placementSequence = placementSequence+player1.compMeth(placementSequence, "");
                        System.out.println("Movement by computer " + i + " is :" + placementSequence);
                        passtoken(token,playerCount,token);

                    }
                    else {
                        ManualPlayer player1 = (ManualPlayer) players.get(token);
                        placementSequence =placementSequence+placementSequence+ player1.manMeth(placementSequence, "");
                        System.out.println("Movement by manual " + i + " is :" + placementSequence);
                        passtoken(token,playerCount,token);

                    }


            }
            win=true;
        }



    }

    private static void passtoken(int token, int playerCount, int token1) {
        if(token<playerCount){
            token1 =token+1;
        }
        else{
            token1=0;
        }
    }

    public void passtoken(int token,int playerCount){


    }
}
