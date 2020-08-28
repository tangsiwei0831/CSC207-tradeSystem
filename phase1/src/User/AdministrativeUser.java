package User;

import Trade.TradeManager;
import java.util.*;
/**
 * [Entity class]
 * The basic functionality of administrative users
 */
public class AdministrativeUser extends ClientUser {
    private final TradeManager tm;
    private final UserManager um;

    /**
     * @param username the username of the user account
     * @param password the password of the user account
     * @param isAdmin the boolean shows if the user is administrative user or not
     * ID is random generated and is unique
     */
    public AdministrativeUser(String username, String password, boolean isAdmin, TradeManager tm, UserManager um){
        super(username, password, isAdmin);
        this.tm = tm;
        this.um = um;
    }


    /**
     * @param a the user that the administrative user wants to set frozen
     * set the user.ClientUser a account frozen
     */
    public void freeze(ClientUser a) {
        a.setFrozen(true);
    }


    /**
     * @param username the name of the administrative user that wants to add
     * @param password the password of the administrative user that wants to add
     * the initial administrative user can add the new administrative user
     */
    public void addNewUser(String username, String password) {
        List<ClientUser> x = um.getUserList();
        if (id.equals(x.get(0).getId())){
        AdministrativeUser a = new AdministrativeUser(username, password, true, tm, um);
        x.add(a);
        }
    }

    /**
     * @param a the user that the administrative user wants to check the transaction limit
     * set the user.ClientUser a account frozen a has exceeded the week transaction limit
     */
    public void tradeLimit(ClientUser a){
        if(!a.getIsFrozen()){
            a.setFrozen(tm.getTradeNumber(a) > a.getWeekTransactionLimit());
        }
    }

    /**
     * @param a the user that the administrative user wants to check the incomplete transaction limit
     * set the user.ClientUser a account frozen if a has exceeded the incomplete transaction limit
     */
    public void incompleteTransaction(ClientUser a){
        if(!a.getIsFrozen()){
            a.setFrozen(tm.getIncompleteTransaction(a) > a.getIncompleteTransactionLimit());
        }
    }

}
