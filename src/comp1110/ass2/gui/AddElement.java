package comp1110.ass2.gui;

import comp1110.ass2.Metro;
import comp1110.ass2.Placement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddElement {
    private static final int SQUARE_SIZE = 70;
    private static final String URI_BASE = "assets/";

    public static void addText(Group root, String text, Double layoutX, Double layoutY, int fontSize, Color color) {
        Text text1 = new Text(text);
        text1.setLayoutX(layoutX * SQUARE_SIZE);
        text1.setLayoutY(layoutY * SQUARE_SIZE);
        text1.setFill(color);
        text1.setFont(Font.font("Segoe UI", fontSize));
        root.getChildren().add(text1);
    }

    public static void addGameOver(Group root)
    {
        addText(root,"Game Over!!",3.0,5.0,120,Color.BLACK);
    }

    public static void addFlag(Group root, Group flagGroup,int turnIndex)
    {
        root.getChildren().remove(flagGroup);
        flagGroup.getChildren().clear();
        Rectangle r=new Rectangle(20,20);
        r.setFill(Color.LIGHTPINK);
        r.setLayoutX(11.5*SQUARE_SIZE);
        r.setLayoutY(1.3*(turnIndex+0.8)*SQUARE_SIZE);
        flagGroup.getChildren().add(r);
        root.getChildren().add(flagGroup);
    }
    public static void addScore(Group root, Group scoreGroup, String placement, int numberOfPlayer) {
        root.getChildren().remove(scoreGroup);
        scoreGroup.getChildren().clear();
        int[] scoreArray = Metro.getScore(placement, numberOfPlayer);
        for (int i = 0; i < numberOfPlayer; i++) {
            String scoreString = scoreArray[i] + "";
            addText(scoreGroup, scoreString, 12.3, 1.3 * (i + 1), 20, Color.DARKCYAN);
        }
        root.getChildren().add(scoreGroup);
    }

    public static String stringArrayToString(String[] StringArray) {
        StringBuilder string = new StringBuilder();
        for (String s : StringArray) {
            if (s != null)
                string.append(s);
        }
        return string.toString();
    }


    public static ArrayList<Rectangle> getExistingRectangle() {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (int row = 1; row < 9; row++) {
            for (int col = 1; col < 9; col++) {
                if (!((row == 4 && col == 4) || (row == 4 && col == 5) || (row == 5 && col == 4) || (row == 5 && col == 5))) {
                    Rectangle rectangle = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
                    rectangle.setLayoutX(col * SQUARE_SIZE);
                    rectangle.setLayoutY(row * SQUARE_SIZE);
                    rectangle.setStrokeWidth(1.0);
                    rectangle.setStroke(Color.WHITE);
                    rectangle.setFill(Color.rgb(0, 0, 0, 0.08));
                    rectangles.add(rectangle);
                }
            }
        }
        return rectangles;
    }

    public static Rectangle cloestRectangle(double x, double y,ArrayList<String > validPlace) {
        Rectangle cloest = null;
        ArrayList<Rectangle> rectangles = getValidRectangles(validPlace);
        double closestDistance = Double.MAX_VALUE;
        for (Rectangle rectangle : rectangles) {
            double tx = rectangle.getLayoutX();
            double ty = rectangle.getLayoutY();
            double dx = tx - x;
            double dy = ty - y;
            double distanceSquare = Math.pow(dx, 2) + Math.pow(dy, 2);
            if (distanceSquare < closestDistance) {
                closestDistance = distanceSquare;
                cloest = rectangle;
            }
        }
        return cloest;
    }

    public static ArrayList<Rectangle> getValidRectangles(ArrayList<String> validPlace)
    {
        ArrayList<Rectangle> rectangles=new ArrayList<>();
        for (String each : validPlace) {
            int row = Integer.parseInt(each.substring(0, 1)) + 1;
            int col = Integer.parseInt(each.substring(1, 2)) + 1;
            Rectangle r = new Rectangle();
            r.setHeight(SQUARE_SIZE);
            r.setWidth(SQUARE_SIZE);
            r.setLayoutX(col * SQUARE_SIZE);
            r.setLayoutY(row * SQUARE_SIZE);
            r.setFill(Color.LIGHTPINK);
            r.setOpacity(0.5);
            rectangles.add(r);
        }
        return rectangles;
    }
    public static void drawValidRectangles(Group validRectangle, ArrayList<String> validPlace) {
        for (String each : validPlace) {
            int row = Integer.parseInt(each.substring(0, 1)) + 1;
            int col = Integer.parseInt(each.substring(1, 2)) + 1;
            Rectangle r = new Rectangle();
            r.setHeight(SQUARE_SIZE);
            r.setWidth(SQUARE_SIZE);
            r.setLayoutX(col * SQUARE_SIZE);
            r.setLayoutY(row * SQUARE_SIZE);
            r.setFill(Color.LIGHTPINK);
            r.setOpacity(0.5);
            validRectangle.getChildren().add(r);
        }
    }

    public static class DraggableRectangle extends ImageView {
        double mouseX;
        double mouseY;

        public DraggableRectangle(Group root, String tile, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, int index,Group scoreGroup,Group flagGroup) {
            Button button = new Button("Draw again");
            button.setStyle("-fx-base: pink;");
            Image image = new Image(this.getClass().getResource(URI_BASE + tile + ".jpg").toString());
            this.setFitHeight(SQUARE_SIZE);
            this.setFitWidth(SQUARE_SIZE);
            this.setImage(image);
            this.setLayoutX(11 * SQUARE_SIZE);
            this.setLayoutY(8.5 * SQUARE_SIZE);
            this.setOnMousePressed(event -> {
                mouseX = this.getLayoutX() - event.getSceneX();
                mouseY = this.getLayoutY() - event.getSceneY();
                ArrayList<String> validPlace = Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer);
                if (validPlace.size()==0)
                {
                    addGameOver(root);
                }
                drawValidRectangles(validRectangle, validPlace);
            });

            this.setOnMouseDragged(event ->
            {
                this.setLayoutX(event.getSceneX() + mouseX);
                this.setLayoutY(event.getSceneY() + mouseY);
                root.getChildren().remove(button);
            });

            this.setOnMouseReleased(event ->
            {
                Rectangle closest = cloestRectangle(this.getLayoutX(), this.getLayoutY(),Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer));
                this.setLayoutX(closest.getLayoutX());
                this.setLayoutY(closest.getLayoutY());
                validRectangle.getChildren().clear();
                ImageView image1 = new ImageView(this.getClass().getResource(URI_BASE + tile + ".jpg").toString());
                image1.setLayoutX(closest.getLayoutX());
                image1.setLayoutY(closest.getLayoutY());
                image1.setFitWidth(SQUARE_SIZE);
                image1.setFitHeight(SQUARE_SIZE);
                root.getChildren().add(image1);
                totalHandArray[index] = null;
                this.setOnMousePressed(null);
                this.setOnMouseDragged(null);
                this.setOnMouseReleased(null);
                String row = ((int) (this.getLayoutY() / SQUARE_SIZE - 1)) + "";
                String col = ((int) (this.getLayoutX() / SQUARE_SIZE - 1)) + "";
                placementStringBuilder.append(tile);
                placementStringBuilder.append(row);
                placementStringBuilder.append(col);
                System.out.println(placementStringBuilder.toString());
                int turnIndex = (placementStringBuilder.length() / 6) % numberOfPlayer;
                addFlag(root,flagGroup,turnIndex);
                if (totalHandArray[turnIndex] != null) {
                    System.out.println(totalHandArray[turnIndex] + "hahaha");
                    root.getChildren().add(new AddElement.DraggableRectangle(root, totalHandArray[turnIndex], placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, turnIndex,scoreGroup,flagGroup));
                }
                addScore(root,scoreGroup,placementStringBuilder.toString(),numberOfPlayer);
            });


            String drawnTile = Metro.drawFromDeck(placementStringBuilder.toString(), AddElement.stringArrayToString(totalHandArray));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    validRectangle.getChildren().clear();
                    DraggableRectangle2 a = new DraggableRectangle2(root, drawnTile, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, index, button, scoreGroup,flagGroup);
                    root.getChildren().add(a);
                }
            });
            button.setLayoutX(12.5 * SQUARE_SIZE);
            button.setLayoutY(10 * SQUARE_SIZE);
            root.getChildren().add(button);
        }
    }

    public static class DraggableRectangle2 extends ImageView {
        double mouseX;
        double mouseY;

        public DraggableRectangle2(Group root, String tile, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, int index, Button button,Group scoreGroup,Group flagGroup) {
            Image image = new Image(this.getClass().getResource(URI_BASE + tile + ".jpg").toString());
            this.setFitHeight(SQUARE_SIZE);
            this.setFitWidth(SQUARE_SIZE);
            this.setImage(image);
            this.setLayoutX(12.5 * SQUARE_SIZE);
            this.setLayoutY(8.5 * SQUARE_SIZE);
            this.setOnMousePressed(event -> {
                mouseX = this.getLayoutX() - event.getSceneX();
                mouseY = this.getLayoutY() - event.getSceneY();
                ArrayList<String> validPlace = Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer);
                if (validPlace.size()==0)
                {
                    addGameOver(root);
                }
                drawValidRectangles(validRectangle, validPlace);
            });

            this.setOnMouseDragged(event ->
            {
                this.setLayoutX(event.getSceneX() + mouseX);
                this.setLayoutY(event.getSceneY() + mouseY);
                root.getChildren().remove(button);
            });

            this.setOnMouseReleased(event ->
            {
                Rectangle closest = cloestRectangle(this.getLayoutX(), this.getLayoutY(),Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer));
                this.setLayoutX(closest.getLayoutX());
                this.setLayoutY(closest.getLayoutY());
                validRectangle.getChildren().clear();
                this.setOnMousePressed(null);
                this.setOnMouseDragged(null);
                this.setOnMouseReleased(null);
                String row = ((int) (this.getLayoutY() / SQUARE_SIZE - 1)) + "";
                String col = ((int) (this.getLayoutX() / SQUARE_SIZE - 1)) + "";
                placementStringBuilder.append(tile);
                placementStringBuilder.append(row);
                placementStringBuilder.append(col);
                System.out.println(placementStringBuilder.toString());
                int turnIndex = (placementStringBuilder.length() / 6) % numberOfPlayer;
                addScore(root,scoreGroup,placementStringBuilder.toString(),numberOfPlayer);
                addFlag(root,flagGroup,turnIndex);
                if (totalHandArray[turnIndex] != null) {
                    System.out.println(totalHandArray[turnIndex] + "hahaha");
                    root.getChildren().add(new AddElement.DraggableRectangle(root, totalHandArray[turnIndex], placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, turnIndex,scoreGroup,flagGroup));
                }

            });

        }
    }


}
