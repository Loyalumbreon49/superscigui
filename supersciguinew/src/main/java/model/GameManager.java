package model;
import java.util.ArrayList;
import java.util.Scanner;

//Ronnie Rkmp

public class GameManager{

    private Scanner moveScanner;

    public Hero hero;
    public Level level;
    public Villain villain;
    public Board board;
    public int victoryState;
    public int objectivesCaptured;
    public QuestionList questions;

    public GameManager(User user){
        level = user.getLevel();
        hero = user.getHero();

        moveScanner = new Scanner(System.in);
        
        hero = level.getHero();
        hero.setStrength(2);
        hero.collectedWeapons = new ArrayList<Weapon>();
        System.out.println(hero.getDescription());
        villain = level.getVillain();
        System.out.println(villain.getDescription());

        questions = QuestionList.getInstance((hero.getSubject()));
        
        board = new Board(villain);
    }

    public Hero chooseHero() {
        //enumeration
        return hero;
    }

    public void gameRun() {
        
        while(victoryState == 0)
        {    
            playerTurn();
            if (hero.hasMove())
                playerTurn();
            if (victoryState == 0)
                villainTurn();
        }
    }

    public void playerTurn() {

        System.out.println("What direction would you like to move?");
        String Direction = moveScanner.nextLine();
        mover(Direction); 
        if (board.heroLocation.isWeapon())
        {
            Question qAsk = questions.getQuestion(level.getLevelNumber());
            System.out.println(qAsk);
            String userAnswer = moveScanner.nextLine();
            if(questions.checkAnswer(userAnswer, qAsk))  //make askQuestion public
            {
                hero.collectedWeapons.add(board.heroLocation.getWeapon());
                if(board.heroLocation.getWeapon().getEffect().equals("strength"))
                {
                    hero.setStrength(hero.getStrength()+2);
                }
                System.out.println(board.heroLocation.weapon.getName());
                board.heroLocation.weapon = null;
            }
        }

        if (board.heroLocation.equals(board.villainLocation))
        {
           fightVillain();
        }
    }

    public void mover(String Direct) 
    {
        Location J = board.heroLocation;
        int xJ = J.xPos;
        int yJ = J.yPos;
        if (Direct.equals("right")) 
        {
            moveRight();
        }
        if (Direct.equals("left")) 
        {
            moveLeft();
        }
        if (Direct.equals("up")) 
        {
            moveUp();
        }
        if (Direct.equals("down")) 
        {
            moveDown();
        }
        if (Direct.equals("qLevelUpCC"))
        {
            levelUp();
            System.out.println("New Level");
        }
        J.xPos = xJ;
        J.yPos = yJ;
        System.out.println(J);
        System.out.println("\nYour Location: " + board.heroLocation);
    }

    public void moveRight() {
        board.heroLocation = board.locations.get(board.heroLocation.xPos++).get(board.heroLocation.yPos);
        board.heroLocation = board.locations.get(board.heroLocation.xPos++).get(board.heroLocation.yPos);
    }

    public void moveLeft() {
        board.heroLocation = board.locations.get(board.heroLocation.xPos--).get(board.heroLocation.yPos);
        board.heroLocation = board.locations.get(board.heroLocation.xPos--).get(board.heroLocation.yPos);
    }

    public void moveUp() {
        board.heroLocation = board.locations.get(board.heroLocation.xPos).get(board.heroLocation.yPos++);
        board.heroLocation = board.locations.get(board.heroLocation.xPos).get(board.heroLocation.yPos++);
    }

    public void moveDown() {
        board.heroLocation = board.locations.get(board.heroLocation.xPos).get(board.heroLocation.yPos--);
        board.heroLocation = board.locations.get(board.heroLocation.xPos).get(board.heroLocation.yPos--);
    }

    public void levelUp() {
        level.nextLevel();
        hero.setStrength(2);
        System.out.println(hero.getDescription());
        villain = level.getVillain();
        System.out.println(villain.getDescription());

        questions = QuestionList.getInstance((hero.getSubject()));
        
        board = new Board(villain);
    }

    public boolean showVillainLocation(int x, int y) {
        int vX = board.villainLocation.xPos;
        int vY = board.villainLocation.yPos;
        y = y - 8;
        if(hero.hasIntel())
        {
            System.out.println("villainShown");
            return true;
        }
        else if( (  (((vX - x >= 0) && (vX -x <=2)) || ((x - vX >= 0) && (x - vX <=2))) && y == vY ) || (  (((vY - y >= 0) && (vY -y <=2)) || ((y - vY >= 0) && (y - vY <=2))) && x == vX ) )
        {
            System.out.println("her0x: " + x + " her0y: " + y + " vilX: " + vX + " vilY: " + vY);
            System.out.println("heroNextToVillain");
            return true;
        }
        else
        {
            System.out.println("villainHidden");
            return false;
        }
    }

    public boolean fightVillain() {
        if (hero.getStrength() > villain.getStrength())
        {
            playerWins();
            return true;
        }
        else 
        {
            playerLoses();
            return false;
        }

    }

    public void villainTurn() {
        if (objectivesCaptured >= 3)
        {
            playerLoses();
        }
        else if (board.villainLocation.xPos < villain.getObjectiveLocation().get(objectivesCaptured).xPos)
        {
            board.villainLocation.xPos ++;
        }
        else if (board.villainLocation.xPos > villain.getObjectiveLocation().get(objectivesCaptured).xPos)
        {
            board.villainLocation.xPos --;
        }
        else if (board.villainLocation.yPos < villain.getObjectiveLocation().get(objectivesCaptured).yPos)
        {
            board.villainLocation.yPos ++;
        }
        else if (board.villainLocation.yPos > villain.getObjectiveLocation().get(objectivesCaptured).yPos)
        {
            board.villainLocation.yPos --;
        }

        System.out.println(villain.getName() + "'s Location: " + board.villainLocation);
        
        if (objectivesCaptured < 3 && board.villainLocation.equals(villain.getObjectiveLocation().get(objectivesCaptured)))
        {
            objectivesCaptured++;
        }

        if (board.heroLocation.equals(board.villainLocation))
        {
           fightVillain();
        }
    }

    public void playerWins() {
        System.out.println("you have won");
        victoryState = 1;
    }

    public void playerLoses() {

        System.out.println("you have lost");
        victoryState = -1;
    }

    /*public static void main(String[] args) {
        GameManager gm = new GameManager();
        gm.hero = gm.level.getHero();
        System.out.println(gm.hero.getDescription());
        gm.villain = gm.level.getVillain();
        System.out.println(gm.villain.getDescription());

        gm.questions = QuestionList.getInstance((gm.hero.getSubject()));
        
        gm.board = new Board(gm.villain);

        gm.gameRun();
        
        
        Question qAsk = gm.questions.getQuestion(1);
        System.out.println(qAsk);
        String userAnswer = gm.moveScanner.nextLine();
        System.out.println(gm.questions.checkAnswer(userAnswer, qAsk));
    }*/
}