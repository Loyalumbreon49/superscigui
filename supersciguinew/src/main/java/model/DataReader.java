package model;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Robert Reed
 * DataReader class handles the serialization of game data to files. 
 * It provides methods to save arrays of Hero, Villain, User, and Question objects to specified files.
 */

public class DataReader {

    public static void saveHeroes(ArrayList<Hero> heroes, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(heroes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveVillains(ArrayList<Villain> villains, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(villains);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    	public static void saveUsers(ArrayList<User> users, String fileName) {
		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< users.size(); i++) {
			jsonUsers.add(getUserJSON(users.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(fileName)) {
 
            file.write(jsonUsers.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();
		userDetails.put("id", user.getId().toString());
		userDetails.put("userName", user.getUserName());
		userDetails.put("phoneNumber", user.getPhoneNumber());
		userDetails.put("email", user.getEmail());
		userDetails.put("hero", user.getHero());
		userDetails.put("password", user.getPassword());
        userDetails.put("level", user.getLevel().getLevelNumber());
        
        return userDetails;
	}

    public static void saveQuestions(ArrayList<Question> questions, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
