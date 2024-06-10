package model;
public class Weapon {
    private String name;
    private Hero hero;
    private String effect;

    
    public Weapon(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    
    public String getName() {
        return name;
    }

    
    public Hero getHero() {
        return hero;
    }

    
    public String getEffect() {
        return effect;
    }

    public String toString() {
        return getName();
    }
}
