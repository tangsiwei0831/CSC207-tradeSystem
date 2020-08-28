package User.UseCase;



import User.Entity.ClientUser;
import User.Gateway.ApprovalItemDataAccess;
import User.Gateway.ApprovalUserDataAccess;
import User.Entity.ItemApprovals;
import User.Entity.UserApprovals;
import User.Gateway.DataAccess;

import java.util.ArrayList;

public class ApprovalManager {
    DataAccess itemDataAccess = new ApprovalItemDataAccess();
    DataAccess userDataAccess = new ApprovalUserDataAccess();

    /**
     * return list of items
     */
    public ArrayList<ItemApprovals> getItemApprovals(){
        ArrayList<ItemApprovals> result = new ArrayList<>();
        for (Object o : itemDataAccess.getList()) {
            result.add((ItemApprovals) o);
        }
        return result;
    }

    /**
     * return list of users
     */
    public ArrayList<UserApprovals> getUserApprovals(){
        ArrayList<UserApprovals> result = new ArrayList<>();
        for (Object o : userDataAccess.getList()) {
            result.add((UserApprovals) o);
        }
        return result;
    }
    /**
     * @param ua  name of item
     * remove item from list
     */
    public void removeItemApproval(String ua){
        itemDataAccess.removeObject(ua);
    }
    /**
     * @param ua  name of user
     * remove user from list
     */
    public void removeUserApproval(String ua){
        userDataAccess.removeObject(ua);
    }
    /**
     * @param username  name of items
     * get item list
     */
    public ItemApprovals getItemApproval(String username){
        return (ItemApprovals) itemDataAccess.getObject(username);
    }

    /**
     * return toString of items
     */
    public String AllItemApprovals(){
        StringBuilder result= new StringBuilder();
        ArrayList<ItemApprovals> itemApprovals=getItemApprovals();
        for (ItemApprovals i : itemApprovals) {
            result.append(i.toString());
        }
        if(result.toString().equals("")){
            return "Currently there is no item approvals";
        }
        return result.toString();
    }

    /**
     * return toString of users
     */
    public String AllUserApprovals(){
        StringBuilder result= new StringBuilder();
        ArrayList<UserApprovals> userApprovals=getUserApprovals();
        for (UserApprovals i : userApprovals) {
            result.append(i.toString());
        }
        if(result.toString().equals("")){
            return "Currently there is no user unfreeze ticket";
        }
        return result.toString();
    }
    /**
     * @param name name of item
     * @param user user
     * @param des  description
     * add item into list
     */
    public void addApprovals(ClientUser user,String name,String des){
        itemDataAccess.addObject(new ItemApprovals(user,name,des));
    }
    /**
     * @param user user
     * @param des  description
     * add user into list
     */
    public void addApprovals(ClientUser user,String des){
        userDataAccess.addObject(new UserApprovals(user,des));
    }

    /**
     * @param itemName name of item
     *                 return if list has item or not
     */
    public boolean hasItemApprovals(String itemName) {
        return itemDataAccess.hasObject(itemName);
    }

    /**
     * Check if user with username has approval
     *
     * @param username the username of the user
     * @return if user with username has approval
     */
    public boolean hasUserApproval(String username) {
        return userDataAccess.hasObject(username);
    }
}
