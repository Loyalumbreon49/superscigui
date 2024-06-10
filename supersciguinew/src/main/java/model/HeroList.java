package model;
/**
 * Holds the infromation for the Hero list
 * @author Amaya Shabazz
 */
import java.util.ArrayList;

public class HeroList {
    private ArrayList<Hero> allHeroes;
    private static HeroList heroList;
    private Hero myHero;

    private HeroList()
    {
        allHeroes = DataLoader.getHeroes("json/Heroes.json"); 
    }

    public static HeroList getInstance()
    {
        if (heroList == null)
            heroList = new HeroList();
        return heroList;
    }

    public Hero getHero(String name)
    {
        for(int index = 0; index < allHeroes.size();++index)
        {
            if(allHeroes.get(index).getName().equalsIgnoreCase(name))
                myHero = allHeroes.get(index);
        }
        return myHero;
    }
}
