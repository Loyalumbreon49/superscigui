package model;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class UserList {
    private static UserList instance;
    private ArrayList<User> users;

    /**
     * Private constructor for the singleton pattern to prevent external instantiation.
     */
    private UserList() {
        users = DataLoader.getUsers("supersciguinew/src/main/java/data/Users.json");
        if (users == null) {
            users = new ArrayList<>();
        }
    }

    /**
     * Returns the singleton instance of the UserList.
     */
    public static synchronized UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    /**
     * Creates an account with the given details if the username is not already taken.
     * The username and password are case-sensitive. The password must meet specific criteria and
     * the phone number must follow the format ###-###-#### including dashes.
     * @param userName The desired username.
     * @param password The account's password.
     * @param phoneNumber The account's phone number.
     * @param email The account's email.
     * @return true if the account was created successfully, false if the username is already in use.
     */
    public boolean createAccount(String userName, String password, String phoneNumber, String email) {
        if (userName == null || userName.isEmpty() || password == null || !isValidPassword(password) || phoneNumber == null || email == null || email.isEmpty()) {
            return false;
        }
        
        for (User user : users) {
            System.out.println("Checking user: " + user.getUserName()); // Debug statement
            if (user.getUserName().equals(userName)) {
                System.out.println("Username already exists. Username is case-sensitive.");
                return false;
            }
        }
        User newUser = new User(UUID.randomUUID(), userName, password, phoneNumber, email, new Level(1));
        users.add(newUser);
        System.out.println("new user");
        DataReader.saveUsers(users, "supersciguinew/src/main/java/data/testWriter.json");
        return true;
    }

    /**
     * Checks if the password is valid according to specified rules.
     * @param password The password to check.
     * @return true if the password is valid, false otherwise.
     */
    private static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    /**
     * Retrieves a user by username and checks password validity.
     * @param userName The username of the account to retrieve.
     * @param password The password for login attempt.
     * @return true if credentials are valid, false otherwise.
     */
    public User login(String userName, String password) {
        if (userName == null || password == null) {
            return null;
        }

        for (User user : users) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                System.out.println("Login successful.");
                return user;
            }
        }
        System.out.println("Login failed.");
        return null;
    }

    /**
     * Retrieves a user by username.
     * @param userName The username to search for.
     * @return The User object if found, null otherwise.
     */
    public User getUser(String userName) {
        if (userName == null) {
            return null;
        }

        for (User user : users) {
            if (userName.equals(user.getUserName())) {
                return user;
            }
        }
        return null;
    }

}
