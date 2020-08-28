package User.Adapter;

import User.Entity.ClientUser;
import User.Gateway.UserDataAccess;
import User.UseCase.AdminActivityManager;
import User.UseCase.UserManager;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * [Controller]
 * controllers that control ClientUser
 */
public class ClientUserController implements IUserController{
    UserManager um = new UserManager();
    /**
     * @param username the name of user
     * return whether the user is admin or not
     */

    public boolean getIsAdmin(String username) {
        return um.getUser(username).getIsAdmin();
    }

    /**
     * @param name     the name of the user that the manager check
     * @param password the password of the user that the manager check
     *                 Check if the name matches with the password
     */
    public boolean verifyUser(String name, String password) {
        return um.verifyUser(name, password);
    }

    /**
     * @param userId the ID of the user that the manager wants to get
     * find the user by the user ID
     */
    public boolean checkUser(UUID userId) {

        return um.getUser(userId) == null;
    }

    /**
     * @param name the name of the user that the manager wants to get
     * find the user by the user name
     */
    public boolean checkUser(String name) {
        System.out.println(um.getUser(name)==null);
        return um.getUser(name) == null;
    }

    public void createClientUser(String name, String password){
        um.createClientUser(name, password, false);
    }

    /**
     * @param userID the id of the user that the manager wants to get
     * return whether the user is frozen or not
     */
    public boolean getIsFrozen(UUID userID){
        checkFrozen(um.UUIDToName(userID));
        return um.getIsFrozen(userID);
    }

    /**
     * @param name the name of the user that the manager wants to get
     * return id of user
     */
    public UUID nameToUUID(String name){
        return um.nameToUUID(name);
    }

    /**
     * @param name the name of the user that the manager wants to get
     * return id of user
     */
    public UUID getIDbyName(String name) {
        return um.getUser(name).getId();
    }

    public String getNameByID(UUID uuid) {
        return um.getUser(uuid).getUsername();
    }

    public ClientUser getUser(String username){
        return um.getUser(username);
    }

    /**
     * @param file File save data
     * create initial admin user admin, it can add another admin users
     */
    public void checkFileEmpty(File file){
        if (file.length() == 0) {
            ClientUser b = new ClientUser("admin", "123", true);
            UserDataAccess users =new UserDataAccess();
            users.addObject(b);
        }
    }


    /**
     * @param userID the id of the user that the manager wants to get
     * return name of user
     */
    public String UUIDToName(UUID userID){
        return um.UUIDToName(userID);
    }

    /**
     * @param username name of user
     * return the password of the user
     */
    public String getPassword(String username){
        return um.getPassword(um.getUser(username));
    }

    /**
     * @param name name of user
     * @param password the input string
     * set the input string as the password
     */
    public void setPassword(String name, String password){
        um.setPassword(name, password);
    }

    /**
     * return the whole user list
     */
    public List<ClientUser> getUserList() {return um.getUserList();}

    /**
     * @param username name of user
     * return the difference between lend amount and borrow amount
     */
    public int readDiff(String username) { return um.readDiff(username); }

    /**
     * @param a name of user
     * get the difference between lend amount and borrow amount
     */
    public int getDiff(String a) {
        return um.getDiff(a);
    }

    /**
     * @param a name of user
     * return the week transaction limit of user
     */
    public int getWeekTransactionLimit(String a) {
        return um.getWeekTransactionLimit(a);
    }

    /**
     * @param a name of user
     * return the Incomplete Transaction Limit of user
     */
    public int getIncompleteTransactionLimit(String a) {
        return um.getIncompleteTransactionLimit(a);
    }

    /**
     * @param username name of user
     * return the total trade number of user
     */
    public int getTradeNumber(String username) {return um.getTradeNumber(username);}

    /**
     * @param userId id of user
     * return the incomplete transaction  number of user
     */
    public int getIncompleteTransaction(UUID userId) {return um.getIncompleteTransaction(userId);}

    /**
     * @param username name of user
     * @param  end the end date
     * set teh edn date
     */
    public void setEnd(String username, LocalDateTime end){um.setEnd(username, end);}

    /**
     * @param userID id of user
     * return whether the user is left or not
     */
    public boolean getIsLeft(UUID userID){
        return um.getIsLeft(userID);
    }


    @Override
    public void deleteLItem(String username, String lendWish) {
        um.deleteLItem(username,lendWish);
    }

    @Override
    public void deleteBItem(String username, String borrowWish) {
        um.deleteBItem(username,borrowWish);
    }

    public boolean checkActionExist(String username,ArrayList<String> input){
        return um.getActions(um.getUser(username)).contains(input);
    }

    public void checkFrozen(String username) {
        if(um.readDiff(username)>um.getDiff(username)){
            System.out.println("You have been freeze due to exceed difference between borrow and lend");
            ClientUser u = um.popUser(um.nameToUUID(username));
            u.setFrozen(true);
            um.addUser(u);
        }
        else if(new AdminActivityManager().incompleteTransaction(um.nameToUUID(username))){
            System.out.println("You have been freeze due to maximum incomplete transaction");
        }
        else if(new AdminActivityManager().tradeLimit(um.nameToUUID(username))){
            System.out.println("You have been freeze due to maximum trade limit");
        }
    }

    public void setLeft(String a,boolean s){um.setLeft(a, s);}

    @Override
    public void setFreeze(String a, boolean s) {
        um.setFreeze(a, s);
    }
    
}
