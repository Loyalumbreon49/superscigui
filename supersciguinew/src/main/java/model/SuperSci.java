package model;
public class SuperSci {
    private UserList users;
    private GameManager gameManager;
    private Hero hero;
    private static SuperSci instance;
    private static User myUser;
    //private User currentUser;

    public SuperSci(UserList users) {
        this.users = users;
    }

    /**
     * Logs in a user and prepares the game environment.
     *
     * @param userName The username of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return true if the login is successful, false otherwise.
     */
    public boolean login(String userName, String password, UserList userlist) {
        myUser = userlist.login(userName, password);
        return (userlist.login(userName, password) != null);
    }

    public static User getUser()
    {
        return myUser;
    }

    public boolean createAccount(String userName, String password, String phoneNumber, String email, UserList userList)
    {
        return userList.createAccount(userName, password, phoneNumber, email);
    }

    public static synchronized SuperSci getInstance() {
        if (instance == null) {
            instance = new SuperSci(UserList.getInstance());
        }
        return instance;
    }

    /**
     * Starts the game session.
     */
    public void play() {
        this.gameManager = new GameManager(myUser);
        if (this.hero != null) {
            gameManager.gameRun();
        }
    }

}
