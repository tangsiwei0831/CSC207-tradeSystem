package User.PointSystem;

import Trade.Entity.Trade;

import java.util.List;

public interface iPointPresenter {

    void closeFrame();

    void updateFrame(List<Trade> tradeList);

    void resetInputArea();

    void presentTradeInfo(Trade trade);

    void changeSuccess();

    void updateSuccess();

    void wrongInput();

    void pointNotEnough();

    void noTradeCurr();

    void notTradeSelected();

    void updatePoint(int points);

    void updateStandard(int exStandard);
}
