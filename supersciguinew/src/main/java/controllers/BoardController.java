package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Question;
import model.SuperSci;
import supersci.gui.App;

public class BoardController {

    model.GameManager gm = new model.GameManager(SuperSci.getUser());

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
    private Label rob_info;

    @FXML
    private Label rob_strength;

    @FXML
    private Label rob_speed;

    @FXML
    private Label vil_objectives;

    @FXML
    private Label qAsk = new Label();

    @FXML
    private Rectangle hider = new Rectangle(64, 64, Color.WHITE);

    @FXML
    private TextField qAnswer = new TextField();

    @FXML
    private Button qButton;

    @FXML
    private ImageView staff_a = new ImageView();

    @FXML
    private ImageView staff_b = new ImageView();

    @FXML
    private ImageView staff_c = new ImageView();

    @FXML
    private ImageView staff_d = new ImageView();

    @FXML
    private Label har_str;

    @FXML
    private Label har_name;



    public int robX = 4; 
    public int robY = 8;
    public boolean spedFalse = false;
    public int time = 0;
    public String answer;
    public int harles = 1;
    public int staffX = 0;
    public int staffY = 0;
    public int staX = 0;
    public int staY = 0;
    public int stbX = 0;
    public int stbY = 0;
    public int stcX = 0;
    public int stcY = 0;
    

    @FXML
    void buttonUp(ActionEvent event) throws IOException {
        gm.mover("up");
        robY--;
        System.out.println("buttonUp");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkWeapon();
        checkQuinn();
        checkVictory();
        showHarley();
    } 

    @FXML
    void buttonDown(ActionEvent event) throws IOException {
        gm.mover("down");
        robY++;
        System.out.println("buttonDown");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkWeapon();
        checkQuinn();
        checkVictory();
        showHarley();
    } 

    @FXML
    void buttonLeft(ActionEvent event) throws IOException {
        gm.mover("left");
        robX--;
        System.out.println("buttonLeft");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkWeapon();
        checkQuinn();
        checkVictory();
        showHarley();
    } 

    @FXML
    void buttonRight(ActionEvent event) throws IOException {
        gm.mover("right");
        robX++;
        System.out.println("buttonRight");
        board_grid.getChildren().remove(robin_on);
        board_grid.add(robin_on, robX, robY);
        checkWeapon();
        checkQuinn();
        checkVictory();
        showHarley();
    } 

    public void checkQuinn() throws IOException
    {
        if (spedFalse == false && gm.hero.hasMove() == false)
        {
            gm.villainTurn();
            board_grid.getChildren().remove(harley_on);
            harles = 0;
            System.out.println("hidden");
            showHarley();
        }
        else if (spedFalse == false && gm.hero.hasMove() == true)
        {
            gm.villainTurn();
            board_grid.getChildren().remove(harley_on);
            harles = 0;
            System.out.println("hidden");
            showHarley();
            spedFalse = true;
        }
        else if (spedFalse == true && gm.hero.hasMove() == true)
        {
            spedFalse = false;
        }

        checkVictory();
    }

    public void checkVictory() throws IOException
    {
        if (time == 0)
        {
            initiate();
        }
        if(robX == gm.board.villainLocation.xPos && robY == 8-gm.board.villainLocation.yPos)
        {
            if (gm.fightVillain()) 
            {
                robX = 4;
                robY = 0;
                SuperSci.getUser().getLevel().nextLevel();
                App.setRoot("board");
            }
            else{
                App.setRoot("lose");
            }
        }

        String obj = "Objectives: " + gm.objectivesCaptured;
        vil_objectives.setText(obj);

        if (gm.objectivesCaptured >= 3)
        {
            gm.playerLoses();
            App.setRoot("lose");
        }
    }

    public void checkWeapon()
    {
        if (gm.board.locations.get(robX).get(robY).isWeapon())
        {
            askQuestion();           
        }
    }

    public void initiate() {
        int str = 0;
        har_str.setText("Strength: " + gm.level.getVillain().getStrength());
        har_name.setText(gm.villain.getName());
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
                    else if (effective.equals("strength") && str == 0)
                    {
                        staffX = i;
                        staffY = j;
                        staff.setImage(new Image(getClass().getResourceAsStream("/images/staff.jpg")));
                        staff.setFitWidth(50);
                        staff.setFitHeight(50);
                        board_grid.add(staff, i, j);
                        time++;
                        str++;
                    }
                    else if (effective.equals("intel"))
                    {
                        birdarang.setImage(new Image(getClass().getResourceAsStream("/images/birdarang.png")));
                        birdarang.setFitWidth(50);
                        birdarang.setFitHeight(50);
                        board_grid.add(birdarang, i, j);
                        time++;
                    }
                    else if (effective.equals("strength") && str == 1)
                    {
                        staX = i;
                        staY = j;
                        staff_a.setImage(new Image(getClass().getResourceAsStream("/images/staff.jpg")));
                        staff_a.setFitWidth(50);
                        staff_a.setFitHeight(50);
                        board_grid.add(staff_a, i, j);
                        time++;
                        str++;
                    }
                    else if (effective.equals("strength") && str == 2)
                    {
                        stbX = i;
                        stbY = j;
                        staff_b.setImage(new Image(getClass().getResourceAsStream("/images/staff.jpg")));
                        staff_b.setFitWidth(50);
                        staff_b.setFitHeight(50);
                        board_grid.add(staff_b, i, j);
                        time++;
                        str++;
                    }
                    else if (effective.equals("strength") && str == 3)
                    {
                        stcX = i;
                        stcY = j;
                        staff_c.setImage(new Image(getClass().getResourceAsStream("/images/staff.jpg")));
                        staff_c.setFitWidth(50);
                        staff_c.setFitHeight(50);
                        board_grid.add(staff_c, i, j);
                        time++;
                        str++;
                    }
                    else if (effective.equals("strength") && str == 4)
                    {
                        staff_d.setImage(new Image(getClass().getResourceAsStream("/images/staff.jpg")));
                        staff_d.setFitWidth(50);
                        staff_d.setFitHeight(50);
                        board_grid.add(staff_d, i, j);
                        time++;
                        str++;
                    }
                }
            }
        }
        
    }
    
    public void askQuestion() {

        VboX.getChildren().add(hider);
        hider.setScaleX(100);
        hider.setScaleY(100);
        hider.setTranslateX(100);
        hider.setTranslateY(-600);
        VboX.getChildren().add(qAsk);
        qAsk.setTranslateX(100);
        qAsk.setTranslateY(-1000);
        Question q = gm.questions.getQuestion(gm.level.getLevelNumber()/2 +1);
        qAsk.setText(q.getQuestion());
        answer = q.getAnswer();
        qButton.toFront();
        qButton.setTranslateX(100);
        qButton.setTranslateY(-900);
        VboX.getChildren().add(qAnswer);
        qAnswer.setTranslateX(-30);
        qAnswer.setTranslateY(-970);
        qAnswer.setScaleX(0.75);
    }

    @FXML
    void finalize(ActionEvent event) throws IOException{
        String answer = qAnswer.getText();
        System.out.println("Answer is "+ answer);
        
        if (answer.equals(this.answer)) {
            addWeapon();
        }
        else {
            qHide();
        }
        qAnswer.setText("");

    }

    public void addWeapon()
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
                    rob_speed.setText("Speed: 2");
                }
                if (effective.equals("strength"))
                {
                    if (robX == staffX && robY == staffY)
                    {
                        board_grid.getChildren().remove(staff);
                    }
                    else if (robX == staX && robY == staY)
                    {
                        board_grid.getChildren().remove(staff_a);
                    }
                    else if (robX == stbX && robY == stbY)
                    {
                        board_grid.getChildren().remove(staff_b);
                    }
                    else if (robX == stcX && robY == stcY)
                    {
                        board_grid.getChildren().remove(staff_c);
                    }
                    else 
                    {
                        board_grid.getChildren().remove(staff_d);
                    }
                    rob_strength.setText("Strength: " + gm.hero.getStrength());
                }
                if (effective.equals("intel"))
                {
                    board_grid.getChildren().remove(birdarang);
                }
            gm.board.locations.get(robX).get(robY).removeWeapon(); 
            qHide();
    }

    public void qHide() {

        VboX.getChildren().remove(hider);
        VboX.getChildren().remove(qAsk);
        qButton.toBack();
        VboX.getChildren().remove(qAnswer);
    }

    public void showHarley()
    {
        //board_grid.getChildren().remove(harley_on);
        if (harles == 0 && gm.showVillainLocation(robX, robY))
        {  
            System.out.println("shown");
            board_grid.add(harley_on, gm.board.villainLocation.xPos, 8-gm.board.villainLocation.yPos);
            harles = 1;
        }
    }

}