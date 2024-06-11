package model;
public class SuperSci {
    private UserList users;
    private GameManager gameManager;
    private Hero hero;
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
        return userlist.login(userName, password);
    }

    public boolean createAccount(String userName, String password, String phoneNumber, String email, UserList userList)
    {
        return userList.createAccount(userName, password, phoneNumber, email);
    }

    /**
     * Starts the game session.
     */
    public void play() {
        this.gameManager = new GameManager();
        if (this.hero != null) {
            gameManager.gameRun();
        }
    }

    public static void main(String[] args) {
        UserList userList = UserList.getInstance();
        SuperSci fleck = new SuperSci(userList);

        // Simulate a login for testing purposes
        fleck.login("keving3", "password", userList);   
        fleck.login("arcSky", "ajG", userList);

        fleck.hero = HeroList.getInstance().getHero("Robin");
        fleck.play();
    }
    
}
