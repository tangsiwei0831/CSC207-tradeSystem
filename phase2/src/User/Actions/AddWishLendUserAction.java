package User.Actions;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;

public class AddWishLendUserAction implements UserAction {
    ClientUser user;
    String itemName;
    public AddWishLendUserAction(ClientUser user, String itemName){
        this.user=user;
        this.itemName=itemName;
    }

    @Override
    public String getIndicator() {
        return "AddWishLendUserAction"+itemName;
    }

    @Override
    public String getType() {
        return "add to wish";
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getItem() {
        return itemName;
    }

    @Override
    public String toString() {
        return "Requested: "+itemName;
    }
}
