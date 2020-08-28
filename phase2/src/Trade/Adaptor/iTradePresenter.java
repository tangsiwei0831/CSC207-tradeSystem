package Trade.Adaptor;

import Trade.Entity.Trade;

import java.util.List;

public interface iTradePresenter {

    void notTradeSelected();

    void ActionSuccess(List<Trade> tl, boolean agree);

    void wrongInput();

    void resetInputArea();

    void presentTradeInfo(Trade trade);

    void updateSuccess();

    void closeFrame();

    void updateFrame(List<Trade> tradeList);

    void noTradeCurr();

}
