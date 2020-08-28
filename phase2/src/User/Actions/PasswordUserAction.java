package User.Actions;

import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import java.util.ArrayList;

public class PasswordUserAction implements UserAction {
    String prePass;
    ClientUser user;
    public PasswordUserAction(ClientUser user, String prePass){
        this.user=user;
        this.prePass=prePass;
    }

    @Override
    public String getIndicator() {
        return "PasswordUserAction"+getUsername()+prePass;
    }

    @Override
    public String getType() {
        return "pass";
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getItem() {
        return prePass;
    }

    @Override
    public String toString() {
        return "Changed password from "+prePass+" to "+user.getPassword();
    }
}
