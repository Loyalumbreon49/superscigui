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
     * @return The single, static instance of the UserList.
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
        System.out.println("Check 1");
        if (userName == null || userName.isEmpty() || password == null || !isValidPassword(password) || phoneNumber == null || email == null || email.isEmpty()) {
            System.out.println("Check 1.1");
            return false;
        }
        
        for (User user : users) {
            System.out.println("Checking user: " + user.getUserName()); // Debug statement
            if (user.getUserName().equals(userName)) {
                System.out.println("Username already exists. Username is case-sensitive.");
                System.out.println("Check 2");
                return false;
            }
        }
        User newUser = new User(UUID.randomUUID(), userName, password, phoneNumber, email);
        users.add(newUser);
        DataReader.saveUsers(users, "json/Users.json");
        System.out.println("Check 3");
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
    public boolean login(String userName, String password) {
        if (userName == null || password == null) {
            return false;
        }

        for (User user : users) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                System.out.println("Login successful.");
                return true;
            }
        }
        System.out.println("Login failed.");
        return false;
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

    /**
     * Main method to interactively test the account creation and login functionality using console input.
     * @param args Command line arguments, not used.
     */
    public static void main(String[] args) {
        UserList userList = UserList.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option: [1] Create Account, [2] Login, [3] Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: // Create account
                    System.out.println("Enter username (case-sensitive):");
                    String userName = scanner.nextLine();

                    String password;
                    do {
                        System.out.println("Enter password (case-sensitive, at least 6 characters, must include special characters !@#$%&*):");
                        password = scanner.nextLine();
                        if (!isValidPassword(password)) {
                            System.out.println("Invalid password. Try again.");
                        }
                    } while (!isValidPassword(password));

                    System.out.println("Enter phone number (format ###-###-####):");
                    String phone = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();

                    boolean added = userList.createAccount(userName, password, phone, email);
                    if (added) {
                        System.out.println("Account created successfully.");
                    } else {
                        System.out.println("Failed to create account.");
                    }
                    break;
                case 2: // Login
                    System.out.println("Enter username:");
                    String loginUserName = scanner.nextLine();
                    int attempts = 4;

                    while (attempts > 0) {
                        System.out.println("Enter password (Attempts left: " + attempts + "):");
                        String loginPassword = scanner.nextLine();
                        if (userList.login(loginUserName, loginPassword)) {
                            break;
                        } else {
                            attempts--;
                        }
                    }

                    if (attempts == 0) {
                        System.out.println("Too many failed attempts. Exiting program.");
                        scanner.close();
                        return;
                    }
                    break;
                case 3: // Exit
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
