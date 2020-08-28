package User.UseCase;

import Trade.Entity.Trade;
import Trade.GateWay.TradeDataAccess;
import Trade.UseCase.TradeManager;
import User.Actions.UserAction;
import User.Entity.ClientUser;
import User.Gateway.DataAccess;
import User.Gateway.UserDataAccess;
import Trade.TradeStatus;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * [UseCase class]
 * The renew and modification of users
 */
public class UserManager {


    DataAccess tradeAccess = new TradeDataAccess();
    DataAccess dataAccess = new UserDataAccess();

    /**
     * Return the list of ClientUser
     *
     * @return list of ClientUser
     */
    public List<ClientUser> getUserList() {
        List<ClientUser> result = new ArrayList<>();
        for (Object o : dataAccess.getList()) {
            result.add((ClientUser) o);
        }
        return result;
    }


    /**
     * Return the ClientUser with such name, or return null if there no such user.
     *
     * @param name the name of the user that the manager wants to get
     *             find the user by the user name
     */
    public ClientUser getUser(String name) {
        return (ClientUser) dataAccess.getObject(name);
    }

    /**
     * @param userId the ID of the user that the manager wants to get
     *               find the user by the user ID
     */
    public ClientUser getUser(UUID userId) {
        return (ClientUser) dataAccess.getObject(userId);
    }

    /**
     * @param id the ID of the user that the manager wants to get
     *              pop the user form list
     */
    public ClientUser popUser(UUID id){
        if (dataAccess.hasObject(id)) {
            ClientUser result =  (ClientUser) dataAccess.getObject(id);
            dataAccess.removeObject(id);
            System.out.println(getUserList());
            return result;
        } else {
            return null;
        }
    }

    /**
     * Add a ClientUser
     *
     * @param user the clientUser we want to add
     */
    public void addUser(ClientUser user){
        dataAccess.addObject(user);
    }


    /**
     * @param name     the name of the user that the manager check
     * @param password the password of the user that the manager check
     *                 Check if the name matches with the password
     */
    public boolean verifyUser(String name, String password) {
        dataAccess.deSerialize();
        ClientUser user = getUser(name);
        if (user == null) {
            return false;
        } else {
            return user.getPassword().equals(password);
        }
    }


    /**
     * Create a ClientUser with name, password, isAdmin; and save it to .ser file
     *
     * @param name     the name of the clientUser
     * @param password the password of the clientUser
     * @param isAdmin  if the clientUser is admin
     */
    public boolean createClientUser(String name, String password, boolean isAdmin) {
        if((getUser(name)==null)) {
            dataAccess.addObject(new ClientUser(name, password, isAdmin));
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @param username name of user
     * return the difference between lend amount and borrow amount
     */
    public int readDiff(String username) {
        ClientUser user = getUser(username);
        return user.getBorrowCounter() - user.getLendCounter();
    }

    /**
     * return the list of most frequent three traders that the user trades with
     * if the user trades with less than three traders, return all the traders the user trades with
     */
    public List<String> getFrequentUser(UUID userID) {
        TradeManager tm = new TradeManager();
        ClientUser user = getUser(userID);

        List<Trade> a = tm.getAllTrade(userID);
        HashMap<UUID, Integer> b = new HashMap<>();
        for (Trade c : a) {
            for (UUID d : c.getUsers()) {
                if (!(d.equals(user.getId()))) {
                    if (b.containsKey(d)) {
                        b.replace(d, b.get(d) + 1);
                    } else {
                        b.put(d, 1);
                    }
                }
            }
        }
        if(b.size() == 0){
            List<String> EmptyList = new ArrayList<>(Collections.emptyList());
            EmptyList.add("no user");
            return EmptyList;}
        int e = 0;
        ArrayList<String> g = new ArrayList<>();
        int maxValueInMap = (Collections.max(b.values()));
        for (Map.Entry<UUID, Integer> entry : b.entrySet()) {
            if (entry.getValue() == maxValueInMap && e != 3) {
                g.add(getUser(entry.getKey()).getUsername());
                e++;
                b.remove(entry.getKey());
            }
        }
        return g;
    }
    /**
     * @param a id of user
     * return lend wish list
     */
    public List<String> getWishLend(UUID a) {
        return getUser(a).getWishLend();
    }
    /**
     * @param a id of user
     * return borrow wish list
     */
    public List<String> getWishBorrow(UUID a) {
        return getUser(a).getWishBorrow();
    }
    /**
     * @param a name of user
     * return the week transaction limit of user
     */
    public int getWeekTransactionLimit(String a) {
        dataAccess.deSerialize();
        return getUser(a).getWeekTransactionLimit();
    }
    /**
     * @param a name of user
     * return the Incomplete Transaction Limit of user
     */
    public int getIncompleteTransactionLimit(String a) {
        dataAccess.deSerialize();
        return getUser(a).getIncompleteTransactionLimit();
    }

    /**
     * @param a name of user
     * get the difference between lend amount and borrow amount
     */
    public int getDiff(String a) {
        dataAccess.deSerialize();
        dataAccess.updateSer();
        return getUser(a).getDiff();
    }
    /**
     * @param a user
     * return the password of the user
     */
    public String getPassword(ClientUser a) {
        dataAccess.deSerialize();
        dataAccess.updateSer();
        return a.getPassword();
    }

    /**
     * @param a the id of the user that the manager wants to get
     * return user name
     */
    public String getUsername(UUID a){
        return getUser(a).getUsername();}

    /**
     * @param a user
     * return the id of the user
     */
    public UUID getId(ClientUser a) {
        return a.getId();
    }
    /**
     * @param name name of user
     * @param password the input string
     * set the input string as the password
     */
    public void setPassword(String name, String password) {
        ClientUser a = popUser(nameToUUID(name));
        a.setPassword(password);
        addUser(a);
    }

    /**
     * @param userID the id of the user that the manager wants to get
     * return whether the user is frozen or not
     */
    public boolean getIsFrozen(UUID userID){
        return getUser(userID).getIsFrozen();
    }

    /**
     * @param a user
     * @param password the input string
     * set the input string as the password
     */
    public void set(ClientUser a, String password){a.setPassword(password);}

    /**
     * @param a user
     * return list of  actions
     */
    public ArrayList<UserAction> getActions(ClientUser a) {
        return a.getActions();
    }

    /**
     * @param hi wish borrow that wants to remove
     * @param b user
     * remove lend borrow from list
     */
    public void removeBWishes(String hi,ClientUser b){

        b.removeBWishes(hi);
    }

    /**
     * @param userID the id of the user that the manager wants to get
     * return name of user
     */
    public String UUIDToName(UUID userID){
        return getUser(userID).getUsername();
    }

    /**
     * @param name the name of the user that the manager wants to get
     * return id of user
     */
    public UUID nameToUUID(String name){
        return getUser(name).getId();
    }

    /**
     * @param userID the id of the user that the manager wants to get
     * return list of bonus trade the user wants to select
     */
    public List<UUID> getSelectedBonusTrades(UUID userID){
        return getUser(userID).getSelectedBonusTrades();
    }

    /**
     * @param username the name of the user that the manager wants to get
     * return total trade number in seven days of user
     */
    public int getTradeNumber(String username) {
        ClientUser user = getUser(username);

        if(user.getTradeHistory().size() == 0){return 0;}
        Trade s = getTrade(user.getTradeHistory().get(user.getTradeHistory().size() - 1));
        LocalDateTime x  = LocalDateTime.now();
        LocalDateTime y = x.minusDays(7);
        int number = 0;
        for (UUID i : user.getTradeHistory()){
            if((!getTrade(i).getStatus().equals(TradeStatus.unconfirmed)) && getTrade(i).getCreateTime().isAfter(y) && getTrade(i).getCreateTime().isBefore(x)){
                number ++;
            }
        }
        for (UUID j: user.getSelectedBonusTrades()) {
            if(getTrade(j).getCreateTime().isAfter(y) && getTrade(j).getCreateTime().isBefore(x)){
                number --;
            }
        }
        return number;
    }

    /**
     * @param id the id of the trade of the  user that the manager wants to get
     * return that trade
     */
    public Trade getTrade(UUID id) {
        return (Trade) tradeAccess.getObject(id);
    }

    /**
     * @param userId the id of the trade of the  user that the manager wants to get
     * return the number of incomplete trade that the user has
     */
    public int getIncompleteTransaction(UUID userId) {
        ClientUser user = (ClientUser) dataAccess.getObject(userId);

        int number = 0;
        for (UUID i : user.getTradeHistory()) {
            if (getTrade(i).getStatus().equals(TradeStatus.incomplete)) {
                number++;
            }
        }
        for (UUID j: user.getSelectedBonusTrades()) {
            if(getTrade(j).getStatus().equals(TradeStatus.incomplete)){
                number --;
            }
        }
        return number;
    }
    /**
     * return the exchange standard of the user
     */
    public int getExStandard() {
        return this.getUserList().get(0).getExStandard();
    }

    /**
     * @param username name of user
     * @param end end time of left
     * return the exchange standard of the user
     */
    public void setEnd(String username, LocalDateTime end){
        dataAccess.deSerialize();
        dataAccess.updateSer();
//        dataAccess.deSerialize();
        ClientUser user = popUser(nameToUUID(username));
        user.setEnd(end);
        if(LocalDateTime.now().isBefore(end)){
            user.setLeft(true);
            user.setFrozen(true);
        }
        addUser(user);
//        dataAccess.updateSer();
    }

    public void  setLeft(String username, boolean a){
        ClientUser ca = (ClientUser)dataAccess.getObject(username);
        if(ca != null){ca.setLeft(a);}
        dataAccess.updateSer();
    }

    public void setFreeze(String a,boolean s){
        ClientUser ca = (ClientUser)dataAccess.getObject(a);
        if(ca != null){ca.setFrozen(s);}
        dataAccess.updateSer();
    }

    /**
     * @param userID id of user
     * return whether the user is left or not
     */
    public boolean getIsLeft(UUID userID){
        return getUser(userID).getIsLeft();
    }

    /**
     * @param username name of user
     * @param action the action that need to add
     * add the action into user's actions
     */
    public void addAction(String username,UserAction action){
        ClientUser a = popUser(nameToUUID(username));
        a.addActions(action);
        addUser(a);
    }

    /**
     * @param username name of user
     * @param ua the instance of UserAction
     * remove the action from user's list.
     */

    public void removeAction(String username,UserAction ua){
        ClientUser a = popUser(nameToUUID(username));
        ArrayList<UserAction> actions=getActions(a);
        UserAction b = null;
        for (UserAction action : actions) {
            if (action.getIndicator().equals(ua.getIndicator())) {
                b=action;
            }
        }
        a.removeAction(b);
        addUser(a);
    }

    /**
     * @param username name of user
     * @param lendWish name of the item
     * remove wish from user
     */

    public void deleteLItem(String username, String lendWish) {
        ClientUser user = popUser(getUser(username).getId());
        user.getWishLend().remove(lendWish);
        addUser(user);
    }
    /**
     * @param username name of user
     * @param borrowWish the name of item
     * remove the borrowWish from user's list.
     */
    public void deleteBItem(String username, String borrowWish) {
        ClientUser user = popUser(getUser(username).getId());
        user.getWishBorrow().remove(borrowWish);
        addUser(user);
    }
}

