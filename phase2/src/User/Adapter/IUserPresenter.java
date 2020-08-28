package User.Adapter;

import Trade.UseCase.TradeManager;
import User.UseCase.UserManager;

import java.util.UUID;

public interface IUserPresenter {

    void performAction();

    UUID getCurrUser();

    void run();

    UserManager getUserModel();

    TradeManager getTradeModel();
}
