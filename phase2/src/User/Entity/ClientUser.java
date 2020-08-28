package User.Entity;

import User.Actions.UserAction;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * [Entity class]
 * The basic functionality of users (including ordinary users and administrative users)
 */
public class ClientUser implements Serializable {
    /**
     * the id of user, evey user has unique id.
     */
    protected UUID id;
    /**
     * the name and password of the user account
     */
    protected String password;
    protected String username;
    /**
     * all the messages the user send
     */
    private List<String> notification;
    /**
     * boolean attribute shows that if the user is administrative user or not
     */
    private final boolean isAdmin;
    /**
     * the list of names of items that the user want to lend and borrow
     */
    private List<String> wishLend;
    private List<String> wishBorrow;
    /**
     * boolean attribute shows that if the user have the ability to borrow items or not
     */
    private boolean isBorrow;
    /**
     * boolean attribute shows that if the user account has been frozen or not
     */
    private boolean isFrozen;
    /**
     * List of IDs of all the trade that the user has
     */
    protected List<UUID> tradeHistory;
    /**
     * the maximum number of transactions the user can do within seven days
     */
    private int weekTransactionLimit;
    /**
     * the maximum number of incomplete transactions the user can have.
     */
    private int incompleteTransactionLimit;
    /**
     * the difference amount between the amount of borrow and lend
     */
    private int diff = 2;
    /**
     * the counters counts the lend amount and borrow amount
     */
    private int lendCounter;
    private int borrowCounter;
    private boolean isLeft;
    private LocalDateTime end;

    /**
     * the list of trades that is set to bonus, so that it's not counting towards being frozen
     */
    private final List<UUID> selectedBonusTrades;

    /**
     * the bonus points that the client user has.
     */
    private int bonusPoints;


    private final ArrayList<UserAction> actions;

    /**
     * The points needed to exchange one bonus trade which will not count towards being frozen
     */
    private int exStandard = 5;

    /**
     * @param username the username of the user account
     * @param password the password of the user account
     * @param isAdmin the boolean shows if the user is administrative user or not
     * ID is random generated and is unique
     */
    public ClientUser(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.weekTransactionLimit=5;
        this.incompleteTransactionLimit=2;
        this.notification=new ArrayList<>();
        this.wishLend= new ArrayList<>();
        this.wishBorrow= new ArrayList<>();
        this.tradeHistory=new ArrayList<>();
        this.isAdmin = isAdmin;
        end = null;
        isLeft = false;
        id = UUID.randomUUID();
        this.selectedBonusTrades = new ArrayList<>();
        this.bonusPoints = 0;
        this.actions= new ArrayList<>();
    }

    public void setEnd(LocalDateTime end){
        this.end = end;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean getIsLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft =  left;
    }

    /**
     * @param borrowCounter the input integer
     * set the borrowCounter as the input integer
     */
    public void setBorrowCounter(int borrowCounter) {
        this.borrowCounter = borrowCounter;
    }

    /**
     * @param lendCounter the input integer
     * set the lendCounter as the input integer
     */
    public void setLendCounter(int lendCounter) {
        this.lendCounter = lendCounter;
    }

    /**
     * return borrowCounter
     */
    public int getBorrowCounter() {
        return borrowCounter;
    }

    /**
     * return the lendCounter
     */
    public int getLendCounter() {
        return lendCounter;
    }

    /**
     * return the diff of the user between the borrow amount and lend amount
     */
    public int getDiff() {
        return diff;
    }

    /**
     * return the password of the user
     */
    public String getPassword() {
        return password;
    }
    /**
     * return the name of the user
     */
    public String getUsername(){return username;}
    /**
     * return the id of the user
     */
    public UUID getId() {
        return id;
    }
    /**
     * return whether the user account is being frozen
     */
    public boolean getIsFrozen(){
        return isFrozen;
    }
    /**
     * return all the notifications the user has
     */
    public List<String> getNotification(){return notification;}

    /**
     * @param incompleteTransaction the maximum number of incomplete transactions user can have
     * set the input integer as the maximum incomplete transaction number of a user
     */
    public void setIncompleteTransaction(int incompleteTransaction) {
        this.incompleteTransactionLimit = incompleteTransaction;
    }

    /**
     * @param a id we want to set for the user
     * set the input id for the user
     */
    public void setId(UUID a){this.id = a;}
    /**
     * @param weekTransaction  the week transaction limit we want to set for the user
     * set the week transaction limit for the user
     */
    public void setWeekTransactionLimit(int weekTransaction){
        this.weekTransactionLimit = weekTransaction;
    }

    /**
     * @param hi the name of the item
     * add the name of item that the user wants to lend into list
     */
    public void addWishes(String hi){
        this.wishLend.add(hi);
    }

    /**
     * @param name the name of the item
     * add the name of item that the user wants to borrow into list
     */
    public void addWishBorrow(String name){
        this.wishBorrow.add(name);
    }
    /**
     * @param hi the name of the item
     * the name of item that the user does not have to borrow
     */
    public void removeBWishes(String hi){
        this.wishBorrow.remove(hi);
    }

    /**
     * @param diff the number of the difference between number of borrowed items and lend items
     * set the integer as the number of the difference between number of borrowed items and lend items
     */
    public void setDiff(int diff) {
        this.diff = diff;
    }

    /**
     * get all the trades' ids the user has
     */
    public List<UUID> getTradeHistory(){
        return tradeHistory;
    }


    /**
     * return whether the user is administrative user or not
     */
    public boolean getIsAdmin(){return isAdmin;}

    /**
     * return the list of items that the user wants to lend
     */
    public List<String> getWishLend() {
        return wishLend;
    }

    /**
     * return the list of items that the user wants to borrow
     */
    public List<String> getWishBorrow() {
        return wishBorrow;
    }

    /**
     * @param aTrue the status of the account the user want to set
     * set the status of the account of user to be aTrue
     */
    public void setFrozen(boolean aTrue){
        isFrozen = aTrue;
    }

    /**
     * @param borrow the status of the account the user want to set
     * set the status of the account of user to be borrow
     */
    public void setBorrow(boolean borrow){

        isBorrow = borrow;
    }

    /**
     * return the week transaction limit
     */
    public int getWeekTransactionLimit() {
        return weekTransactionLimit;
    }

    /**
     * return the incomplete transaction limit
     */
    public int getIncompleteTransactionLimit() {
        return incompleteTransactionLimit;
    }

    /**
     * @param password the input string
     * set the input string as the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * return the list of trades that the user set to bonus.
     * Once a trade is set to bonus, the bonusPoints reduce by 1.
     */
    public List<UUID> getSelectedBonusTrades(){ return this.selectedBonusTrades;}

    /***/
    public void addBonusTrade(UUID tradeId){ this.selectedBonusTrades.add(tradeId);}

    /**
     * return the bonus points the client user currently has
     */
    public int getBonusPoints(){return this.bonusPoints;}

    /**
     * Set new bonusPoints
     */
    public void setBonusPoints(int newPoints) {this.bonusPoints = newPoints;}

    /**
     * return actions
     */
    public ArrayList<UserAction> getActions() {
        return this.actions;
    }

    /**
     * return exchange standard
     */
    public int getExStandard(){return this.exStandard;}

    /**
     * @param newStandard the new exchange standard
     * Set exchange standard
     */
    public void setExStandard(int newStandard){this.exStandard = newStandard;}


    public void addActions(UserAction action){
        actions.add(0,action);
    }

    public void removeAction(UserAction action){
        actions.remove(action);
    }


}