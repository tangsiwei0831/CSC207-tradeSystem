package User.Adapter;

import User.Actions.PasswordUserAction;
import User.Actions.UserAction;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import java.util.ArrayList;

public class ActionController {
    ApprovalManager iam= new ApprovalManager();
    UserManager um=new UserManager();
    public void addAction(String username, UserAction action) {
        um.addAction(username,action);
    }

    /**
     * @param username name of user
     * return list of list of UserActions
     */
    public ArrayList<UserAction> getActions(String username) {
        return um.getActions(um.getUser(username));
    }



    public void check(UserAction ua){
        switch (ua.getType()) {
            case "pass":
                passWordReverse(ua.getUsername(), ua.getItem());
                break;
            case "Freeze Ticket":
                deleteFreezeTicket(ua.getUsername());
                break;
            case "add to borrow":
                deleteWishBorrow(ua.getUsername(), ua.getItem());
                break;
            case "add to wish":
                deleteItemTicket(ua.getItem());
                break;
        }
    }
    private void passWordReverse(String username,String password){
        um.setPassword(username,password);
    }
    private void deleteFreezeTicket(String username){
        iam.removeUserApproval(username);
    }

    private void deleteWishBorrow(String username,String borrowWish){
        um.deleteBItem(username,borrowWish);
    }
    private void deleteItemTicket(String itemName){
        iam.removeItemApproval(itemName);
    }
    public void reverse(String username){
        System.out.println(um.getUser(username).getActions());
        UserAction ua= getActions(username).get(0);
        check(ua);
        removeAction(username,ua);
    }
    public void removeAction(String username,UserAction ua){
        um.removeAction(username,ua);
    }

}
