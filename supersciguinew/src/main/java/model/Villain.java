package model;
import java.util.ArrayList;

public class Villain {
    private String name;
    private ArrayList<Location> objectiveLocation;
    private int strength;
    private String story;

    public Villain(String name, ArrayList<Location> objectiveLocation, int strength, String story) {
        this.name = name;
        this.objectiveLocation = objectiveLocation;
        this.objectiveLocation.add(new Location(4, 8));
        this.strength = strength;
        this.story = story;
    }

    public String getName() {
        return name;
    }

    
    public ArrayList<Location> getObjectiveLocation() {
        return objectiveLocation;
    }

    
    public int getStrength() {
        return strength;
    }

    
    public String getStory() {
        return story;
    }

    public String getDescription() {
        return name+": \nStrength: "+strength+"\nStory: "+story;
    }
}