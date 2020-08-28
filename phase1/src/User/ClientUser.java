package User;

import java.util.*;
import java.util.UUID;
/**
 * [Entity class]
 * The basic functionality of users
 */
public class ClientUser {
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
     * the list of names of items that the user has lent and borrowed
     */
    private List<String> lend;
    private List<String> borrowed;
    /**
     * the difference amount between the amount of
     */
    private int diff = 2;
    /**
     * the counters counts the lend amount and borrow amount
     */
    private int lendCounter;
    private int borrowCounter;


    /**
     * @param username the username of the user account
     * @param password the password of the user account
     * @param isAdmin the boolean shows if the user is administrative user or not
     * ID is random generated and is unique
     */
    public ClientUser(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        //this.id = id;
        this.weekTransactionLimit=5;
        this.incompleteTransactionLimit=2;
        this.notification=new ArrayList<>();
        this.wishLend= new ArrayList<>();
        this.wishBorrow= new ArrayList<>();
        this.tradeHistory=new ArrayList<>();
        this.isAdmin = isAdmin;
        id = UUID.randomUUID();
        lendCounter = 0;
        borrowCounter = 0;
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
     * the real difference between borrow amount and lend amount
     */
    public int readDiff(){
        return lendCounter - borrowCounter;
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
     * @param hi the name of the item
     * the name of item that the user does not have to lend
     */
    public void removeLWishes(String hi){
        this.wishLend.remove(hi);
    }

    /**
     * @param wishLend the list of items the user wants to lend
     * set the list of items that the user wants to lend as wishlend list
     */
    public void setWishLend(List<String> wishLend){
        this.wishLend = wishLend;
    }

    /**
     * @param notification all the notifications the user has
     * set all the notifications as the list
     */
    public void setNotification(List<String> notification){
        this.notification = notification;
    }

    /**
     * @param diff the number of the difference between number of borrowed items and lend items
     * set the integer as the number of the difference between number of borrowed items and lend items
     */
    public void setDiff(int diff) {
        this.diff = diff;
    }

    /**
     * @param tradeHistory all the trades' ids that the user has
     * set the user's trade history as the list of ids
     */
    public void setTradeHistory(List<UUID> tradeHistory) {
        this.tradeHistory = tradeHistory;
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
     * @param wishBorrow the input item list
     * set the borrow list as input item list
     */
    public void setWishBorrow(ArrayList<String> wishBorrow) {
        this.wishBorrow =wishBorrow;
    }

    /**
     * @param password the input string
     * set the input string as the password
     */
    public void setPassword(String password) {
        this.password=password;
    }



    /**
     * return the status that whether the user can borrow or not
     */
    public boolean getIsBorrow() {
        return isBorrow;
    }

    /**
     * return the lst of names of items that the user lends
     */
    public List<String> getLend() {
        return lend;
    }

    /**
     * return the lst of names of items that the user borrows
     */
    public List<String> getBorrowed() {
        return borrowed;
    }

}