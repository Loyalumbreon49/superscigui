package model;

import java.util.ArrayList;

public class Level {
    private int levelNumber;
    private ArrayList<Villain> villains;
    private ArrayList<Hero> heroes;

    public Level() {
        levelNumber = 1;
        heroes = DataLoader.getHeroes("supersciguinew/src/main/java/data/Heroes.json");
        villains = DataLoader.getVillains("supersciguinew/src/main/java/data/Villains.json");
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public Villain getVillain() {
        if (villains.size() > levelNumber - 1) {
            return villains.get(levelNumber - 1);
        }
        return null;
    }

    public Hero getHero() {
        if (heroes.size() > 0) {
            return heroes.get(0);
        }
        return null;
    }

    public void nextLevel() {
        levelNumber++;
    }
}
