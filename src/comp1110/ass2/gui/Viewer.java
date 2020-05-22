package comp1110.ass2.gui;

import comp1110.ass2.Deck;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    private final Group root1 = new Group();
    private final Group root2 = new Group();
    private final Group root3 = new Group();
    private final Group root4 = new Group();
    private final Group root5 = new Group();
    private final Group root6 = new Group();
    private final Group rootComp = new Group();
    private final Group board = new Group();
    private final Group controls = new Group();
    private final Group placement = new Group();
    private TextField textField;


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

        //Current Tile Label
        Label currentTileCaption = new Label("Current Tile:");
        currentTileCaption.setLayoutX(SQUARE_SIZE*10.5);
        currentTileCaption.setLayoutY(SQUARE_SIZE*7);
        controls.getChildren().add(currentTileCaption);
    }

    void player2(){
        Label label1 = new Label("Player 1");

        Button button11 = new Button("hand");
        button11.setOnAction(e -> {
        });
        Button button12 = new Button("draw");
        button12.setOnAction(e -> {
            drawTile();
        });
        Button button13 = new Button("place");
        button13.setOnAction(e -> {
        });

        HBox p1 = new HBox();
        p1.getChildren().addAll(label1, button11, button12, button13);
        p1.setSpacing(20);
        p1.setLayoutX(SQUARE_SIZE*10.5);
        p1.setLayoutY(SQUARE_SIZE);
        root2.getChildren().add(p1);

        Label label2 = new Label("Player 2");

        Button button21 = new Button("hand");
        button21.setOnAction(e -> {
        });
        Button button22 = new Button("draw");
        button22.setOnAction(e -> {
            drawTile();
        });
        Button button23 = new Button("place");
        button23.setOnAction(e -> {
        });

        HBox p2 = new HBox();
        p2.getChildren().addAll(label2, button21, button22, button23);
        p2.setSpacing(20);
        p2.setLayoutX(SQUARE_SIZE*10.5);
        p2.setLayoutY(SQUARE_SIZE * 2);
        root2.getChildren().add(p2);
    }

    void player3(){
        Label label1 = new Label("Player 1");

        Button button11 = new Button("hand");
        button11.setOnAction(e -> {
        });
        Button button12 = new Button("draw");
        button12.setOnAction(e -> {
            drawTile();
        });
        Button button13 = new Button("place");
        button13.setOnAction(e -> {
        });

        HBox p1 = new HBox();
        p1.getChildren().addAll(label1, button11, button12, button13);
        p1.setSpacing(20);
        p1.setLayoutX(SQUARE_SIZE*10.5);
        p1.setLayoutY(SQUARE_SIZE);
        root3.getChildren().add(p1);

        Label label2 = new Label("Player 2");

        Button button21 = new Button("hand");
        button21.setOnAction(e -> {
        });
        Button button22 = new Button("draw");
        button22.setOnAction(e -> {
            drawTile();
        });
        Button button23 = new Button("place");
        button23.setOnAction(e -> {
        });

        HBox p2 = new HBox();
        p2.getChildren().addAll(label2, button21, button22, button23);
        p2.setSpacing(20);
        p2.setLayoutX(SQUARE_SIZE*10.5);
        p2.setLayoutY(SQUARE_SIZE * 2);
        root3.getChildren().add(p2);

        Label label3 = new Label("Player 3");

        Button button31 = new Button("hand");
        button31.setOnAction(e -> {
        });
        Button button32 = new Button("draw");
        button32.setOnAction(e -> {
            drawTile();
        });
        Button button33 = new Button("place");
        button33.setOnAction(e -> {
        });

        HBox p3 = new HBox();
        p3.getChildren().addAll(label3, button31, button32, button33);
        p3.setSpacing(20);
        p3.setLayoutX(SQUARE_SIZE*10.5);
        p3.setLayoutY(SQUARE_SIZE * 3);
        root3.getChildren().add(p3);
    }

    void player4(){
        Label label1 = new Label("Player 1");

        Button button11 = new Button("hand");
        button11.setOnAction(e -> {
        });
        Button button12 = new Button("draw");
        button12.setOnAction(e -> {
            drawTile();
        });
        Button button13 = new Button("place");
        button13.setOnAction(e -> {
        });

        HBox p1 = new HBox();
        p1.getChildren().addAll(label1, button11, button12, button13);
        p1.setSpacing(20);
        p1.setLayoutX(SQUARE_SIZE*10.5);
        p1.setLayoutY(SQUARE_SIZE);
        root4.getChildren().add(p1);

        Label label2 = new Label("Player 2");

        Button button21 = new Button("hand");
        button21.setOnAction(e -> {
        });
        Button button22 = new Button("draw");
        button22.setOnAction(e -> {
            drawTile();
        });
        Button button23 = new Button("place");
        button23.setOnAction(e -> {
        });

        HBox p2 = new HBox();
        p2.getChildren().addAll(label2, button21, button22, button23);
        p2.setSpacing(20);
        p2.setLayoutX(SQUARE_SIZE*10.5);
        p2.setLayoutY(SQUARE_SIZE * 2);
        root4.getChildren().add(p2);

        Label label3 = new Label("Player 3");

        Button button31 = new Button("hand");
        button31.setOnAction(e -> {
        });
        Button button32 = new Button("draw");
        button32.setOnAction(e -> {
            drawTile();
        });
        Button button33 = new Button("place");
        button33.setOnAction(e -> {
        });

        HBox p3 = new HBox();
        p3.getChildren().addAll(label3, button31, button32, button33);
        p3.setSpacing(20);
        p3.setLayoutX(SQUARE_SIZE*10.5);
        p3.setLayoutY(SQUARE_SIZE * 3);
        root4.getChildren().add(p3);

        Label label4 = new Label("Player 4");

        Button button41 = new Button("hand");
        button41.setOnAction(e -> {
        });
        Button button42 = new Button("draw");
        button42.setOnAction(e -> {
            drawTile();
        });
        Button button43 = new Button("place");
        button43.setOnAction(e -> {
        });

        HBox p4 = new HBox();
        p4.getChildren().addAll(label4, button41, button42, button43);
        p4.setSpacing(20);
        p4.setLayoutX(SQUARE_SIZE*10.5);
        p4.setLayoutY(SQUARE_SIZE * 4);
        root4.getChildren().add(p4);
    }

    void player5(){
        Label label1 = new Label("Player 1");

        Button button11 = new Button("hand");
        button11.setOnAction(e -> {
        });
        Button button12 = new Button("draw");
        button12.setOnAction(e -> {
            drawTile();
        });
        Button button13 = new Button("place");
        button13.setOnAction(e -> {
        });

        HBox p1 = new HBox();
        p1.getChildren().addAll(label1, button11, button12, button13);
        p1.setSpacing(20);
        p1.setLayoutX(SQUARE_SIZE*10.5);
        p1.setLayoutY(SQUARE_SIZE);
        root5.getChildren().add(p1);

        Label label2 = new Label("Player 2");

        Button button21 = new Button("hand");
        button21.setOnAction(e -> {
        });
        Button button22 = new Button("draw");
        button22.setOnAction(e -> {
            drawTile();
        });
        Button button23 = new Button("place");
        button23.setOnAction(e -> {
        });

        HBox p2 = new HBox();
        p2.getChildren().addAll(label2, button21, button22, button23);
        p2.setSpacing(20);
        p2.setLayoutX(SQUARE_SIZE*10.5);
        p2.setLayoutY(SQUARE_SIZE * 2);
        root5.getChildren().add(p2);

        Label label3 = new Label("Player 3");

        Button button31 = new Button("hand");
        button31.setOnAction(e -> {
        });
        Button button32 = new Button("draw");
        button32.setOnAction(e -> {
            drawTile();
        });
        Button button33 = new Button("place");
        button33.setOnAction(e -> {
        });

        HBox p3 = new HBox();
        p3.getChildren().addAll(label3, button31, button32, button33);
        p3.setSpacing(20);
        p3.setLayoutX(SQUARE_SIZE*10.5);
        p3.setLayoutY(SQUARE_SIZE * 3);
        root5.getChildren().add(p3);

        Label label4 = new Label("Player 4");

        Button button41 = new Button("hand");
        button41.setOnAction(e -> {
        });
        Button button42 = new Button("draw");
        button42.setOnAction(e -> {
            drawTile();
        });
        Button button43 = new Button("place");
        button43.setOnAction(e -> {
        });

        HBox p4 = new HBox();
        p4.getChildren().addAll(label4, button41, button42, button43);
        p4.setSpacing(20);
        p4.setLayoutX(SQUARE_SIZE*10.5);
        p4.setLayoutY(SQUARE_SIZE * 4);
        root5.getChildren().add(p4);

        Label label5 = new Label("Player 5");

        Button button51 = new Button("hand");
        button51.setOnAction(e -> {
        });
        Button button52 = new Button("draw");
        button52.setOnAction(e -> {
            drawTile();
        });
        Button button53 = new Button("place");
        button53.setOnAction(e -> {
        });

        HBox p5 = new HBox();
        p5.getChildren().addAll(label5, button51, button52, button53);
        p5.setSpacing(20);
        p5.setLayoutX(SQUARE_SIZE*10.5);
        p5.setLayoutY(SQUARE_SIZE * 5);
        root5.getChildren().add(p5);
    }

    void player6(){
        Label label1 = new Label("Player 1");

        Button button11 = new Button("hand");
        button11.setOnAction(e -> {
        });
        Button button12 = new Button("draw");
        button12.setOnAction(e -> {
            drawTile();
        });
        Button button13 = new Button("place");
        button13.setOnAction(e -> {
        });

        HBox p1 = new HBox();
        p1.getChildren().addAll(label1, button11, button12, button13);
        p1.setSpacing(20);
        p1.setLayoutX(SQUARE_SIZE*10.5);
        p1.setLayoutY(SQUARE_SIZE);
        root6.getChildren().add(p1);

        Label label2 = new Label("Player 2");

        Button button21 = new Button("hand");
        button21.setOnAction(e -> {
        });
        Button button22 = new Button("draw");
        button22.setOnAction(e -> {
            drawTile();
        });
        Button button23 = new Button("place");
        button23.setOnAction(e -> {
        });

        HBox p2 = new HBox();
        p2.getChildren().addAll(label2, button21, button22, button23);
        p2.setSpacing(20);
        p2.setLayoutX(SQUARE_SIZE*10.5);
        p2.setLayoutY(SQUARE_SIZE * 2);
        root6.getChildren().add(p2);

        Label label3 = new Label("Player 3");

        Button button31 = new Button("hand");
        button31.setOnAction(e -> {
        });
        Button button32 = new Button("draw");
        button32.setOnAction(e -> {
            drawTile();
        });
        Button button33 = new Button("place");
        button33.setOnAction(e -> {
        });

        HBox p3 = new HBox();
        p3.getChildren().addAll(label3, button31, button32, button33);
        p3.setSpacing(20);
        p3.setLayoutX(SQUARE_SIZE*10.5);
        p3.setLayoutY(SQUARE_SIZE * 3);
        root6.getChildren().add(p3);

        Label label4 = new Label("Player 4");

        Button button41 = new Button("hand");
        button41.setOnAction(e -> {
        });
        Button button42 = new Button("draw");
        button42.setOnAction(e -> {
            drawTile();
        });
        Button button43 = new Button("place");
        button43.setOnAction(e -> {
        });

        HBox p4 = new HBox();
        p4.getChildren().addAll(label4, button41, button42, button43);
        p4.setSpacing(20);
        p4.setLayoutX(SQUARE_SIZE*10.5);
        p4.setLayoutY(SQUARE_SIZE * 4);
        root6.getChildren().add(p4);

        Label label5 = new Label("Player 5");

        Button button51 = new Button("hand");
        button51.setOnAction(e -> {
        });
        Button button52 = new Button("draw");
        button52.setOnAction(e -> {
            drawTile();
        });
        Button button53 = new Button("place");
        button53.setOnAction(e -> {
        });

        HBox p5 = new HBox();
        p5.getChildren().addAll(label5, button51, button52, button53);
        p5.setSpacing(20);
        p5.setLayoutX(SQUARE_SIZE*10.5);
        p5.setLayoutY(SQUARE_SIZE * 5);
        root6.getChildren().add(p5);

        Label label6 = new Label("Player 6");

        Button button61 = new Button("hand");
        button61.setOnAction(e -> {
        });
        Button button62 = new Button("draw");
        button62.setOnAction(e -> {
            drawTile();
        });
        Button button63 = new Button("place");
        button63.setOnAction(e -> {
        });

        HBox p6 = new HBox();
        p6.getChildren().addAll(label6, button61, button62, button63);
        p6.setSpacing(20);
        p6.setLayoutX(SQUARE_SIZE*10.5);
        p6.setLayoutY(SQUARE_SIZE * 6);
        root6.getChildren().add(p6);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metro Game Viewer");

        //Welcome Scene1
        ImageView background = new ImageView();
        background.setImage(new Image(this.getClass().getResource(URI_BASE + "tile_back_cover.jpg").toString()));
        background.setFitWidth(VIEWER_HEIGHT);
        background.setFitHeight(VIEWER_HEIGHT);
        root1.getChildren().add(background);

        Text welcome = new Text("Welcome!");
        welcome.setFont(Font.font("Segoe UI", FontWeight.BOLD, 50));
        welcome.setStyle("-fx-text-fill: crimson");
        welcome.setOpacity(0.5);
        welcome.setLayoutX(SQUARE_SIZE*11.1);
        welcome.setLayoutY(SQUARE_SIZE);
        root1.getChildren().add(welcome);

        Text instruction = new Text("Please choose the number");
        instruction.setFont(Font.font("Segoe UI", 20));
        instruction.setOpacity(0.2);
        instruction.setLayoutX(SQUARE_SIZE*11.1);
        instruction.setLayoutY(SQUARE_SIZE*1.8);
        root1.getChildren().add(instruction);

        Text instruction2 = new Text("of the players to start.");
        instruction2.setFont(Font.font("Segoe UI", 20));
        instruction2.setOpacity(0.2);
        instruction2.setLayoutX(SQUARE_SIZE*11.1);
        instruction2.setLayoutY(SQUARE_SIZE*2.3);
        root1.getChildren().add(instruction2);

        Button button2 = new Button("2 Players");
        button2.setOnAction(e -> {
            Stage2();
            primaryStage.close();
        });
        button2.setFont(Font.font("Segoe UI",15));

        Button button3 = new Button("3 Players");
        button3.setOnAction(e -> {
            Stage3();
            primaryStage.close();
        });
        button3.setFont(Font.font("Segoe UI", 15));

        Button button4 = new Button("4 Players");
        button4.setOnAction(e -> {
            Stage4();
            primaryStage.close();
        });
        button4.setFont(Font.font("Segoe UI", 15));

        Button button5= new Button("5 Players");
        button5.setOnAction(e -> {
            Stage5();
            primaryStage.close();
        });
        button5.setFont(Font.font("Segoe UI", 15));

        Button button6= new Button("6 Players");
        button6.setOnAction(e -> {
            Stage6();
            primaryStage.close();
        });
        button6.setFont(Font.font("Segoe UI", 15));

        Button buttonComp= new Button("AI Player");
        buttonComp.setOnAction(e -> {
            StageComp();
            primaryStage.close();
        });
        buttonComp.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        buttonComp.setStyle("-fx-text-fill: crimson");

        VBox vb = new VBox();
        vb.getChildren().addAll(button2, button3, button4, button5, button6, buttonComp);
        vb.setSpacing(SQUARE_SIZE*0.8);
        vb.setLayoutX(SQUARE_SIZE * 12.2);
        vb.setLayoutY(SQUARE_SIZE * 3);
        root1.getChildren().add(vb);

        Scene scene1 = new Scene(root1, VIEWER_WIDTH, VIEWER_HEIGHT);

        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public void Stage2(){
        Stage stage2 = new Stage();
        stage2.setTitle("Metro: 2 Human Players");

        drawBoard();
        makeControls();

        //Game Restart Button
        Button buttonX = new Button("Game Restart");
        buttonX.setStyle("-fx-text-fill: red");
        buttonX.setOnAction(e -> {
            Viewer viewer = new Viewer();
            viewer.start(new Stage());
            stage2.close();
        });
        buttonX.setLayoutX(SQUARE_SIZE*12);
        buttonX.setLayoutY(0);
        controls.getChildren().add(buttonX);

        //2 Players scene2
        Scene scene = new Scene(root2, VIEWER_WIDTH, VIEWER_HEIGHT);

        root2.getChildren().add(board);
        root2.getChildren().add(controls);
        root2.getChildren().add(placement);

        player2();

        stage2.setScene(scene);
        stage2.show();
    }

    public void Stage3(){
        Stage stage3 = new Stage();
        stage3.setTitle("Metro: 3 Human Players");

        drawBoard();
        makeControls();

        //Game Restart Button
        Button buttonX = new Button("Game Restart");
        buttonX.setStyle("-fx-text-fill: red");
        buttonX.setOnAction(e -> {
            Viewer viewer = new Viewer();
            viewer.start(new Stage());
            stage3.close();
        });
        buttonX.setLayoutX(SQUARE_SIZE*12);
        buttonX.setLayoutY(0);
        controls.getChildren().add(buttonX);


        //3 Players scene3
        Scene scene = new Scene(root3, VIEWER_WIDTH, VIEWER_HEIGHT);

        player3();

        root3.getChildren().add(board);
        root3.getChildren().add(controls);
        root3.getChildren().add(placement);

        stage3.setScene(scene);
        stage3.show();
    }

    public void Stage4(){
        Stage stage4 = new Stage();
        stage4.setTitle("Metro: 4 Human Players");

        drawBoard();
        makeControls();

        //Game Restart Button
        Button buttonX = new Button("Game Restart");
        buttonX.setStyle("-fx-text-fill: red");
        buttonX.setOnAction(e -> {
            Viewer viewer = new Viewer();
            viewer.start(new Stage());
            stage4.close();
        });
        buttonX.setLayoutX(SQUARE_SIZE*12);
        buttonX.setLayoutY(0);
        controls.getChildren().add(buttonX);


        //4 Players scene
        Scene scene = new Scene(root4, VIEWER_WIDTH, VIEWER_HEIGHT);

        player4();

        root4.getChildren().add(board);
        root4.getChildren().add(controls);
        root4.getChildren().add(placement);

        stage4.setScene(scene);
        stage4.show();
    }

    public void Stage5(){
        Stage stage5 = new Stage();
        stage5.setTitle("Metro: 5 Human Players");

        drawBoard();
        makeControls();

        //Game Restart Button
        Button buttonX = new Button("Game Restart");
        buttonX.setStyle("-fx-text-fill: red");
        buttonX.setOnAction(e -> {
            Viewer viewer = new Viewer();
            viewer.start(new Stage());
            stage5.close();
        });
        buttonX.setLayoutX(SQUARE_SIZE*12);
        buttonX.setLayoutY(0);
        controls.getChildren().add(buttonX);


        //5 Players scene
        Scene scene = new Scene(root5, VIEWER_WIDTH, VIEWER_HEIGHT);

        player5();

        root5.getChildren().add(board);
        root5.getChildren().add(controls);
        root5.getChildren().add(placement);

        stage5.setScene(scene);
        stage5.show();
    }

    public void Stage6(){
        Stage stage6 = new Stage();
        stage6.setTitle("Metro: 6 Human Players");

        drawBoard();
        makeControls();

        //Game Restart Button
        Button buttonX = new Button("Game Restart");
        buttonX.setStyle("-fx-text-fill: red");
        buttonX.setOnAction(e -> {
            Viewer viewer = new Viewer();
            viewer.start(new Stage());
            stage6.close();
        });
        buttonX.setLayoutX(SQUARE_SIZE*12);
        buttonX.setLayoutY(0);
        controls.getChildren().add(buttonX);


        //6 Players scene
        Scene scene = new Scene(root6, VIEWER_WIDTH, VIEWER_HEIGHT);

        player6();

        root6.getChildren().add(board);
        root6.getChildren().add(controls);
        root6.getChildren().add(placement);

        stage6.setScene(scene);
        stage6.show();
    }

    public void StageComp(){
    }
}