package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Question;
import model.SuperSci;
import model.UserList;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image; 
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;

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
    private ImageView hook = new ImageView();

    @FXML
    private ImageView birdarang = new ImageView();

    @FXML
    private ImageView staff = new ImageView();

    @FXML
    private ImageView staff_one = new ImageView();

    

    public int robX = 4; 
    public int robY = 8;
    public boolean spedFalse = false;
    public int time = 0;
    

    @FXML
    void buttonUp(ActionEvent event) {
        gm.mover("up");
        robY--;
        System.out.println("buttonUp");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkWeapon();
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
        checkWeapon();
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
        checkWeapon();
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
        checkWeapon();
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
        if (time == 0)
        {
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    if (gm.board.locations.get(i).get(j).isWeapon())
                    {
                        String effective = gm.board.locations.get(i).get(j).getWeapon().getEffect();
                        if (effective.equals("move"))
                        {
                            hook.setImage(new Image(getClass().getResourceAsStream("/images/hook.png")));
                            hook.setFitWidth(50);
                            hook.setFitHeight(50);
                            board_grid.add(hook, i, j);
                            time++;
                        }
                        if (effective.equals("strength"))
                        {
                            staff.setImage(new Image(getClass().getResourceAsStream("/images/staff.jpg")));
                            staff.setFitWidth(50);
                            staff.setFitHeight(50);
                            board_grid.add(staff, i, j);
                            time++;
                        }
                        if (effective.equals("intel"))
                        {
                            birdarang.setImage(new Image(getClass().getResourceAsStream("/images/birdarang.png")));
                            birdarang.setFitWidth(50);
                            birdarang.setFitHeight(50);
                            board_grid.add(birdarang, i, j);
                            time++;
                        }
                    }
                }
            }

        }
        if(robX == gm.board.villainLocation.xPos && robY == 8-gm.board.villainLocation.yPos)
        {
            if (gm.fightVillain()) 
            {
                
            }
        }
    }

    public void checkWeapon()
    {
        if (gm.board.locations.get(robX).get(robY).isWeapon())
        {
            gm.hero.collectedWeapons.add(gm.board.locations.get(robX).get(robY).getWeapon());
            if(gm.board.locations.get(robX).get(robY).getWeapon().getEffect().equals("strength"))
            {
                gm.hero.setStrength(gm.hero.getStrength()+2);
            }
            String effective = gm.board.locations.get(robX).get(robY).getWeapon().getEffect();
                if (effective.equals("move"))
                {
                    board_grid.getChildren().remove(hook);
                }
                if (effective.equals("strength"))
                {
                    board_grid.getChildren().remove(staff);
                }
                if (effective.equals("intel"))
                {
                    board_grid.getChildren().remove(birdarang);
                }
            gm.board.heroLocation.weapon = null;

            
        }
    }
    
}



