/*package User.UseCase;


import User.Actions.UserAction;
import User.Adapter.ClientUserController;
import User.Adapter.IUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import java.util.ArrayList;

public class RemoveActions {
    ClientUser user;
    IUserController uc;
    ApprovalManager iam;
    ActionManager am;
    public RemoveActions(ClientUser user, IUserController uc, ApprovalManager iam) {
        this.user = user;
        this.uc = uc;
        this.iam = iam;
    }

    public void deleteAction(UserAction ua) {
        String check = ua.getIndicator();
        switch (check) {
            case "password":
                deletePassWord(action.get(1));
                break;
            case "Freeze ticket":
                deleteFreezeTicket();
                break;
            case "Item ticket":
                deleteItemTicket(action.get(1));
                break;
            case "add to borrow":
                deleteWishBorrow(action.get(1));
                break;
        }
    }

    private void deletePassWord(String passAction){
        uc.setPassword(user.getUsername(),passAction);
        am.removeAction(user.getUsername(),"password",passAction);
    }

    private void deleteFreezeTicket(){
        iam.removeUserApproval(user.getUsername());
        uc.removeAction(user.getUsername(),"Freeze ticket","");
    }


    private void deleteWishBorrow(String borrowWish){
        uc.deleteBItem(user.getUsername(),borrowWish);
        uc.removeAction(user.getUsername(),"add to borrow",borrowWish);
    }


    private void deleteItemTicket(String itemName){
        iam.removeItemApproval(itemName);
        uc.removeAction(user.getUsername(),"Item ticket",itemName);
    }
}
*/