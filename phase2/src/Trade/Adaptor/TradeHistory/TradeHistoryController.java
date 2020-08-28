package Trade.Adaptor.TradeHistory;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iTradeController;
import Trade.Entity.Trade;
import User.UseCase.UserManager;
import Trade.UseCase.TradeManager;
import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class TradeHistoryController implements iTradeController {
    UUID currUser;
    TradeManager tm;
    UserManager um;
    iTradeHistoryPresenter tp;
    JFrame fr;

    public TradeHistoryController(String currUser, BorderGUI tgp, JFrame fr){
        this.tm = new TradeManager();
        this.um = new UserManager();
        tp = new TradeHistoryPresenter(tgp);
        this.currUser = um.nameToUUID(currUser);
        this.fr = fr;

    }


    @Override
    public UUID getCurrTrade(String num) {
        return null;
    }


    public void backBut(){
        fr.setVisible(true);
        tp.closeFrame();
    }



    private void updateTrade(){
        tp.updateTrade(tm.getComplete(currUser));
    }

    private void updateUser(){
        tp.updateFreUser(um.getFrequentUser(currUser));

    }

    public void updateBut(){
        updateTrade();
        updateUser();
    }

    @Override
    public void noTradeSelected() {
    }

    @Override
    public void performAction1() {
    }

    @Override
    public void performAction2() {
    }

    @Override
    public void submitBut(String tradeNum){
    }

    public void updateList(){
        updateTrade();
    }

}
