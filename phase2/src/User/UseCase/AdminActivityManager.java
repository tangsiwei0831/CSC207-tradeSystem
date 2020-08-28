package User.UseCase;

import Trade.UseCase.TradeManager;
import User.Entity.ClientUser;
import User.Gateway.DataAccess;
import User.Gateway.UserDataAccess;
import User.PointSystem.PointManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * This is the use case class of administrative user
 * Administrative user are allowed to
 */

public class AdminActivityManager {
    UserManager um = new UserManager();
    TradeManager tm = new TradeManager();
    DataAccess userAccess = new UserDataAccess();


    /**
     * @param a the user that the administrative user wants to set frozen
     * set the user.ClientUser a account frozen
     */
//    public void setFreeze(ClientUser a,boolean s) {
//        a.setFrozen(s);
//    }

    public void setFreeze(String a,boolean s){
        ClientUser ca = (ClientUser)userAccess.getObject(a);
        if(ca != null){ca.setFrozen(s);}
        userAccess.updateSer();
    }

    /**
     * @param username the name of the administrative user that wants to add
     * @param password the password of the administrative user that wants to add
     * the initial administrative user can add the new administrative user
     */


    public void addNewAdmin(String username, String password) {
        if(userAccess.getObject("admin")!= null) {
            um.addUser(new ClientUser(username, password, true));
        }
    }




    /**
     * @param username the user that the administrative user wants to check the incomplete transaction limit
     * set the account of user a frozen if a has exceeded the incomplete transaction limit
     */
    public boolean incompleteTransaction(UUID username){
        int tl = tm.getIncompleteTransaction(username);
        ClientUser a = um.popUser(username);
        boolean setFrozen;
        if(!a.getIsFrozen()) {
            setFrozen = (tl > a.getIncompleteTransactionLimit());
            if (setFrozen) {
                a.setFrozen(true);
            }
        }else{
            setFrozen = false;
        }
        um.addUser(a);
        return setFrozen;
    }

    /**
     * @param username the user name that the administrative user wants to check the transaction limit
     * set the user.ClientUser a account frozen a has exceeded the week transaction limit
     */
    public boolean tradeLimit(UUID username){
        int tl = tm.getTradeNumber(username);
        ClientUser a = um.popUser(username);
        boolean setFrozen;
        if(!a.getIsFrozen()) {
            setFrozen = (tl > a.getWeekTransactionLimit());
            if (setFrozen) {
                a.setFrozen(true);
            }
        }else{
            setFrozen = false;
        }
        um.addUser(a);
        return setFrozen;
    }

    public void  setLeft(String username, boolean a){
        ClientUser ca = (ClientUser)userAccess.getObject(username);
        if(ca != null){ca.setFrozen(a);}
        userAccess.updateSer();
    }



}
