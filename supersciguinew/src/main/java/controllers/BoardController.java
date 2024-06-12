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

    @FXML
    private ImageView robin_weapon = new ImageView("supersciguinew/src/main/resources/images/hook.png");

    public int robX = 4; 
    public int robY = 8;
    public boolean spedFalse = false;
    

    @FXML
    void buttonUp(ActionEvent event) {
        board_grid.add(robin_weapon, 2, 2);
        gm.mover("up");
        robY--;
        System.out.println("buttonUp");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkQuinn();
        checkVictory();
    } 

    @FXML
    void buttonDown(ActionEvent event) {
        gm.mover("down");
        robY++;
        System.out.println("buttonDown");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkQuinn();
        checkVictory();
    } 

    @FXML
    void buttonLeft(ActionEvent event) {
        gm.mover("left");
        robX--;
        System.out.println("buttonLeft");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkQuinn();
        checkVictory();
    } 

    @FXML
    void buttonRight(ActionEvent event) {
        gm.mover("right");
        robX++;
        System.out.println("buttonRight");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkQuinn();
        checkVictory();
    } 

    public void checkQuinn()
    {
        if (spedFalse == false && gm.hero.hasMove() == false)
        {
            gm.villainTurn();
            board_grid.getChildren().remove(harley_on);
            board_grid.add(harley_on, gm.board.villainLocation.xPos, 8-gm.board.villainLocation.yPos);
        }
        else if (spedFalse == false && gm.hero.hasMove() == true)
        {
            gm.villainTurn();
            board_grid.getChildren().remove(harley_on);
            board_grid.add(harley_on, gm.board.villainLocation.xPos, 8-gm.board.villainLocation.yPos);
            spedFalse = true;
        }
        else if (spedFalse == true && gm.hero.hasMove() == true)
        {
            spedFalse = false;
        }

        checkVictory();
    }

    public void checkVictory()
    {
        if(robX == gm.board.villainLocation.xPos && robY == 8-gm.board.villainLocation.yPos)
        {
            if (gm.fightVillain()) 
            {
                
            }
        }
    }
    
}


