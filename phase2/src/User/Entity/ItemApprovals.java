package User.Entity;


import java.io.Serializable;
/**
 * [Entity class]
 */
public class ItemApprovals extends UserApprovals implements Serializable {
    private final String secString;

    /**
     * [Constructor]
     * @param user the user account
     * @param fstString item name
     * @param secString the description
     */
    public ItemApprovals(ClientUser user, String fstString, String secString) {
        super(user, fstString);
        this.secString=secString;
        this.fstString=fstString;
    }

    /**
     * return description
     */
    public String getSecString() {
        return secString;
    }

    /**
     * return toString
     */
    @Override
    public String toString() {
        return "User: "+getCurUserName()+"\nItem name: "+getFstString()+"\nDescription: "+getSecString()+"\n";
    }
}
