package model;
//Rkmp

import java.io.Serializable;


public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public int xPos;
    public int yPos;
    //private String locGraphic;
    private Weapon weapon;

    public Location(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        weapon = null;
    }

    public void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /*private void setGraphic() {
        locGraphic = "jimmy";
    }*/

    public boolean isWeapon() {
        return (weapon != null);
    }

    public void setWeapon(String name, String effect) {
        this.weapon = new Weapon(name, effect);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String toString() {
        return xPos + " " + yPos;
    }

    public boolean equals(Location locale) {
        if (this.xPos == locale.xPos && this.yPos == locale.yPos) {
            return true;
        } else {
            return false;
        }
    }
}
