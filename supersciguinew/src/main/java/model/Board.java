package model;

/**
 * Holds the information for game board
 * @author Amaya Shabazz
 */
import java.util.*;
public class Board {
    public ArrayList<ArrayList<Location>> locations;
    public Location heroLocation;
    public Location villainLocation;
    public ArrayList<Location> weaponLocations;
    public ArrayList<Location> villainObjectiveLocations;

    /**
     * Creates the locations for the 9x9 (0x0 to 8x8) board
     * @param hero Holds the hero to be placed on the baord
     * @param villain Holds the villian, which is based on the level, from which objective locations will be set
     */
    public Board(Villain villain) 
    {
       villainObjectiveLocations = villain.getObjectiveLocation();
       locations = new ArrayList<ArrayList<Location>>();
       for(int i = 0; i < 9; i++)
       {
            locations.add(new ArrayList<Location>());
            for(int j = 0; j < 9;  j++)
            {

                locations.get(i).add(new Location(i,j));
        
            }
       }
       this.heroLocation = locations.get(4).get(0);
       this.villainLocation = locations.get(4).get(8);
       getRandomLocation(villain);
    }

    /**
     * Sets random locations on the board for weapons
     * @param villain Holds the information about the villain
     */
    protected void getRandomLocation(Villain villain) 
    {
        ArrayList<Location> weapons = new ArrayList<Location>();
        int weaponAmount = getWeaponAmount(villain);

        Random rand = new Random();
        // set weapon locations
        for(int index = 0;index < weaponAmount;index++) //3 weapons total per level
        {
            int xPos = rand.nextInt(8);
            int yPos = rand.nextInt(8);
            weapons.add (new Location(xPos, yPos));
            if(index > 1 && weapons.get(index).xPos==weapons.get(index-1).xPos 
            && weapons.get(index).yPos==weapons.get(index-1).yPos)
            {
                int newXPos = rand.nextInt(8);
                int newYPos = rand.nextInt(8);
                weapons.get(index).setPos(newXPos, newYPos);
            }      
            System.out.print("Weapon: " + weapons.get(index));   
            if (index == 0)
                locations.get(xPos).get(yPos).setWeapon("Birdarangs", "intel"); 
            else if (index == 1)
                locations.get(xPos).get(yPos).setWeapon("Grappling Hook", "move"); 
            else 
                locations.get(xPos).get(yPos).setWeapon("Battle Staff", "strength");   

            System.out.println("                 " + locations.get(xPos).get(yPos).getWeapon());
        }
    }

    /**
     * Sets the weapon amount the hero can get based on the villain's strength
     * @param villain Holds the villian information which will decide the amount of weapons
     * @return Will return the amount of weapons
     */
    protected int getWeaponAmount(Villain villain)
    {
        int amount = 3;
        // Every 2 levels increase weapon amout by 1
        switch (villain.getStrength()) 
        {
            case 5:
                amount+=1;
                break;
            case 7:
                amount+=2;
                break;
            case 9:
                amount+=3;
                break;
            case 11:
                amount+=4;
                break;
            default:
                break;
        }
        return amount;
    }

    /**
     * Checks that the board has been properly filled
     * @return Will return a true or false if the board has been filled
     */
    public boolean createBoard() 
    {
        return locations != null;
    }
}
