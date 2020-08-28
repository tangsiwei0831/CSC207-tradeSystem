package Trade.Adaptor.RequestTrade;

import Inventory.Entity.Item;
import User.Entity.ClientUser;

import java.util.ArrayList;
import java.util.List;

public interface iRTradePresenter {

    void wrongInput();

    void presentTradeInfo(ClientUser currUser, Item item, List<String> secondList, ArrayList<String> suggestList);

    void inTradeError();

    void currAccountFrozen();

    void tarAccountFrozen();

    void tarUserNotFound();

    void selfItem();

    void createSuccess(String dur);

    void updateInputArea();

    void closeFrame();
}
