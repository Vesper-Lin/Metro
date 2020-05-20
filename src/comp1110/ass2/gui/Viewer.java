package comp1110.ass2.gui;

import comp1110.ass2.Board;
import comp1110.ass2.Deck;
import comp1110.ass2.Metro;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

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
    private double X;
    private double Y;
    private double mouseX;
    private double mouseY;
    private int row_closest;
    private int col_closest;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group board = new Group();
    private final Group controls = new Group();
    private final Group placement = new Group();
    private TextField textField;

    private final Slider players = new Slider();

    static class DrawPiece extends ImageView{
        /* draw the taken piece */
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
        for (int row = 0; row < 10; row++)
            /* draw the main body of the board (without stations and corners) */
        {
            for (int col = 0; col < 10; col++) {
                Rectangle r = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
                r.setLayoutX(col * SQUARE_SIZE);
                r.setLayoutY(row * SQUARE_SIZE);
                r.setStyle("-fx-fill: black; -fx-stroke: white; -fx-stroke-width: 1;");
                Text coordinate = new Text("("+(row-1)+","+(col-1)+")");
                coordinate.setFill(Color.WHITE);
                coordinate.setFont(Font.font("Tahoma", 20));
                coordinate.setLayoutX(col * SQUARE_SIZE +15 );
                coordinate.setLayoutY(row * SQUARE_SIZE +45);
                board.getChildren().add(r);
                board.getChildren().add(coordinate);
            }
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
    }
    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer

        this.placement.getChildren().clear();
        /* remove any previously drawn placement */

        if (Metro.isPlacementSequenceWellFormed(placement) && Metro.isPlacementSequenceValid(placement)){
            /* check whether the placement is valid */

            if (placement.length()!=0){
                /* check whether the placement is an empty string */

                for (int i=0; i<placement.length()-5; i+=6){
                    /* divide the placement sequence to pieces */

                    DrawPiece drawPiece = new DrawPiece(placement.substring(i, i + 4));
                    /* draw the taken pieces */

                    int x = placement.charAt(i+4)-'0';
                    int y = placement.charAt(i+5)-'0';

                    drawPiece.setLayoutY((x+1) * SQUARE_SIZE);
                    drawPiece.setLayoutX((y+1) * SQUARE_SIZE);
                    /* draw the pieces to the correct position on the board */

                    this.placement.getChildren().add(drawPiece);
                }
            }
            return;
        }
        System.out.println("Error: Invalid Placement");
    }

    void drawTile() {
        ImageView drawTile;
        drawTile = new ImageView();

        Random random = new Random();
        int i = random.nextInt(Deck.getInitialDeck().size());
        String random_tile_type = Deck.getInitialDeck().get(i);

        drawTile.setImage(new Image(this.getClass().getResource(URI_BASE + random_tile_type + ".jpg").toString()));
        drawTile.setFitWidth(SQUARE_SIZE);
        drawTile.setFitHeight(SQUARE_SIZE);
        X = SQUARE_SIZE * 12;
        Y = SQUARE_SIZE * 7;
        drawTile.setLayoutX(X);
        drawTile.setLayoutY(Y);
        controls.getChildren().add(drawTile);

        drawTile.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        drawTile.setOnMouseDragged(event -> {
            drawTile.setLayoutX(drawTile.getLayoutX() + event.getSceneX() - mouseX);
            drawTile.setLayoutY(drawTile.getLayoutY() + event.getSceneY() - mouseY);
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        drawTile.setOnMouseReleased(event -> {
            if (drawTile.getLayoutX() >= 0.5 * SQUARE_SIZE && drawTile.getLayoutX() <= 8.5 * SQUARE_SIZE
                    && drawTile.getLayoutY() >= 0.5 * SQUARE_SIZE && drawTile.getLayoutY() <= 8.5 * SQUARE_SIZE
                    && !(drawTile.getLayoutX() >= 3.5 * SQUARE_SIZE && drawTile.getLayoutX() <= 5.5 * SQUARE_SIZE
                    && drawTile.getLayoutY() >= 3.5 * SQUARE_SIZE && drawTile.getLayoutY() <= 5.5 * SQUARE_SIZE)) {
                row_closest = (int) Math.round(drawTile.getLayoutX() / SQUARE_SIZE);
                col_closest = (int) Math.round(drawTile.getLayoutY() / SQUARE_SIZE);

                int dx = SQUARE_SIZE * row_closest;
                int dy = SQUARE_SIZE * col_closest;

                drawTile.setLayoutX(dx);
                drawTile.setLayoutY(dy);
            } else {
                drawTile.setLayoutX(X);
                drawTile.setLayoutY(Y);
            }
        });
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

        players.setMin(2);
        players.setMax(6);
        players.setValue(2);
        players.setShowTickLabels(true);
        players.setShowTickMarks(true);
        players.setMajorTickUnit(1);
        players.setMinorTickCount(0);
        players.setSnapToTicks(true);

        players.setLayoutX(SQUARE_SIZE*12);
        players.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(players);

        final Label playersCaption = new Label("Number of Players:");
        playersCaption.setLayoutX(SQUARE_SIZE*10.5);
        playersCaption.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(playersCaption);

        final Label currentTileCaption = new Label("Current Tile:");
        currentTileCaption.setLayoutX(SQUARE_SIZE*10.5);
        currentTileCaption.setLayoutY(SQUARE_SIZE*7);
        controls.getChildren().add(currentTileCaption);

        Label label1 = new Label("Player 1");
        Button button1 = new Button("hand");
        button1.setOnAction(e -> {
        });
        Button button2 = new Button("draw");
        button2.setOnAction(e -> {
            drawTile();
        });
        Button button3 = new Button("place");
        button3.setOnAction(e -> {
        });

        HBox p1 = new HBox();
        p1.getChildren().addAll(label1, button1, button2, button3);
        p1.setSpacing(20);
        p1.setLayoutX(SQUARE_SIZE*10.5);
        p1.setLayoutY(SQUARE_SIZE);
        controls.getChildren().add(p1);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FocusGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        Button buttonX = new Button("Game Restart");
        buttonX.setStyle("-fx-text-fill: red");
        buttonX.setOnAction(e -> {
            Viewer viewer = new Viewer();
            viewer.start(new Stage());
            primaryStage.close();
        });

        HBox restart = new HBox();
        restart.getChildren().addAll(buttonX);
        restart.setLayoutX(SQUARE_SIZE*12);
        restart.setLayoutY(0);
        controls.getChildren().add(restart);

        root.getChildren().add(board);
        root.getChildren().add(controls);
        root.getChildren().add(placement);
        /* draw the board, controls and placement */

        drawBoard();
        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}