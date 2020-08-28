package Trade.Adaptor.TradeHistory;

import MeetingSystem.UseCase.MeetingActionManager;
import Trade.Entity.Trade;
import User.UseCase.UserManager;

import java.util.ArrayList;
import java.util.List;

interface iTradeHistoryPresenter {

    void closeFrame();

    void updateTrade(List<Trade> tradeList);

    void updateFreUser(List<String> userList);
}
