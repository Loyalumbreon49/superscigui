package model;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveQuestions(ArrayList<Question> questions, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
