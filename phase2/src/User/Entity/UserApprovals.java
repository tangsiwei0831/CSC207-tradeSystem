package User.Entity;

import java.io.Serializable;
/**
 * [Entity class]
 */
public class UserApprovals implements Serializable {
    ClientUser user;
    String fstString;

    /**
     * [Constructor]
     * @param user the user account
     * @param fstString item name
     */
    public UserApprovals(ClientUser user, String fstString){
        this.user=user;
        this.fstString=fstString;
    }

    /**
     * return user name
     */
    public String getCurUserName(){
        return user.getUsername();
    }

    /**
     * return user name
     */
    public String getFstString(){
        return fstString;
    }

    /**
     * return toString
     */
    @Override
    public String toString() {
        return "User: "+getCurUserName()+"\nReasons: "+getFstString()+"\n";
    }
}
