package User.Actions;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;

public class AddWishBorrowUserAction implements UserAction {

    ClientUser user;
    String borrowWish;
    public AddWishBorrowUserAction(ClientUser user, String borrowWish){
        this.user=user;
        this.borrowWish=borrowWish;
    }

    @Override
    public String getIndicator() {
        return "AddWishBorrowUserAction"+getUsername()+borrowWish;
    }

    @Override
    public String getType() {
        return "add to borrow";
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getItem() {
        return borrowWish;
    }
}
