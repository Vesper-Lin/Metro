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
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * A very simple viewer for piece placements in the Metro game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 * @author Yuxuan Lin(u6828533)
 */
public class Viewer extends Application {
    /* board layout */
    private static final int SQUARE_SIZE = 70;
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;
    private static final int RIGHT_ANGLE = 90;
    private int numeberOfPlayer;
    private StringBuilder placementStringBuilder=new StringBuilder();
    private String[] totalHandArray =new String[6];

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


//    static class DrawPiece extends ImageView{
//        /* draw the taken piece */
//        String pieceType;
//        DrawPiece(String placementPiece){
//            this.pieceType = placementPiece;
//            setFitHeight(SQUARE_SIZE);
//            setFitWidth(SQUARE_SIZE);
//            this.setImage(new Image(Viewer.class.getResource(URI_BASE + pieceType + ".jpg").toString()));
//        }
//    }



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


    public void addFunction(Stage primaryStage,Group root,Scene scene)
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
                System.out.println(turnIndex);
                if (totalHandArray[turnIndex]!=null)
                {
                    //do something
                    AddElement.DraggableRectangle c=new AddElement.DraggableRectangle(root,totalHandArray[turnIndex],placementStringBuilder,numeberOfPlayer,validPlaces,totalHandArray,turnIndex,scoreGroup,flagGroup);
                    validPlaces.getChildren().add(c);
                }
                else
                {
                    //do something
                    totalHandArray[turnIndex]=drawnTile;
                    AddElement.DraggableRectangle b=new AddElement.DraggableRectangle(root,drawnTile,placementStringBuilder,numeberOfPlayer,validPlaces,totalHandArray,turnIndex,scoreGroup,flagGroup);
                    validPlaces.getChildren().add(b);
                }
            }
        });
        button.setLayoutX(11.2*SQUARE_SIZE);
        button.setLayoutY(10*SQUARE_SIZE);
        root.getChildren().add(button);
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


    @Override
    public void start(Stage primaryStage) {
        Scene scene1 = new Scene(root1, VIEWER_WIDTH, VIEWER_HEIGHT);
        Scene scene2 =new Scene(root2, VIEWER_WIDTH, VIEWER_HEIGHT);
        primaryStage.setTitle("Metro Game Viewer");
        ImageView background = new ImageView();
        background.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background.setFitWidth(VIEWER_HEIGHT);
        background.setFitHeight(VIEWER_HEIGHT);
        root2.getChildren().add(background);
        AddElement.addText(root2,"Welcome!",11.1,1.0,50,Color.PINK);
        AddElement.addText(root2,"Please choose playing with",11.1,1.8,20,Color.PINK);
        AddElement.addText(root2,"people or with computer",11.1,2.3,20,Color.PINK);
        AddElement.addText(root2,"Number of real players",11.1,4.0,20,Color.PINK);
        AddElement.addText(root2,"(please type between 2-6)",11.1,5.0,20,Color.PINK);
        AddElement.addText(root2,"Or",12.5,6.5,20,Color.PINK);
        AddElement.addText(root2,"Number of player and bots",11.1,7.8,20,Color.PINK);
        AddElement.addText(root2,"(please type between 2-6)",11.1,8.8,20,Color.PINK);
        textField = new TextField();
        textField.setText("2");
        textField.setPrefWidth(100);
        Button button = new Button("Set");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                numeberOfPlayer=Integer.parseInt(textField.getText());
                if (numeberOfPlayer<2||numeberOfPlayer>6)
                {
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                }
                else {
                    primaryStage.setScene(scene1);
                    addFunction(primaryStage,root1,scene2);
                }
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll( textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(SQUARE_SIZE*11.1);
        hb.setLayoutY(SQUARE_SIZE*4.2);
        root2.getChildren().add(hb);
        textField2 = new TextField();
        textField2.setText("2");
        textField2.setPrefWidth(100);
        Button button2 = new Button("Set");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                numeberOfPlayer=Integer.parseInt(textField2.getText());
                root2.getChildren().clear();
            }
        });
        HBox hb2 = new HBox();
        hb2.getChildren().addAll( textField2, button2);
        hb2.setSpacing(10);
        hb2.setLayoutX(SQUARE_SIZE*11.1);
        hb2.setLayoutY(SQUARE_SIZE*8);
        root2.getChildren().add(hb2);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

}