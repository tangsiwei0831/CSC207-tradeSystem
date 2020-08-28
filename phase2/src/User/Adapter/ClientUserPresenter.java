package User.Adapter;

import Trade.UseCase.TradeManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

/**
 * [Presenter]
 */
public class ClientUserPresenter implements IUserPresenter {

    // View
    JFrame view;

    // Use Case
    UserManager userManager = new UserManager();
    TradeManager tradeManager = new TradeManager();

    // Other
    UUID currUser;
    /**
     * [Constructor]
     */
    public ClientUserPresenter(UUID currUser, JFrame view) {
        this.currUser = currUser;
        this.view = view;
    }

    /**
     * return user manager
     */
    @Override
    public UserManager getUserModel() {
        return userManager;
    }

    /**
     * return trade manager
     */
    @Override
    public TradeManager getTradeModel() {
        return tradeManager;
    }

    @Override
    public void performAction() {

    }

    /**
     * return id of the user
     */
    @Override
    public UUID getCurrUser() {
        return currUser;
    }

    /**
     * run the frame
     */
    @Override
    public void run() {
        view.setVisible(true);
    }
}
