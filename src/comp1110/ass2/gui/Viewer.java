package comp1110.ass2.gui;

import comp1110.ass2.Metro;
import comp1110.ass2.Placement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This class include the GUI of the Metro game and some methods
 * and global variable are also there. It also contains a main
 * method so that by running this class, metro game starts.
 *
 * After running Viewer.class, there are several options, the
 * first option is playing with real players and the number of
 * players can be 2 to 6. The second one is playing with AI and
 * player 1 is set to be the real player and others are AI. The
 * third one is playing with advanced AI who places the tiles
 * according to some rules so they aim to get higher scores.
 *
 * @author Yuxuan Lin
 * @author Jiawei Fan
 */
public class Viewer extends Application {
    /* board layout */
    private static final int SQUARE_SIZE = 70;
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;
    private static final int RIGHT_ANGLE = 90;
    private int numeberOfPlayer;
    private Boolean isAdvancedBot=false;
    private StringBuilder placementStringBuilder=new StringBuilder();
    private String[] totalHandArray =new String[6];
    private static final int MAX_PLAYER_NUMBER=6;
    private static final String URI_BASE = "assets/";

    private final Group root1 = new Group();
    private final Group root2 = new Group();
    private final Group board = new Group();
    private final Group controls = new Group();
    private final Group scoreGroup= new Group();
    private final Group flagGroup=new Group();
    private final Group validPlaces =new Group();
    private TextField textField;
    private TextField textField2;
    private TextField textField3;


    /**
     *
     * @param root1
     */
    void drawBoard(Group root1){
        ArrayList<Rectangle> rectangles=AddElement.getExistingRectangle();
        for (Rectangle a:rectangles)
        {
            board.getChildren().add(a);
        }
        for (int i=1; i<9; i++){
            /* draw the stations 1-8 */
            ImageView background = new ImageView();
            background.setImage(new Image(this.getClass().getResource(URI_BASE + "station" + i + ".jpg").toString()));
            background.setFitWidth(SQUARE_SIZE);
            background.setFitHeight(SQUARE_SIZE);
            background.setLayoutY(0);
            background.setLayoutX(SQUARE_SIZE*(9-i));
            background.setRotate(RIGHT_ANGLE*2);
            board.getChildren().add(background);
        }
        for (int i=9; i<17; i++){
            /* draw the stations 9-16 */
            ImageView background = new ImageView();
            background.setImage(new Image(this.getClass().getResource(URI_BASE + "station" + i + ".jpg").toString()));
            background.setFitWidth(SQUARE_SIZE);
            background.setFitHeight(SQUARE_SIZE);
            background.setLayoutY(SQUARE_SIZE*(i-8));
            background.setLayoutX(0);
            background.setRotate(RIGHT_ANGLE);
            board.getChildren().add(background);
        }
        for (int i=17; i<25; i++){
            /* draw the stations 17-24 */
            ImageView background = new ImageView();
            background.setImage(new Image(this.getClass().getResource(URI_BASE + "station" + i + ".jpg").toString()));
            background.setFitWidth(SQUARE_SIZE);
            background.setFitHeight(SQUARE_SIZE);
            background.setLayoutY(SQUARE_SIZE*9);
            background.setLayoutX(SQUARE_SIZE*(i-16));
            board.getChildren().add(background);
        }
        for (int i=25; i<33; i++){
            /* draw the stations 25-32 */
            ImageView background = new ImageView();
            background.setImage(new Image(this.getClass().getResource(URI_BASE + "station" + i + ".jpg").toString()));
            background.setFitWidth(SQUARE_SIZE);
            background.setFitHeight(SQUARE_SIZE);
            background.setLayoutY(SQUARE_SIZE*(33-i));
            background.setLayoutX(SQUARE_SIZE*9);
            background.setRotate(RIGHT_ANGLE*3);
            board.getChildren().add(background);
        }

        ImageView background = new ImageView();
        /* draw one of the centre stations */
        background.setImage(new Image(this.getClass().getResource(URI_BASE + "centre_station.jpg").toString()));
        background.setFitWidth(SQUARE_SIZE);
        background.setFitHeight(SQUARE_SIZE);
        background.setLayoutY(SQUARE_SIZE*4);
        background.setLayoutX(SQUARE_SIZE*4);
        background.setRotate(RIGHT_ANGLE*3);
        board.getChildren().add(background);

        ImageView background2 = new ImageView();
        /* draw one of the centre stations */
        background2.setImage(new Image(this.getClass().getResource(URI_BASE + "centre_station.jpg").toString()));
        background2.setFitWidth(SQUARE_SIZE);
        background2.setFitHeight(SQUARE_SIZE);
        background2.setLayoutY(SQUARE_SIZE*5);
        background2.setLayoutX(SQUARE_SIZE*4);
        background2.setRotate(RIGHT_ANGLE*2);
        board.getChildren().add(background2);

        ImageView background3 = new ImageView();
        /* draw one of the centre stations */
        background3.setImage(new Image(this.getClass().getResource(URI_BASE + "centre_station.jpg").toString()));
        background3.setFitWidth(SQUARE_SIZE);
        background3.setFitHeight(SQUARE_SIZE);
        background3.setLayoutY(SQUARE_SIZE*5);
        background3.setLayoutX(SQUARE_SIZE*5);
        background3.setRotate(RIGHT_ANGLE);
        board.getChildren().add(background3);

        ImageView background4 = new ImageView();
        /* draw one of the centre stations */
        background4.setImage(new Image(this.getClass().getResource(URI_BASE + "centre_station.jpg").toString()));
        background4.setFitWidth(SQUARE_SIZE);
        background4.setFitHeight(SQUARE_SIZE);
        background4.setLayoutY(SQUARE_SIZE*4);
        background4.setLayoutX(SQUARE_SIZE*5);
        board.getChildren().add(background4);

        ImageView background5 = new ImageView();
        /* draw one of the tile-covers in the corner */
        background5.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background5.setFitWidth(SQUARE_SIZE);
        background5.setFitHeight(SQUARE_SIZE);
        background5.setLayoutY(0);
        background5.setLayoutX(0);
        board.getChildren().add(background5);

        ImageView background6 = new ImageView();
        /* draw one of the tile-covers in the corner */
        background6.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background6.setFitWidth(SQUARE_SIZE);
        background6.setFitHeight(SQUARE_SIZE);
        background6.setLayoutY(0);
        background6.setLayoutX(SQUARE_SIZE*9);
        board.getChildren().add(background6);

        ImageView background7 = new ImageView();
        /* draw one of the tile-covers in the corner */
        background7.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background7.setFitWidth(SQUARE_SIZE);
        background7.setFitHeight(SQUARE_SIZE);
        background7.setLayoutY(SQUARE_SIZE*9);
        background7.setLayoutX(SQUARE_SIZE*9);
        board.getChildren().add(background7);

        ImageView background8 = new ImageView();
        /* draw one of the tile-covers in the corner */
        background8.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background8.setFitWidth(SQUARE_SIZE);
        background8.setFitHeight(SQUARE_SIZE);
        background8.setLayoutY(SQUARE_SIZE*9);
        background8.setLayoutX(0);
        board.getChildren().add(background8);
        root1.getChildren().add(board);
    }
    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer

        board.getChildren().clear();
        int numberOfPiece=placement.length()/6;
        for (int i =0;i<numberOfPiece;i++)
        {
            String type=placement.substring(6*i,6*i+4);
            ImageView piece=new ImageView();
            piece.setImage(new Image(this.getClass().getResource("assets/"+type+".jpg").toString()));
            String rowString=placement.substring(6*i+4,6*i+5);
            String colString=placement.substring(6*i+5,6*i+6);
            int row1 =Integer.parseInt(rowString)+1;
            int col1=Integer.parseInt(colString)+1;
            piece.setLayoutX(col1*SQUARE_SIZE);
            piece.setLayoutY(row1*SQUARE_SIZE);
            piece.setFitHeight(SQUARE_SIZE);
            piece.setFitWidth(SQUARE_SIZE);
            board.getChildren().add(piece);
        }
        root1.getChildren().add(board);
    }






    /**
     * Create a basic text field for input and a refresh button.
     */
    void makeControls() {
        Label label0 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label0, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    /**
     * This method is for the games for real players, it contains several buttons which
     * contains different functionalites. For example, the draw button is to draw a new
     * tile from the deck when player does not have a tile in their hand. Scores are changed
     * everytime any player place a tile, scores are initialised to be all zero.
     * @param primaryStage a stage that contains the scene
     * @param root the root group to add elements in
     * @param scene a scene contains the root
     * @author Jiawei Fan
     */
    public void addFunction(Stage primaryStage,Group root,Scene scene)
    {
        root.getChildren().add(validPlaces); //validPlaces contains valid rectangles which tile can put on their location, it will be explained further later
        drawBoard(root);
        Button button = new Button("Draw");
        button.setStyle("-fx-base: pink;");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String drawnTile=Metro.drawFromDeck(placementStringBuilder.toString(),AddElement.stringArrayToString(totalHandArray));
                //draw a tile each time "Draw" button is clicked
                if (drawnTile.equals(""))
                {//game over when there is no more tile in the deck
                    AddElement.addGameOver(root);
                }
                int numberOfPlacement=placementStringBuilder.toString().length()/ Placement.LENGTH_OF_ONE_PlACEMENT;
                int turnIndex=numberOfPlacement%numeberOfPlayer;//indicate which player should place tile
                    totalHandArray[turnIndex]=drawnTile;
                    AddElement.DraggableRectangle b=new AddElement.DraggableRectangle(root,drawnTile,placementStringBuilder,numeberOfPlayer,validPlaces,totalHandArray,turnIndex,button,scoreGroup,flagGroup);
                    //by passing all the needed information, DraggableRectangle will deal with the tiles
                    validPlaces.getChildren().add(b);
            }
        });
        button.setLayoutX(11.2*SQUARE_SIZE);
        button.setLayoutY(10*SQUARE_SIZE);
        validPlaces.getChildren().add(button);//set draw button parameters
        for (int i=1;i<=numeberOfPlayer;i++)
        {
            String playerNumber=i+"";
            AddElement.addText(root,"player "+playerNumber,10.1,1.3*i,20,Color.PINK);//add player text, numbers are not stored
            //as global variable because i thought it was just about coordinates.
        }
        Button button2 = new Button("Restart");
        button2.setStyle("-fx-base: pink;");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(scene);
                root.getChildren().clear();
                numeberOfPlayer=0;//set all parameters to initial conditions
                placementStringBuilder=new StringBuilder();
                totalHandArray= new String[MAX_PLAYER_NUMBER];
                validPlaces.getChildren().clear();
            }
        });
        button2.setLayoutX(12*SQUARE_SIZE);//set "draw again" button
        button2.setLayoutY(0);
        root.getChildren().add(button2);
        AddElement.addText(root,"Current Score",11.7,0.8,15,Color.DEEPPINK);
        AddElement.addText(root,"Currenet Hand",10.8,9.8,15,Color.DEEPPINK);
        AddElement.addScore(root,scoreGroup,"",numeberOfPlayer);
        AddElement.addFlag(root,flagGroup,placementStringBuilder.length()/Placement.LENGTH_OF_ONE_PlACEMENT);
    }

    /**
     * This method is similiar to addFunction1, what is different is that this one is for play with
     * simple AI. All buttons are basically the same as addFunction1. Sorry im not intending to waste
     * the readers time. (The codes may be a bit repetitive, maybe it can be improved by setting a another
     * siganature in addFunction method to indicate it is addfunction 1 or 2 or 3)
     * @param primaryStage a stage contains the scene
     * @param root a root contains all the element
     * @param scene a scene contains the root
     * @author Jiawei Fan
     */
    public void addFunction2(Stage primaryStage,Group root,Scene scene)
    {
        root.getChildren().add(validPlaces);
        drawBoard(root);
        Button button = new Button("Draw");
        button.setStyle("-fx-base: pink;");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String drawnTile=Metro.drawFromDeck(placementStringBuilder.toString(),AddElement.stringArrayToString(totalHandArray));
                if (drawnTile.equals(""))
                {
                    AddElement.addGameOver(root);
                }
                int numberOfPlacement=placementStringBuilder.toString().length()/6;
                int turnIndex=numberOfPlacement%numeberOfPlayer;
                isAdvancedBot=false;//this boolean is initiliased to be false, indicating that it is simple AI
                totalHandArray[turnIndex]=drawnTile;
                AddElement.DraggableRectangle3 b=new AddElement.DraggableRectangle3(root,drawnTile,placementStringBuilder,numeberOfPlayer,validPlaces,totalHandArray,turnIndex,button,scoreGroup,flagGroup, isAdvancedBot);
                validPlaces.getChildren().add(b);
            }
        });
        button.setLayoutX(11.2*SQUARE_SIZE);
        button.setLayoutY(10*SQUARE_SIZE);
        validPlaces.getChildren().add(button);
        for (int i=1;i<=numeberOfPlayer;i++)
        {
            String playerNumber=i+"";
            AddElement.addText(root,"player "+playerNumber,10.1,1.3*i,20,Color.PINK);
        }
        Button button2 = new Button("Restart");
        button2.setStyle("-fx-base: pink;");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(scene);
                root.getChildren().clear();
                numeberOfPlayer=0;
                placementStringBuilder=new StringBuilder();
                totalHandArray= new String[6];
                isAdvancedBot=false;
                validPlaces.getChildren().clear();
            }
        });
        button2.setLayoutX(12*SQUARE_SIZE);
        button2.setLayoutY(0);
        root.getChildren().add(button2);
        AddElement.addText(root,"Current Score",11.7,0.8,15,Color.DEEPPINK);
        AddElement.addText(root,"Currenet Hand",10.8,9.8,15,Color.DEEPPINK);
        AddElement.addScore(root,scoreGroup,"",numeberOfPlayer);
        AddElement.addFlag(root,flagGroup,placementStringBuilder.length()/6);
    }

    /**
     * Again, addFunction3 has the similiar functions as addFunction1 and 2, sorry again for the repetition,
     * the only difference is that the Boolean isAdvanced is initialised to be false. Do not want waste readers'
     * time to read again this method.
     * @param primaryStage a Stage contains the root
     * @param root a root contains all the needed elements
     * @param scene a scene contains the root
     * @author Jiawei Fan
     */
    public void addFunction3(Stage primaryStage,Group root,Scene scene)
    {
        root.getChildren().add(validPlaces);
        drawBoard(root);
        Button button = new Button("Draw");
        button.setStyle("-fx-base: pink;");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String drawnTile=Metro.drawFromDeck(placementStringBuilder.toString(),AddElement.stringArrayToString(totalHandArray));
                if (drawnTile.equals(""))
                {
                    AddElement.addGameOver(root);
                }
                int numberOfPlacement=placementStringBuilder.toString().length()/6;
                int turnIndex=numberOfPlacement%numeberOfPlayer;
                isAdvancedBot=true;//the only difference is here,this boolean is set to be true
                totalHandArray[turnIndex]=drawnTile;
                AddElement.DraggableRectangle3 b=new AddElement.DraggableRectangle3(root,drawnTile,placementStringBuilder,numeberOfPlayer,validPlaces,totalHandArray,turnIndex,button,scoreGroup,flagGroup, isAdvancedBot);
                validPlaces.getChildren().add(b);
            }
        });
        button.setLayoutX(11.2*SQUARE_SIZE);
        button.setLayoutY(10*SQUARE_SIZE);
        validPlaces.getChildren().add(button);
        for (int i=1;i<=numeberOfPlayer;i++)
        {
            String playerNumber=i+"";
            AddElement.addText(root,"player "+playerNumber,10.1,1.3*i,20,Color.PINK);
        }
        Button button2 = new Button("Restart");
        button2.setStyle("-fx-base: pink;");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(scene);
                root.getChildren().clear();
                numeberOfPlayer=0;
                placementStringBuilder=new StringBuilder();
                totalHandArray= new String[MAX_PLAYER_NUMBER];
                isAdvancedBot=false;
                validPlaces.getChildren().clear();
            }
        });
        button2.setLayoutX(12*SQUARE_SIZE);
        button2.setLayoutY(0);
        root.getChildren().add(button2);
        AddElement.addText(root,"Current Score",11.7,0.8,15,Color.DEEPPINK);
        AddElement.addText(root,"Currenet Hand",10.8,9.8,15,Color.DEEPPINK);
        AddElement.addScore(root,scoreGroup,"",numeberOfPlayer);
        AddElement.addFlag(root,flagGroup,placementStringBuilder.length()/6);
    }

    /**
     * This is the main method which is used to visualise the metro game. A lot of elements are set.
     * For example, "welcome" text message.
     * @param primaryStage an initial stage which will be used to contain the scene
     * @author Jiawei Fan
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene1 = new Scene(root1, VIEWER_WIDTH, VIEWER_HEIGHT);//set two different scenes which contains two different roots
        Scene scene2 =new Scene(root2, VIEWER_WIDTH, VIEWER_HEIGHT);
        primaryStage.setTitle("Metro Game Viewer");
        ImageView background = new ImageView();
        background.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background.setFitWidth(VIEWER_HEIGHT);
        background.setFitHeight(VIEWER_HEIGHT);
        root2.getChildren().add(background);
        AddElement.addText(root2,"Welcome!",11.0,1.0,50,Color.PINK);
        AddElement.addText(root2,"Please choose playing with",11.0,1.8,20,Color.PINK);
        AddElement.addText(root2,"people or with computer",11.0,2.3,20,Color.PINK);
        AddElement.addText(root2,"Number of real players",11.0,4.0,20,Color.PINK);
        AddElement.addText(root2,"(please type between 2-6)",11.0,5.0,20,Color.PINK);
        AddElement.addText(root2,"Or",12.5,5.5,20,Color.PINK);
        AddElement.addText(root2,"Number of player and AI",11.0,6.0,20,Color.PINK);
        AddElement.addText(root2,"(please type between 2-6)",11.0,7.0,20,Color.PINK);
        AddElement.addText(root2,"Or",12.5,7.5,20,Color.PINK);
        AddElement.addText(root2,"player advanced AI numbner",11.0,8.0,20,Color.PINK);
        AddElement.addText(root2,"(please type between 2-6)",11.0,9.0,20,Color.PINK);
        textField = new TextField();
        textField.setText("2");//initialise the textfield to be 2
        textField.setPrefWidth(100);
        Button button = new Button("Set");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                numeberOfPlayer=Integer.parseInt(textField.getText());
                if (numeberOfPlayer<2||numeberOfPlayer>6)
                {//if the number of player is invalid, need type the number again
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                }
                else {//if the number of player is valid, go to next scene
                    primaryStage.setScene(scene1);
                    addFunction(primaryStage,root1,scene2);//this is for real players
                }
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll( textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(SQUARE_SIZE*11.1);
        hb.setLayoutY(SQUARE_SIZE*4.2);
        root2.getChildren().add(hb);
        textField2 = new TextField();//here,again the code is similiar to above,the only difference is that addfunction1 becomes addfunction2
        textField2.setText("2");
        textField2.setPrefWidth(100);
        Button button2 = new Button("Set");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                numeberOfPlayer=Integer.parseInt(textField2.getText());
                if (numeberOfPlayer<2||numeberOfPlayer>6)
                {
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                }
                else {
                    primaryStage.setScene(scene1);
                    addFunction2(primaryStage,root1,scene2);
                }
            }
        });
        HBox hb2 = new HBox();
        hb2.getChildren().addAll( textField2, button2);
        hb2.setSpacing(10);
        hb2.setLayoutX(SQUARE_SIZE*11.1);
        hb2.setLayoutY(SQUARE_SIZE*6.2);
        root2.getChildren().add(hb2);
        textField3 = new TextField();//The code below is similar to above, only difference is addFunction2 becomes addFunction3
        textField3.setText("2");
        textField3.setPrefWidth(100);
        Button button3 = new Button("Set");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                numeberOfPlayer=Integer.parseInt(textField3.getText());
                if (numeberOfPlayer<2||numeberOfPlayer>6)
                {
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                }
                else {
                    primaryStage.setScene(scene1);
                    addFunction3(primaryStage,root1,scene2);
                }
            }
        });
        HBox hb3 = new HBox();
        hb3.getChildren().addAll( textField3, button3);
        hb3.setSpacing(10);
        hb3.setLayoutX(SQUARE_SIZE*11.1);
        hb3.setLayoutY(SQUARE_SIZE*8.2);
        root2.getChildren().add(hb3);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

}