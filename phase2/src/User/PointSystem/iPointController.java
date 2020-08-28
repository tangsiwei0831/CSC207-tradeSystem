package User.PointSystem;

import Trade.Entity.Trade;
import User.Entity.ClientUser;
import java.util.List;

public interface iPointController {

    Trade getCurrTrade(String num);


    boolean checkInput(String num);


    List<Trade> getTradesForExchange(ClientUser user);

    void submitBut(String tradeNum);


    void backBut();

    void updateBut();

    void updatePoint();

    void updateStandard();

    void noTradeSelected();

    void ebBut();

    void updateList();
}
