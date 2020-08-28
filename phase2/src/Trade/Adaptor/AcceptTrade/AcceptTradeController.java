package Trade.Adaptor.AcceptTrade;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iTradeController;
import Trade.Adaptor.iTradePresenter;
import Trade.Entity.Trade;
import Trade.TradeStatus;
import Trade.UseCase.TradeManager;
import User.UseCase.UserManager;
import javax.swing.*;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * [Controller]
 * controller for accept trade session
 */
public class AcceptTradeController implements iTradeController {
    private final UUID currUser;
    private final TradeManager tm;
    private UserManager um;
    private UUID currTrade;
    private final iTradePresenter tp;
    private final JFrame frame;

    /**
     * [Constructor]
     * @param userName current user name
     * @param tg view
     * @param fr last frame
     */
    public AcceptTradeController(String userName, BorderGUI tg, JFrame fr){
        this.tm = new TradeManager();
        this.um = new UserManager();
        tp = new AcceptTradePresenter(tg);
        frame = fr;
        currUser = um.nameToUUID(userName);
    }

    /**
     * check whether input is an integer
     * @param num input string
     * @return boolean
     */
    private boolean checkInput(String num){
        if (!isNum(num)){
            return false;
        }else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tm.getUnconfirmed(currUser).size());
    }

    /**
     * get trade id with input string
     * @param num
     * @return
     */
    public UUID getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tm.getUnconfirmed(currUser).get(tradeNum).getId();
        return currTrade;

    }

    /**
     * check whether a string is an integer
     * @param str input string
     * @return boolean
     */
    private boolean isNum(String str){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * set current trade's status to incomplete
     */
    private void agreeTrade(){
        Trade trade = tm.popTrade(currTrade);
        trade.setStatus(TradeStatus.incomplete);
        tm.addTrade(trade);
    }

    /**
     * set current trade's status to cancelled
     */
    private void refuseTrade(){
        Trade trade = tm.popTrade(currTrade);
        trade.setStatus(TradeStatus.cancelled);
        tm.addTrade(trade);
    }

    /**
     * action of agree and refuse button
     * @param agree whether to agree or refuse
     */
    private void agreeBut(Boolean agree){
        if (currTrade == null){
            tp.notTradeSelected();
        } else{
            if (agree){
                agreeTrade();
                tp.ActionSuccess(tm.getUnconfirmed(currUser), true);
            }else{
                refuseTrade();
                tp.ActionSuccess(tm.getUnconfirmed(currUser), false);
            }
            currTrade = null;

        }
    }

    /**
     * set current trade
     * @param tradeNum input trade number
     */
    public void submitBut(String tradeNum){
        if (!checkInput(tradeNum)){
            tp.wrongInput();
        }else{
            currTrade = getCurrTrade(tradeNum);
            tp.presentTradeInfo(tm.getTrade(currTrade));
            tp.updateSuccess();
        }
    }

    /**
     * back to last frame
     */
    public void backBut(){
        frame.setVisible(true);
        tp.closeFrame();
    }

    /**
     * action of update button
     */
    public void updateBut(){
        tp.updateFrame(tm.getUnconfirmed(currUser));
        tp.noTradeCurr();
    }

    /**
     * update list information
     */
    public void updateList(){
        tp.updateFrame(tm.getUnconfirmed(currUser));
    }

    /**
     * notify users if no trade is selected
     */
    public void noTradeSelected(){
        tp.noTradeCurr();
    }

    /**
     * perform agree action
     */
    @Override
    public void performAction1() {
        agreeBut(true);
    }

    /**
     * perform disagree action
     */
    @Override
    public void performAction2() {
        agreeBut(false);
    }


}
