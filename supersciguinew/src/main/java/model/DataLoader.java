package model;
//Rkmp

import java.util.HashMap;
import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader {

    public static ArrayList<Hero> getHeroes(String fileName) {
        ArrayList<Hero> heroes = new ArrayList<Hero>();
        
        try {
            FileReader reader = new FileReader(fileName);
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            
            for(int i=0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                String subject = ((String)personJSON.get("subject"));
                String name = (String)personJSON.get("name");
                JSONArray weapons = (JSONArray)personJSON.get("weapons");
                ArrayList<Weapon> weaponed = new ArrayList<Weapon>();
                for(int j = 0; j < weapons.size(); j++) {
                    JSONObject weapon = (JSONObject)weapons.get(j);
                    String weaponName = ((String)weapon.get("weaponName"));
                    String effect = ((String)weapon.get("effect"));
                    weaponed.add(new Weapon(weaponName, effect));
                    //System.out.println(weaponName + " " + effect);
                }
                int strength = ((Long)personJSON.get("strength")).intValue();
                String story = (String)personJSON.get("story");
                
                heroes.add(new Hero(name, strength, subject, story, weaponed));
            }
            
            return heroes;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static ArrayList<Villain> getVillains(String fileName) {
        ArrayList<Villain> villains = new ArrayList<Villain>();
        
        try {
            FileReader reader = new FileReader(fileName);
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            
            for(int i=0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                String name = (String)personJSON.get("name");
                JSONArray locs = (JSONArray)personJSON.get("objectivLocation");
                ArrayList<Location> locale = new ArrayList<Location>();
                for(int j = 0; j < locs.size(); j++) {
                    JSONObject location = (JSONObject)locs.get(j);
                    int xString = ((Long)location.get("xPos")).intValue();
                    int yString = ((Long)location.get("yPos")).intValue();
                    //System.out.println(xString + " " + yString);
                    locale.add(new Location(xString, yString));
                }
                int strength = ((Long)personJSON.get("strength")).intValue();
                String story = (String)personJSON.get("story");
                
                villains.add(new Villain(name, locale, strength, story));
            }
            
            return villains;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static ArrayList<User> getUsers(String fileName) {
        ArrayList<User> users = new ArrayList<User>();
        
        try {
            FileReader reader = new FileReader(fileName);
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            
            for(int i=0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID id = UUID.fromString((String)personJSON.get("id"));
                String userName = (String)personJSON.get("userName");
                String phoneNumber = (String)personJSON.get("phoneNumber");
                String email = (String)personJSON.get("email");
                String password = (String)personJSON.get("password");
                //String heroName = (String)personJSON.get("hero");
                Level level = new Level(((Long)personJSON.get("level")).intValue());
                users.add(new User(id, userName, password, phoneNumber, email, level));
            }
            
            return users;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static HashMap<Integer, ArrayList<Question>> getQuestions(String fileName, String math) {
        HashMap<Integer, ArrayList<Question>> Qs = new HashMap<Integer, ArrayList<Question>>();
        for (int q=1; q<11; q++)
            Qs.put(q, new ArrayList<Question>());
        
        try {
            FileReader reader = new FileReader(fileName);
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            
            for(int i=0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                int difficulty = ((Long)personJSON.get("difficulty")).intValue();
                JSONArray answers = (JSONArray)personJSON.get("answers");
                ArrayList<String> answered = new ArrayList<String>();
                for (int j = 0; j < answers.size(); j++) {
                    //System.out.println((String)answers.get(j));
                    answered.add((String)answers.get(j));
                }
                String subject = (String)personJSON.get("subject");
                String correctAnswer = (String)personJSON.get("correctAnswer");
                String question = (String)personJSON.get("question");
                              
                if(math.equalsIgnoreCase(subject)){
                    Qs.get(difficulty).add(new Question(question, difficulty, subject, answered, correctAnswer));
                }
       
            }
            
            return Qs;
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return null;
    }

    // Method to save users to a JSON file
    /*public static void saveUsers(String fileName, ArrayList<User> users) {
        JSONArray userArray = new JSONArray();
        for (User user : users) {
            JSONObject userObject = new JSONObject();
            userObject.put("id", user.getId().toString());
            userObject.put("userName", user.getUserName());
            userObject.put("password", user.getPassword());
            userObject.put("phoneNumber", user.getPhoneNumber());
            userObject.put("email", user.getEmail());
            // Assuming User has a method to get a Hero object and Hero has a method to get name
            userObject.put("hero", user.getHero().getName().toString());

            userArray.add(userObject);
        }

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(userArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public static void main(String[] args) {
        System.out.println("None");
        getUsers("json/Users.json");
        System.out.println("User");
        getQuestions("json/Questions.json", "math");

        System.out.println("Q's");
        getHeroes("json/Heroes.json");
        System.out.println("Hero");
        getVillains("json/Villains.json");
        System.out.println("Vilain");
    }*/
}
