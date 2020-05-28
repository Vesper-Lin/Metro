package comp1110.ass2.gui;

import comp1110.ass2.Metro;
import comp1110.ass2.Placement;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is supplementary for Viewer.Class and contains many useful methods working for visualising the game.
 *
 * @author Jiawei Fan
 */
public class AddElement {
    private static final int SQUARE_SIZE = 70;
    private static final String URI_BASE = "assets/";

    /**
     * This method is written to make it eaiser to add text group. By passing needed parameters,
     * text group can be added to the children of specific group.
     *
     * @param root     the root group which add the text group as children
     * @param text     a String represnting the text information
     * @param layoutX  X coordinate of the text group
     * @param layoutY  y coordinate of the text group
     * @param fontSize font size of the text
     * @param color    color of the text
     * @author Jiawei Fan
     */
    public static void addText(Group root, String text, Double layoutX, Double layoutY, int fontSize, Color color) {
        Text text1 = new Text(text);
        text1.setLayoutX(layoutX * SQUARE_SIZE);
        text1.setLayoutY(layoutY * SQUARE_SIZE);
        text1.setFill(color);
        text1.setFont(Font.font("Segoe UI", fontSize));
        root.getChildren().add(text1);
    }

    /**
     * This method is to add the game over text, it will be used when there is no more
     * tile in the deck. The code only contains an addText method, the class name is
     * just to make it more clear.
     *
     * @param root root group
     * @author Jiawei Fan
     */
    public static void addGameOver(Group root) {
        addText(root, "Game Over!!", 3.0, 5.0, 120, Color.BLACK);
    }

    /**
     * This method is to add a flag to indicate this is which player's turn of placing
     * tile, the flag is designed to be a pink rectangle which is placed next to the
     * player.
     *
     * @param root      root Group which adds the flag as children
     * @param flagGroup Flag group which conntains the flag
     * @param turnIndex an int represting the turn
     * @author Jiawei Fan
     */
    public static void addFlag(Group root, Group flagGroup, int turnIndex) {
        root.getChildren().remove(flagGroup);//firstly remove this group,
        flagGroup.getChildren().clear();//reomove the children, this line of code might not be useful, but just to ensure flag group childen has been removed,
        Rectangle r = new Rectangle(20, 20);
        r.setFill(Color.LIGHTPINK);
        r.setLayoutX(11.5 * SQUARE_SIZE);
        r.setLayoutY(1.3 * (turnIndex + 0.8) * SQUARE_SIZE);
        flagGroup.getChildren().add(r);
        root.getChildren().add(flagGroup);
    }

    /**
     * This method is to add the score information. Everytime a player places a
     * tile, this method will be called to update the scores of each player.
     *
     * @param root           root group which contains the score group
     * @param scoreGroup     scoreGroup which contains several score text
     * @param placement      a string representing the placement
     * @param numberOfPlayer a int representing the number of player
     * @author Jiawei Fan
     */
    public static void addScore(Group root, Group scoreGroup, String placement, int numberOfPlayer) {
        root.getChildren().remove(scoreGroup);//Firstly remove the score Group
        scoreGroup.getChildren().clear();
        int[] scoreArray = Metro.getScore(placement, numberOfPlayer);
        for (int i = 0; i < numberOfPlayer; i++) {
            String scoreString = scoreArray[i] + "";
            addText(scoreGroup, scoreString, 12.3, 1.3 * (i + 1), 20, Color.DARKCYAN);
        }
        root.getChildren().add(scoreGroup);
    }

    /**
     * A simple method transform a string array to a string.
     *
     * @param stringArray the string array to be transformed
     * @return A string which is transformed from the string array
     * @author Jiawei Fan
     */
    public static String stringArrayToString(String[] stringArray) {
        StringBuilder string = new StringBuilder();
        for (String s : stringArray) {
            if (s != null)
                string.append(s);
        }
        return string.toString();
    }

    /**
     * This method is orinally to get every rectangle place on the board,
     * but later another method is used to get the valid rectangle place of the
     * tile, then this method is used to draw the board instead.
     *
     * @return all the rectangles
     * @author Jiawei Fan
     */
    public static ArrayList<Rectangle> getExistingRectangle() {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (int row = 1; row < 9; row++) {
            for (int col = 1; col < 9; col++) {
                if (!((row == 4 && col == 4) || (row == 4 && col == 5) || (row == 5 && col == 4) || (row == 5 && col == 5))) {//centre stations are not drawn
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

    /**
     * This method is used to get the cloest rectangle by passing a coordinate and the valid places.
     * This method is written according to the learning from the triangle example in the tutorial.
     *
     * @param x          a double representing the x coordinate
     * @param y          a double representing the y coordinate
     * @param validPlace the coordinate of valid places which tile can be put on. Each valid place is represented by a string of length 2.
     * @return The closet Rectangle
     * @author Jiawei Fan
     */
    public static Rectangle cloestRectangle(double x, double y, ArrayList<String> validPlace) {
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

    /**
     * This method is used to get the array of rectangles based an array of valid coordinates,
     * validPlace a array of string, each string is of length two, representing the coordinates
     * of this valid places.
     *
     * @param validPlace an Arraylist representing the valid coordinates
     * @return An arraylist of rectangles which match with the valid places
     * @author Jiawei Fan
     */
    public static ArrayList<Rectangle> getValidRectangles(ArrayList<String> validPlace) {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (String each : validPlace) {
            int row = Integer.parseInt(each.substring(0, 1)) + 1;//the difference between the valid place coordinate and the actual row in the screen is 1
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

    /**
     * This method is to draw the valid rectangles, which is the valid places where a tile can be put
     * on when player is placing it. Therefore, players can visualise the valid places to place the tile.
     *
     * @param validRectangle a group which used to contains valid rectangles
     * @param validPlace     Arraylist of valid places
     * @author Jiawei Fan
     */
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

    /**
     * This method is used to help AI decide which place is the best to place a tile to get the highest score
     * We assume an additional score of 5 to be a good placement, so if the placing the tile can get more than
     * 5 score, it is a good placement. This number is decided by doing a lot of tests, and averaging the result.
     *
     * @param root                   root group
     * @param drawnTile              the drawn tile which is going to be placed
     * @param placementStringBuilder a StringBuilder which contains the placement information
     * @param numberOfPlayer         a int representing the number of player
     * @return a String representing the coordinate of the best placement
     * @author Jiawei Fan
     */
    public static String getHighestAdditionalScoreCoordinates(Group root, String drawnTile, StringBuilder placementStringBuilder, int numberOfPlayer) {
        int index = 0;
        ArrayList<String> validPlace = Placement.getFinalValidPlace(placementStringBuilder.toString(), drawnTile, numberOfPlayer);//get valid places to put the tile based on the current placement and the drawn tile
        int additionalScore = 0;
        int currentPlayerIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;
        int[] scoreArray = Metro.getScore(placementStringBuilder.toString(), numberOfPlayer);
        int currentScore = scoreArray[currentPlayerIndex];
        if (validPlace.size() == 0) {//game over if there is no valid place to place the tile
            addGameOver(root);
            return "";
        } else {
            for (int i = 0; i < validPlace.size(); i++) {
                String coordinate = validPlace.get(i);//test each coordinate
                String nextPlacement = placementStringBuilder.toString() + drawnTile + coordinate;//get the tested placement
                int nextMoveScore = Metro.getScore(nextPlacement, numberOfPlayer)[currentPlayerIndex];
                if (nextMoveScore - currentScore > additionalScore) {
                    additionalScore = nextMoveScore - currentScore;
                    index = i;
                }
            }
            if (additionalScore > 5) {//this is the decision point, if the highest additional score is greater than 5, then this is decided to be a good placement
                return validPlace.get(index);
            } else return "";//return "" if  additional score is less than 5
        }
    }

    /**
     * This method comes to the placement stage for the advanced AI player. This method will place the chosen tile to the board.
     * This method contains some animations which makes the placement process look nicer.
     *
     * @param root                       a root group
     * @param drawnTile                  the tile to be placed
     * @param placementStringBuilder     current placement string
     * @param numberOfPlayer             a int representing number of player
     * @param putTile                    ImageView of the drawn tile
     * @param additionalScoreCoordinates the coordinate of place where the drawn tile is going to be placed
     * @author Jiawei Fan
     */
    public void giveGoodPlacement(Group root, String drawnTile, StringBuilder placementStringBuilder, int numberOfPlayer, ImageView putTile, String additionalScoreCoordinates) {
        ArrayList<String> validPlace = Placement.getFinalValidPlace(placementStringBuilder.toString(), drawnTile, numberOfPlayer);
        if (additionalScoreCoordinates.equals("")) {
            givePlacement(root, drawnTile, placementStringBuilder, numberOfPlayer, putTile);
        } else {
            for (String coordinate : validPlace) {
                if (coordinate.equals(additionalScoreCoordinates)) {//firstly find the coordinate
                    int layoutX = Integer.parseInt(coordinate.substring(1, 2)) + 1;
                    int layoutY = Integer.parseInt(coordinate.substring(0, 1)) + 1;
                    putTile.setLayoutX(11 * SQUARE_SIZE);//give a start point of the animation of placement
                    putTile.setLayoutY(8.5 * SQUARE_SIZE);
                    root.getChildren().add(putTile);
                    Path path = new Path();
                    path.getElements().add(new MoveTo(0, 0));
                    path.getElements().add(new LineTo(layoutX * SQUARE_SIZE - 11 * SQUARE_SIZE + 0.5 * SQUARE_SIZE, layoutY * SQUARE_SIZE - 8.5 * SQUARE_SIZE + 0.5 * SQUARE_SIZE));
                    PathTransition pathTransition = new PathTransition();
                    pathTransition.setDuration(Duration.seconds(2));
                    pathTransition.setNode(putTile);
                    pathTransition.setPath(path);
                    pathTransition.setCycleCount(1);
                    pathTransition.play();
                    placementStringBuilder.append(drawnTile);//change the placement to be a new one because new tile has been placed on board
                    placementStringBuilder.append(coordinate);
                    break;
                }
            }
        }
    }

    /**
     * This method is also used to give a placement to the board, but this method is for simple AI.
     * A simple AI will randomly choose a valid place to place the drawn tile.
     *
     * @param root                   root group
     * @param drawnTile              the tile to be placed
     * @param placementStringBuilder current placement information
     * @param numberOfPlayer         an int representing the numebr of integers
     * @param putTile                ImageView of the drawn tile
     * @author Jiawei Fan
     */
    public static void givePlacement(Group root, String drawnTile, StringBuilder placementStringBuilder, int numberOfPlayer, ImageView putTile) {
        ArrayList<String> validPlace = Placement.getFinalValidPlace(placementStringBuilder.toString(), drawnTile, numberOfPlayer);
        if (validPlace.size() == 0) {
            addGameOver(root);
        } else {
            String movement = Metro.generateMove(placementStringBuilder.toString(), drawnTile, numberOfPlayer);//randomly generate a momentment
            int layoutX = Integer.parseInt(movement.substring(5, 6)) + 1;
            int layoutY = Integer.parseInt(movement.substring(4, 5)) + 1;
            putTile.setLayoutX(11 * SQUARE_SIZE);
            putTile.setLayoutY(8.5 * SQUARE_SIZE);
            root.getChildren().add(putTile);
            Path path = new Path();
            path.getElements().add(new MoveTo(0, 0));
            path.getElements().add(new LineTo(layoutX * SQUARE_SIZE - 11 * SQUARE_SIZE + 0.5 * SQUARE_SIZE, layoutY * SQUARE_SIZE - 8.5 * SQUARE_SIZE + 0.5 * SQUARE_SIZE));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(2));
            pathTransition.setNode(putTile);
            pathTransition.setPath(path);
            pathTransition.setCycleCount(1);
            pathTransition.play();
            placementStringBuilder.append(movement);//update the placement string information
        }
    }

    /**
     * This class is the most important class for the game, which implements the functionalities that
     * tiles can be dragged from an initial place to the board, once user clicks draw button, this method
     * is called so that a new draggable tile is shown on the screen and can be dragged to the board.
     * All credits to Harriet who is the tutor of our group and a member of 2020 S1 COMP6710 teaching group,
     * Harriet demontrates the triangle example very clearly during the lab seesion, which helps me a lot in writing this class.
     * In this class, a litte bit recursive concept is applied so that the game can be running to the end.
     * Also, as for task 12,I give real player a chance to rotate the tile 90 degrees clockwise when they place a tile
     * they can not change it anymore in other turns because it will make no sense for the game if all tiles can be rotated at all time by all players.
     * Actullay, if the AI can click the mouse, their tile can be rotated, but they can't. It's a human win!
     * By the way. DraggableRectangle and DraggableRectangle2 are for all real players and DraggableRectangle3,DraggableRectangle4 are for playing with AI.
     * These 4 classes might can be simplified to one by adding a new sigature to the class, but i'm too busy
     * recently and have tried my best to complete all the tasks. It's sad my teammate did not help much.
     *
     * @author Jiawei Fan
     */
    public static class DraggableRectangle extends ImageView {
        double mouseX;
        double mouseY;

        public DraggableRectangle(Group root, String tile, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, int index, Button button1, Group scoreGroup, Group flagGroup) {
            validRectangle.getChildren().clear();
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
                ArrayList<String> validPlace = Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer);//get valid places that tiles can be put on
                if (validPlace.size() == 0) {
                    addGameOver(root);//game over if there is no more valid places
                }
                drawValidRectangles(validRectangle, validPlace);//draw the places that can be put on tiles, by the way, its color is in pink. One rule i made is that once user
                //drags the tile, the tile must be placed on board and once the tile is dragged, pink valid places are shown.
            });

            this.setOnMouseDragged(event ->
            {
                this.setLayoutX(event.getSceneX() + mouseX);
                this.setLayoutY(event.getSceneY() + mouseY);
                validRectangle.getChildren().remove(button);//once the tile is dragged, the draw button is removed
            });

            this.setOnMouseReleased(event ->
            {
                Rectangle closest = cloestRectangle(this.getLayoutX(), this.getLayoutY(), Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer));//find the cloest rectangle
                this.setLayoutX(closest.getLayoutX());
                this.setLayoutY(closest.getLayoutY());
                validRectangle.getChildren().clear();
                ImageView image1 = new ImageView(this.getClass().getResource(URI_BASE + tile + ".jpg").toString());//get the imageView of the tile
                image1.setLayoutX(closest.getLayoutX());
                image1.setLayoutY(closest.getLayoutY());
                image1.setFitWidth(SQUARE_SIZE);
                image1.setFitHeight(SQUARE_SIZE);
                root.getChildren().add(image1);
                totalHandArray[index] = null;//because the tile is already on the board
                image1.setOnMousePressed(event1 ->
                {
                    image1.setRotate(90.0);//this is for task 12,allow the user to rotate 90 degrees clockwise for each placement

                });
                this.setOnMousePressed(null);//once the tile is placed, ensure this tile can not be dragged more
                this.setOnMouseDragged(null);
                this.setOnMouseReleased(null);
                String row = ((int) (this.getLayoutY() / SQUARE_SIZE - 1)) + "";//-1 becase actual coordinate shown is 1 unit different to the placement coordinate
                String col = ((int) (this.getLayoutX() / SQUARE_SIZE - 1)) + "";
                placementStringBuilder.append(tile);
                placementStringBuilder.append(row);
                placementStringBuilder.append(col);
                int turnIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;//change turn index to the next player
                addFlag(root, flagGroup, turnIndex);//change flag to the next player
                if (totalHandArray[turnIndex] != null) {//if next player holds tiles on hand, kind of recursively using DraggableRectanble class
                    validRectangle.getChildren().add(new AddElement.DraggableRectangle(root, totalHandArray[turnIndex], placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, turnIndex, button1, scoreGroup, flagGroup));
                } else {
                    validRectangle.getChildren().add(button1);//if next player has no tiles on hand, add the draw button in so that the player can draw a new tile
                }
                addScore(root, scoreGroup, placementStringBuilder.toString(), numberOfPlayer);
            });


            String drawnTile = Metro.drawFromDeck(placementStringBuilder.toString(), AddElement.stringArrayToString(totalHandArray));//this tile is for draw again option
            if (drawnTile.equals("")) {//game over if there is no more tile
                addGameOver(root);
            }
            button.setOnAction(e -> {
                validRectangle.getChildren().clear();
                DraggableRectangle2 a = new DraggableRectangle2(root, drawnTile, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, button, scoreGroup, flagGroup);//once pressed draw again, this line executes
                validRectangle.getChildren().add(a);
            });
            button.setLayoutX(12.5 * SQUARE_SIZE);//"draw again" button layout
            button.setLayoutY(10 * SQUARE_SIZE);
            validRectangle.getChildren().add(button);
        }
    }

    /**
     * This class is is similiar to DraggableRectangle.class, the different is that this class is for the tile from the draw again option.
     * Readers can only read the lines that i comment because other ones are pretty the same as DraggableRectangle.class.
     *
     * @author Jiawei Fan
     */
    public static class DraggableRectangle2 extends ImageView {
        double mouseX;
        double mouseY;

        public DraggableRectangle2(Group root, String tile, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, Button button1, Button button, Group scoreGroup, Group flagGroup) {
            validRectangle.getChildren().clear();
            Image image = new Image(this.getClass().getResource(URI_BASE + tile + ".jpg").toString());
            this.setFitHeight(SQUARE_SIZE);
            this.setFitWidth(SQUARE_SIZE);
            this.setImage(image);
            this.setLayoutX(12.5 * SQUARE_SIZE);//different layout as the draggableRectangle
            this.setLayoutY(8.5 * SQUARE_SIZE);
            this.setOnMousePressed(event -> {
                mouseX = this.getLayoutX() - event.getSceneX();
                mouseY = this.getLayoutY() - event.getSceneY();
                ArrayList<String> validPlace = Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer);
                if (validPlace.size() == 0) {
                    addGameOver(root);
                }
                drawValidRectangles(validRectangle, validPlace);
            });

            this.setOnMouseDragged(event ->
            {
                this.setLayoutX(event.getSceneX() + mouseX);
                this.setLayoutY(event.getSceneY() + mouseY);
                validRectangle.getChildren().remove(button);
            });

            this.setOnMouseReleased(event ->
            {
                Rectangle closest = cloestRectangle(this.getLayoutX(), this.getLayoutY(), Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer));
                ImageView image1 = new ImageView(this.getClass().getResource(URI_BASE + tile + ".jpg").toString());
                image1.setLayoutX(closest.getLayoutX());
                image1.setLayoutY(closest.getLayoutY());
                image1.setFitWidth(SQUARE_SIZE);
                image1.setFitHeight(SQUARE_SIZE);
                root.getChildren().add(image1);
                this.setLayoutX(closest.getLayoutX());
                this.setLayoutY(closest.getLayoutY());
                validRectangle.getChildren().clear();
                image1.setOnMousePressed(event1 ->
                {
                    image1.setRotate(90.0);//this is for task 12,allow the user to rotate 90 degrees clockwise for each placement

                });
                this.setOnMousePressed(null);
                this.setOnMouseDragged(null);
                this.setOnMouseReleased(null);
                String row = ((int) (this.getLayoutY() / SQUARE_SIZE - 1)) + "";
                String col = ((int) (this.getLayoutX() / SQUARE_SIZE - 1)) + "";
                placementStringBuilder.append(tile);
                placementStringBuilder.append(row);
                placementStringBuilder.append(col);
                int turnIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;
                addScore(root, scoreGroup, placementStringBuilder.toString(), numberOfPlayer);
                addFlag(root, flagGroup, turnIndex);
                if (totalHandArray[turnIndex] != null) {
                    validRectangle.getChildren().clear();
                    validRectangle.getChildren().remove(button1);//remove the draw again button
                    validRectangle.getChildren().add(new AddElement.DraggableRectangle(root, totalHandArray[turnIndex], placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, turnIndex, button1, scoreGroup, flagGroup));//go to next player;s turn
                } else {
                    validRectangle.getChildren().add(button1);
                }

            });

        }
    }

    /**
     * This class is is similiar to DraggableRectangle.class and DraggableRectangle2.class. But this class is for playing with AI.
     * Note that player 1 is real player and other players are AI.
     * Readers can only read the lines that i comment because other ones are pretty the same as DraggableRectangle.class.
     *
     * @author Jiawei Fan
     */
    public static class DraggableRectangle3 extends ImageView {
        double mouseX;
        double mouseY;

        public DraggableRectangle3(Group root, String tile, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, int index, Button button1, Group scoreGroup, Group flagGroup, Boolean isAdvancedBot) {
            validRectangle.getChildren().clear();
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
                if (validPlace.size() == 0) {
                    addGameOver(root);
                }
                drawValidRectangles(validRectangle, validPlace);
            });

            this.setOnMouseDragged(event ->
            {
                this.setLayoutX(event.getSceneX() + mouseX);
                this.setLayoutY(event.getSceneY() + mouseY);
                validRectangle.getChildren().remove(button);
            });

            this.setOnMouseReleased(event ->
            {
                Rectangle closest = cloestRectangle(this.getLayoutX(), this.getLayoutY(), Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer));
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
                image1.setOnMousePressed(event1 ->
                {
                    image1.setRotate(90.0);//this is for task 12,allow the user to rotate 90 degrees clockwise for each placement

                });
                this.setOnMousePressed(null);
                this.setOnMouseDragged(null);
                this.setOnMouseReleased(null);
                String row = ((int) (this.getLayoutY() / SQUARE_SIZE - 1)) + "";
                String col = ((int) (this.getLayoutX() / SQUARE_SIZE - 1)) + "";
                placementStringBuilder.append(tile);
                placementStringBuilder.append(row);
                placementStringBuilder.append(col);
                int turnIndex = (placementStringBuilder.length() / 6) % numberOfPlayer;
                addFlag(root, flagGroup, turnIndex);
                addScore(root, scoreGroup, placementStringBuilder.toString(), numberOfPlayer);
                AddElement a = new AddElement();
                a.addComputerPlacement(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, isAdvancedBot);//after player 1 placed the tile, player 2 (AI) goes to place
            });


            String drawnTile = Metro.drawFromDeck(placementStringBuilder.toString(), AddElement.stringArrayToString(totalHandArray));
            if (drawnTile.equals("")) {
                addGameOver(root);
            }
            button.setOnAction(e -> {
                validRectangle.getChildren().clear();
                DraggableRectangle4 a = new DraggableRectangle4(root, drawnTile, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, button, scoreGroup, flagGroup, isAdvancedBot);//player 1 choose to draw again
                validRectangle.getChildren().add(a);
            });
            button.setLayoutX(12.5 * SQUARE_SIZE);
            button.setLayoutY(10 * SQUARE_SIZE);
            validRectangle.getChildren().add(button);
        }
    }

    /**
     * Again this class is similiar to the other three DraggbleRectangle class. This class is for player 1, who is the only real player,
     * to draw again and place the tile.
     * Readers can skip reading this class because it is pretty the same as other three. The difference is minor and commented.
     *
     * @author Jiawei Fan
     */
    public static class DraggableRectangle4 extends ImageView {
        double mouseX;
        double mouseY;

        public DraggableRectangle4(Group root, String tile, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, Button button1, Button button, Group scoreGroup, Group flagGroup, Boolean isAdvancedBot) {
            validRectangle.getChildren().clear();
            Image image = new Image(this.getClass().getResource(URI_BASE + tile + ".jpg").toString());
            this.setFitHeight(SQUARE_SIZE);
            this.setFitWidth(SQUARE_SIZE);
            this.setImage(image);
            this.setLayoutX(12.5 * SQUARE_SIZE);//different layout
            this.setLayoutY(8.5 * SQUARE_SIZE);
            this.setOnMousePressed(event -> {
                mouseX = this.getLayoutX() - event.getSceneX();
                mouseY = this.getLayoutY() - event.getSceneY();
                ArrayList<String> validPlace = Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer);
                if (validPlace.size() == 0) {
                    addGameOver(root);
                }
                drawValidRectangles(validRectangle, validPlace);
            });

            this.setOnMouseDragged(event ->
            {
                this.setLayoutX(event.getSceneX() + mouseX);
                this.setLayoutY(event.getSceneY() + mouseY);
                validRectangle.getChildren().remove(button);
            });

            this.setOnMouseReleased(event ->
            {
                Rectangle closest = cloestRectangle(this.getLayoutX(), this.getLayoutY(), Placement.getFinalValidPlace(placementStringBuilder.toString(), tile, numberOfPlayer));
                ImageView image1 = new ImageView(this.getClass().getResource(URI_BASE + tile + ".jpg").toString());
                image1.setLayoutX(closest.getLayoutX());
                image1.setLayoutY(closest.getLayoutY());
                image1.setFitWidth(SQUARE_SIZE);
                image1.setFitHeight(SQUARE_SIZE);
                root.getChildren().add(image1);
                this.setLayoutX(closest.getLayoutX());
                this.setLayoutY(closest.getLayoutY());
                validRectangle.getChildren().clear();
                image1.setOnMousePressed(event1 ->
                {
                    image1.setRotate(90.0);//this is for task 12,allow the user to rotate 90 degrees clockwise for each placement

                });
                this.setOnMousePressed(null);
                this.setOnMouseDragged(null);
                this.setOnMouseReleased(null);
                String row = ((int) (this.getLayoutY() / SQUARE_SIZE - 1)) + "";
                String col = ((int) (this.getLayoutX() / SQUARE_SIZE - 1)) + "";
                placementStringBuilder.append(tile);
                placementStringBuilder.append(row);
                placementStringBuilder.append(col);
                int turnIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;
                addScore(root, scoreGroup, placementStringBuilder.toString(), numberOfPlayer);
                addFlag(root, flagGroup, turnIndex);
                AddElement a = new AddElement();
                a.addComputerPlacement(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, isAdvancedBot);
            });

        }
    }

    /**
     * @param root                   root group
     * @param placementStringBuilder stringBuilder representing the placement
     * @param numberOfPlayer         an int representing number of players
     * @param validRectangle         group validRectangle
     * @param totalHandArray         String[] representing the hands for all players
     * @param button1                a button
     * @param scoreGroup             score group
     * @param flagGroup              flag group
     * @param isAdvancedBot          true if the AI is advanced; false if not
     * @throws NullPointerException at later stage of the game(the board is nearly full), this excpetion may occur.
     * @author Jiawei Fan
     */
    public void addComputerPlacement(Group root, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, Button button1, Group scoreGroup, Group flagGroup, Boolean isAdvancedBot) throws NullPointerException {
        Random random = new Random();
        int numberOfPlacement = placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT;
        int turnIndex = numberOfPlacement % numberOfPlayer;
        if (turnIndex == 0) {//if its real player1's turn
            if (totalHandArray[turnIndex] == null) {
                validRectangle.getChildren().add(button1);
            } else {
                DraggableRectangle3 b = new DraggableRectangle3(root, totalHandArray[turnIndex], placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, turnIndex, button1, scoreGroup, flagGroup, isAdvancedBot);
                validRectangle.getChildren().add(b);
            }
        } else {
            if (totalHandArray[turnIndex] == null) {//if this AI does not have a tile on hand
                String drawnTile = Metro.drawFromDeck(placementStringBuilder.toString(), AddElement.stringArrayToString(totalHandArray));
                if (drawnTile.equals("")) {
                    AddElement.addGameOver(root);
                }
                if (!isAdvancedBot) {//if it is just simple AI
                    compChoice(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, random, drawnTile);
                } else {
                    compChoice2(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, drawnTile);
                }
            } else {
                if (!isAdvancedBot) {
                    compChoice(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, random, totalHandArray[turnIndex]);
                } else {
                    compChoice2(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, totalHandArray[turnIndex]);
                }
            }
        }
    }

    /**
     * This method is for helping AI decide which place to place the tile. Actullay it is random because this AI is simple AI.
     *
     * @param root                   root group
     * @param placementStringBuilder stringBuilder containing the placement information
     * @param numberOfPlayer         an int representing the number of players
     * @param validRectangle         valid rectangle group
     * @param totalHandArray         string[] representing the hands in all players' hand
     * @param button1                a button
     * @param scoreGroup             score group
     * @param flagGroup              flag groupo
     * @param random                 random object
     * @param drawnTile              string representing the tile to be placed
     * @throws NullPointerException at later stage of the game(the board is nearly full), this excpetion may occur.
     * @author Jiawei Fan
     */
    private void compChoice(Group root, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, Button button1, Group scoreGroup, Group flagGroup, Random random, String drawnTile) throws NullPointerException {
        int turnIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;
        ImageView putTile = new ImageView();//get a new imageView for the drawn tile
        putTile.setImage(new Image(this.getClass().getResource(URI_BASE + drawnTile + ".jpg").toString()));
        putTile.setFitWidth(SQUARE_SIZE);
        putTile.setFitHeight(SQUARE_SIZE);
        putTile.setLayoutX(11 * SQUARE_SIZE);//give it a initial place
        putTile.setLayoutY(8.5 * SQUARE_SIZE);
        validRectangle.getChildren().add(putTile);
        int wetherDrawAgain = random.nextInt(2);
        if (wetherDrawAgain == 1) {
            validRectangle.getChildren().clear();
            totalHandArray[turnIndex] = drawnTile;
            String drawnTile2 = Metro.drawFromDeck(placementStringBuilder.toString(), AddElement.stringArrayToString(totalHandArray));
            if (drawnTile2.equals("")) {
                AddElement.addGameOver(root);//game over if there is no more tile
            }
            ImageView putTile2 = new ImageView();
            putTile2.setImage(new Image(this.getClass().getResource(URI_BASE + drawnTile2 + ".jpg").toString()));
            putTile2.setFitWidth(SQUARE_SIZE);
            putTile2.setFitHeight(SQUARE_SIZE);
            givePlacement(root, drawnTile2, placementStringBuilder, numberOfPlayer, putTile2);//give placement to this tile, it contains an animation
        } else {
            givePlacement(root, drawnTile, placementStringBuilder, numberOfPlayer, putTile);
            totalHandArray[turnIndex] = null;
            validRectangle.getChildren().clear();
            //place this tile
        }
        turnIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;
        addScore(root, scoreGroup, placementStringBuilder.toString(), numberOfPlayer);//update score
        addFlag(root, flagGroup, turnIndex);//update flag
        checkIndex(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, false);//check index to see if next is real player 1 or other AI
    }

    /**
     * This method is for advanced AI player, it will give a good placement for the AI by applying a bit greedy algorithm.
     * Again, apologise for the repetive code, this method is very similair to compChoice 1, it i have time, i can merge
     * this two methods into one. Readers can only read the commented lines. The signature random is gone because advanced
     * AI does not decide the placement randomly.
     *
     * @param root                   root group
     * @param placementStringBuilder stringBuilder containing the placement information
     * @param numberOfPlayer         an int representing the number of players
     * @param validRectangle         valid rectangle group
     * @param totalHandArray         string[] representing the hands in all players' hand
     * @param button1                a button
     * @param scoreGroup             score group
     * @param flagGroup              flag group
     * @param drawnTile              string representing the tile to be placed
     * @throws NullPointerException at later stage of the game(the board is nearly full), this excpetion may occur.
     * @author Jiawei Fan
     */
    private void compChoice2(Group root, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, Button button1, Group scoreGroup, Group flagGroup, String drawnTile) throws NullPointerException {
        int turnIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;
        ImageView putTile = new ImageView();
        putTile.setImage(new Image(this.getClass().getResource(URI_BASE + drawnTile + ".jpg").toString()));
        putTile.setFitWidth(SQUARE_SIZE);
        putTile.setFitHeight(SQUARE_SIZE);
        putTile.setLayoutX(11 * SQUARE_SIZE);
        putTile.setLayoutY(8.5 * SQUARE_SIZE);
        validRectangle.getChildren().add(putTile);
        String highestAdditionalScoreCoordinates = getHighestAdditionalScoreCoordinates(root, drawnTile, placementStringBuilder, numberOfPlayer);//get the coordinate of highest place, if no place can gain 5 more points, draw again
        if (highestAdditionalScoreCoordinates.equals("")) {//if no place gain 5 more points
            validRectangle.getChildren().clear();
            totalHandArray[turnIndex] = drawnTile;
            String drawnTile2 = Metro.drawFromDeck(placementStringBuilder.toString(), AddElement.stringArrayToString(totalHandArray));//draw again
            if (drawnTile2.equals("")) {
                AddElement.addGameOver(root);
            }
            ImageView putTile2 = new ImageView();
            putTile2.setImage(new Image(this.getClass().getResource(URI_BASE + drawnTile2 + ".jpg").toString()));
            putTile2.setFitWidth(SQUARE_SIZE);
            putTile2.setFitHeight(SQUARE_SIZE);
            String highestAdditionalScoreCoordinates2 = getHighestAdditionalScoreCoordinates(root, drawnTile2, placementStringBuilder, numberOfPlayer);//get highest place to place the tile because AI can not draw again
            giveGoodPlacement(root, drawnTile2, placementStringBuilder, numberOfPlayer, putTile2, highestAdditionalScoreCoordinates2);
        } else {
            giveGoodPlacement(root, drawnTile, placementStringBuilder, numberOfPlayer, putTile, highestAdditionalScoreCoordinates);
            totalHandArray[turnIndex] = null;
            validRectangle.getChildren().clear();
        }
        turnIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;
        addScore(root, scoreGroup, placementStringBuilder.toString(), numberOfPlayer);
        addFlag(root, flagGroup, turnIndex);
        checkIndex(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, true);
    }

    /**
     * This method is to check the current index to see if it is 0, which means it is real player1's turn to place a tile.
     * If it is AI's turn, this method applied a bit concept of recursion to go to next AI's turn.
     *
     * @param root                   root group
     * @param placementStringBuilder stringBuilder containing the placement information
     * @param numberOfPlayer         an int representing the number of players
     * @param validRectangle         valid rectangle group
     * @param totalHandArray         string[] representing the hands in all players' hand
     * @param button1                a button
     * @param scoreGroup             score group
     * @param flagGroup              flag group
     * @param isAdvancedBot          true if the AI is advanced, false if not
     * @author Jiawei Fan
     */
    private void checkIndex(Group root, StringBuilder placementStringBuilder, int numberOfPlayer, Group validRectangle, String[] totalHandArray, Button button1, Group scoreGroup, Group flagGroup, Boolean isAdvancedBot) {
        int turnIndex = (placementStringBuilder.length() / Placement.LENGTH_OF_ONE_PlACEMENT) % numberOfPlayer;
        if (turnIndex == 0) {
            if (totalHandArray[turnIndex] == null) {//check hand
                validRectangle.getChildren().add(button1);//put in the draw button so that player 1 can draw
            } else {
                DraggableRectangle3 d = new DraggableRectangle3(root, totalHandArray[0], placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, turnIndex, button1, scoreGroup, flagGroup, isAdvancedBot);
                validRectangle.getChildren().add(d);
            }
        } else {
            addComputerPlacement(root, placementStringBuilder, numberOfPlayer, validRectangle, totalHandArray, button1, scoreGroup, flagGroup, isAdvancedBot);//this is the place recursion concept is applied
        }
    }
}
