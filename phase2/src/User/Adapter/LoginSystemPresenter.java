package User.Adapter;

import Trade.UseCase.TradeManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

/**
 * [Presenter]
 */
public class LoginSystemPresenter implements IUserPresenter {

    // model
    UserManager um = new UserManager();

    // view
    JFrame view;

    /**
     * @param view frame
     * [Constructor]
     */
    public LoginSystemPresenter(JFrame view) {
        this.view = view;
    }

    /**
     * return user manager
     */
    @Override
    public UserManager getUserModel() {
        return um;
    }

    /**
     * return trade manager
     */
    @Override
    public TradeManager getTradeModel() {
        return null;
    }

    @Override
    public void performAction() {

    }

    /**
     * return id of the user
     */
    @Override
    public UUID getCurrUser() {
        return null;
    }

    /**
     * run the frame
     */
    public void run() {
        view.setVisible(true);
    }

    /**
     * @param name the name of user
     * @param password the password of user
     * register the user
     */
    public boolean register(String name, String password) {

        return um.createClientUser(name,password,false);
    }

    /**
     * @param name the name of user
     * @param password the password of user
     * verify the user
     */
    public boolean login(String name, String password) {
        return um.verifyUser(name, password);
    }


}
