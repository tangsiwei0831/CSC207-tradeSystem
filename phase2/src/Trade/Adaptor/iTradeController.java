package Trade.Adaptor;

import Trade.Entity.Trade;

import javax.swing.*;
import java.util.UUID;

public interface iTradeController {
    UUID getCurrTrade(String num);
    void submitBut(String tradeNum);
    void backBut();
    void updateBut();
    void noTradeSelected();
    void performAction1();
    void performAction2();
    void updateList();

}
