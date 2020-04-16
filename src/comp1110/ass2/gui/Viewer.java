package comp1110.ass2.gui;

import comp1110.ass2.Board;
import comp1110.ass2.Metro;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

/**
 * A very simple viewer for piece placements in the Metro game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {
    /* board layout */
    private static final int SQUARE_SIZE = 70;
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group board = new Group();
    private final Group controls = new Group();
    private final Group placement = new Group();
    private TextField textField;

    class DrawPiece extends ImageView{
        String pieceType;
        DrawPiece(String placementPiece){
            this.pieceType = placementPiece;
            setFitHeight(SQUARE_SIZE);
            setFitWidth(SQUARE_SIZE);
            this.setImage(new Image(Viewer.class.getResource(URI_BASE + pieceType + ".jpg").toString()));
        }
    }

    void drawBoard(){
        board.getChildren().clear();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Rectangle r = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
                r.setLayoutX(col * SQUARE_SIZE + 162);
                r.setLayoutY(row * SQUARE_SIZE);
                r.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
                board.getChildren().add(r);
            }
        }

        for (int i=1; i<9; i++){
            ImageView background = new ImageView();
            background.setImage(new Image(this.getClass().getResource(URI_BASE + "station" + i + ".jpg").toString()));
            background.setFitWidth(SQUARE_SIZE);
            background.setFitHeight(SQUARE_SIZE);
            background.setLayoutY(0);
            background.setLayoutX(162+ 70*(9-i));
            background.setRotate(180);
            board.getChildren().add(background);
        }
        for (int i=9; i<17; i++){
            ImageView background = new ImageView();
            background.setImage(new Image(this.getClass().getResource(URI_BASE + "station" + i + ".jpg").toString()));
            background.setFitWidth(SQUARE_SIZE);
            background.setFitHeight(SQUARE_SIZE);
            background.setLayoutY(70*(i-8));
            background.setLayoutX(162);
            background.setRotate(90);
            board.getChildren().add(background);
        }
        for (int i=17; i<25; i++){
            ImageView background = new ImageView();
            background.setImage(new Image(this.getClass().getResource(URI_BASE + "station" + i + ".jpg").toString()));
            background.setFitWidth(SQUARE_SIZE);
            background.setFitHeight(SQUARE_SIZE);
            background.setLayoutY(630);
            background.setLayoutX(162 + 70*(i-16));
            board.getChildren().add(background);
        }
        for (int i=25; i<33; i++){
            ImageView background = new ImageView();
            background.setImage(new Image(this.getClass().getResource(URI_BASE + "station" + i + ".jpg").toString()));
            background.setFitWidth(SQUARE_SIZE);
            background.setFitHeight(SQUARE_SIZE);
            background.setLayoutY(70*(33-i));
            background.setLayoutX(792);
            background.setRotate(270);
            board.getChildren().add(background);
        }

        ImageView background = new ImageView();
        background.setImage(new Image(this.getClass().getResource(URI_BASE + "centre_station.jpg").toString()));
        background.setFitWidth(SQUARE_SIZE);
        background.setFitHeight(SQUARE_SIZE);
        background.setLayoutY(280);
        background.setLayoutX(442);
        background.setRotate(270);
        board.getChildren().add(background);

        ImageView background2 = new ImageView();
        background2.setImage(new Image(this.getClass().getResource(URI_BASE + "centre_station.jpg").toString()));
        background2.setFitWidth(SQUARE_SIZE);
        background2.setFitHeight(SQUARE_SIZE);
        background2.setLayoutY(350);
        background2.setLayoutX(442);
        background2.setRotate(180);
        board.getChildren().add(background2);

        ImageView background3 = new ImageView();
        background3.setImage(new Image(this.getClass().getResource(URI_BASE + "centre_station.jpg").toString()));
        background3.setFitWidth(SQUARE_SIZE);
        background3.setFitHeight(SQUARE_SIZE);
        background3.setLayoutY(350);
        background3.setLayoutX(512);
        background3.setRotate(90);
        board.getChildren().add(background3);

        ImageView background4 = new ImageView();
        background4.setImage(new Image(this.getClass().getResource(URI_BASE + "centre_station.jpg").toString()));
        background4.setFitWidth(SQUARE_SIZE);
        background4.setFitHeight(SQUARE_SIZE);
        background4.setLayoutY(280);
        background4.setLayoutX(512);
        board.getChildren().add(background4);

        ImageView background5 = new ImageView();
        background5.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background5.setFitWidth(SQUARE_SIZE);
        background5.setFitHeight(SQUARE_SIZE);
        background5.setLayoutY(0);
        background5.setLayoutX(162);
        board.getChildren().add(background5);

        ImageView background6 = new ImageView();
        background6.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background6.setFitWidth(SQUARE_SIZE);
        background6.setFitHeight(SQUARE_SIZE);
        background6.setLayoutY(0);
        background6.setLayoutX(792);
        board.getChildren().add(background6);

        ImageView background7 = new ImageView();
        background7.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background7.setFitWidth(SQUARE_SIZE);
        background7.setFitHeight(SQUARE_SIZE);
        background7.setLayoutY(630);
        background7.setLayoutX(792);
        board.getChildren().add(background7);

        ImageView background8 = new ImageView();
        background8.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background8.setFitWidth(SQUARE_SIZE);
        background8.setFitHeight(SQUARE_SIZE);
        background8.setLayoutY(630);
        background8.setLayoutX(162);
        board.getChildren().add(background8);
    }
    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        this.placement.getChildren().clear();

        if (!Metro.isPlacementSequenceWellFormed(placement)||!Metro.isPlacementSequenceValid(placement)){
            System.out.println("Error: Invalid Placement");
            return;
        }
        if (placement.length()==0){
            return;
        }
        for (int i=0; i<placement.length()-5; i+=6){
            DrawPiece drawPiece = new DrawPiece(placement.substring(i, i+4));

            int x = placement.charAt(i+4)-'0';
            int y = placement.charAt(i+5)-'0';

            drawPiece.setLayoutY((x+1) * SQUARE_SIZE);
            drawPiece.setLayoutX((y+1) * SQUARE_SIZE + 162);

            this.placement.getChildren().add(drawPiece);
        }
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
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
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(board);
        root.getChildren().add(controls);
        root.getChildren().add(placement);

        drawBoard();
        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}