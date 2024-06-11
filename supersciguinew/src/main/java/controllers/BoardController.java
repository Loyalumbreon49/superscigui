package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.SuperSci;
import model.UserList;
import javafx.scene.image.ImageView;

public class BoardController {

    model.GameManager gm = new model.GameManager();
    
    @FXML
    private VBox VboX;

    @FXML
    private GridPane board_grid;

    @FXML
    private ImageView harley_on;

    @FXML
    private ImageView robin_on;

    public int robX = 4; 
    public int robY = 8;

    @FXML
    void buttonUp(ActionEvent event) {
        gm.mover("up");
        robY--;
        System.out.println("buttonUp");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
    } 

    @FXML
    void buttonDown(ActionEvent event) {
        gm.mover("down");
        robY++;
        System.out.println("buttonDown");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
    } 

    @FXML
    void buttonLeft(ActionEvent event) {
        gm.mover("left");
        robX--;
        System.out.println("buttonLeft");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
    } 

    @FXML
    void buttonRight(ActionEvent event) {
        gm.mover("right");
        robX++;
        System.out.println("buttonRight");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
    } 
    
}
