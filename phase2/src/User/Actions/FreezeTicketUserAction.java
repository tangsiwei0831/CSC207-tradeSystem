package User.Actions;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;

public class FreezeTicketUserAction implements UserAction {
    ClientUser user;
    public FreezeTicketUserAction(ClientUser user){
        this.user=user;
    }


    @Override
    public String getIndicator() {
        return getType()+user.getUsername()+getItem();
    }

    @Override
    public String getType() {
        return "Freeze Ticket";
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }


    @Override
    public String getItem() {
        return "";
    }
}
