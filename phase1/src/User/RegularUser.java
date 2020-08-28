package User;

public class RegularUser extends ClientUser {
    /**
     * @param username the username of the user account
     * @param password the password of the user account
     * @param isAdmin  the boolean shows if the user is administrative user or not
     */
    public RegularUser(String username, String password, boolean isAdmin) {
        super(username, password, isAdmin);
    }

    @Override
    public String toString() {
        return "this is a client user";
    }
}
