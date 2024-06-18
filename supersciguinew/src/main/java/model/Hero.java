package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds the information for the Hero
 * @author Amaya Shabazz
 */
public class Hero implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String subject;
    private int strength;
    private String story;
    public ArrayList<Weapon> weapons;
    public ArrayList<Weapon> collectedWeapons;

    public Hero(String name, int strength, String subject, String story, ArrayList<Weapon> weapons) {
        this.setName(name);
        this.setStrength(strength);
        this.setSubject(subject);
        this.setStory(story);
        this.setWeapons(weapons);
        collectedWeapons = new ArrayList<Weapon>();
    }

    private void setStory(String story) {
        this.story = story;
    }

    public String getStory() {
        return this.story;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    private void setSubject(String subject) {
        this.subject = subject;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public ArrayList<Weapon> getCollectedWeapons() {
        return this.collectedWeapons;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    // One list of weapons & one list of collected weapons
    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    /**
     * Adds a weapon to the list of collected weapons
     */
    public void addWeapon(Weapon weapon) {
        collectedWeapons.add(weapon);
        if (weapon.getEffect().equals("strength"))
            strength += 2;
    }

    public String getDescription() {
        return name + ": \nStrength: " + strength + "\nSubject: " + subject
                + "\nAll Weapons: " + weapons + "\nCollected Weapons: " + collectedWeapons + "\nStory: " + story;
    }

    public boolean hasMove() 
    {
        boolean check = false;
        for(int i = 0; i < collectedWeapons.size(); i++)
        {
            if(collectedWeapons.get(i).getEffect().equalsIgnoreCase("move")) {
                check = true;
            }
        }
        return check;
    }

    public boolean hasIntel() {
        boolean check = false;
        for(int i = 0; i < collectedWeapons.size(); i++)
        {
            if(collectedWeapons.get(i).getEffect().equalsIgnoreCase("intel")) {
                check = true;
            }

        }
        return check;
    }

}
