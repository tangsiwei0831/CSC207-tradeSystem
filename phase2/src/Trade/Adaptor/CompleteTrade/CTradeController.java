package Trade.Adaptor.CompleteTrade;

import Trade.Adaptor.BorderGUI;
import MeetingSystem.Adapter.MPresenter;
import MeetingSystem.Adapter.MainViewPresenter;
import MeetingSystem.Entity.Meeting;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.MeetingActionManager;
import Trade.Adaptor.iInput;
import Trade.Adaptor.iTradeController;
import Trade.Adaptor.iTradePresenter;
import Trade.Entity.Trade;
import Trade.TradeStatus;
import User.Entity.ClientUser;
import User.UseCase.UserManager;
import Trade.UseCase.TradeManager;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * [controller]
 * controller for complete trade session
 */
public class CTradeController implements Observer, iTradeController {
    UUID currUser;
    TradeManager tm;
    UserManager um;
    UUID currTrade;
    Boolean isFirst;
    iTradePresenter tp;
    iInput tg;
    JFrame fr;

    /**
     * [constructor]
     * @param currUser current user name
     * @param tg view
     * @param fr last frame
     */
    public CTradeController(String currUser, BorderGUI tg, JFrame fr) {

        tm = new TradeManager();
        um = new UserManager();
        tp = new CTradePresenter(tg);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
        this.tg = tg;
    }


    /**
     * check whether input is correct
     * @param num input string
     * @return boolean
     */
    private boolean checkInput(String num) {
        if (!isNum(num)) {
            return false;
        } else return !(Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tm.getIncomplete(currUser).size());
    }

    /**
     * check whether input is an integer
     * @param str input string
     * @return boolean
     */
    private boolean isNum(String str) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * get trade UUID with input string
     * @param num input string
     * @return trade UUID
     */
    public UUID getCurrTrade(String num) {
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tm.getIncomplete(currUser).get(tradeNum).getId();
        return currTrade;
    }


    /**
     * decide whether to enter first meeting session
     * @return boolean
     */
    private boolean enterFirst() {
        Trade tr = tm.getTrade(currTrade);
        isFirst = (tr.getSecondMeeting() == null);
        return isFirst;
    }


    /**
     * update trade status based on meeting system's notification
     * @param o presenters in meeting system
     * @param arg meeting id
     */
    @Override
    public void update(Observable o, Object arg) {
        Trade tr = tm.getTrade(currTrade);
        UUID mtID = (UUID) arg;
        Meeting mt = new MeetingActionManager().getMeetingWithId(mtID);
        if (isFirst) {
            if (mt.getStatus().equals(MeetingStatus.INCOMPLETE)) {
                Trade trade = tm.popTrade(currTrade);
                trade.setMeeting(mtID);
                tm.addTrade(trade);
            }
            if (mt.getStatus().equals(MeetingStatus.CANCELLED)) {
                Trade trade = tm.popTrade(currTrade);
                trade.setStatus(TradeStatus.cancelled);
                tm.addTrade(trade);

            }
            if (mt.getStatus().equals(MeetingStatus.COMPLETED)) {
                if (tr.getDuration() == -1) {
                    completeTrade();

                } else {
                    Trade trade = tm.popTrade(currTrade);
                    trade.setSecondMeeting(new MeetingActionManager().setUpSecondMeeting(mtID));
                    tm.addTrade(trade);
                }
            }

        } else {
            Meeting smt = new MeetingActionManager().getMeetingWithId(tr.getSecondMeeting());
            if (smt.getStatus().equals(MeetingStatus.COMPLETED)) {
                completeTrade();
            }
        }

    }

    private void completeTrade() {
        Trade trade = tm.popTrade(currTrade);
        trade.setStatus(TradeStatus.complete);
        tm.addTrade(trade);
        makeTrade();
    }

    private void makeTrade() {
        Trade tr = tm.popTrade(currTrade);
        if (tr.getType().equals("one way")) {
            ClientUser bor = um.popUser(tr.getUsers().get(0));
            ClientUser lend = um.popUser(tr.getUsers().get(1));
            bor.getWishBorrow().remove(tr.getItemList().get(0));
            lend.getWishLend().remove(tr.getItemList().get(0));
            bor.setBorrowCounter(bor.getBorrowCounter() + 1);
            lend.setLendCounter(bor.getLendCounter() + 1);
            um.addUser(bor);
            um.addUser(lend);
            tm.addTrade(tr);
        } else {
            ClientUser u1 = um.popUser(tr.getUsers().get(0));
            ClientUser u2 = um.popUser(tr.getUsers().get(1));
            u1.getWishBorrow().remove(tr.getItemList().get(1));
            u1.getWishLend().remove(tr.getItemList().get(0));
            u2.getWishBorrow().remove(tr.getItemList().get(0));
            u2.getWishLend().remove(tr.getItemList().get(1));
            u1.setBorrowCounter(u1.getBorrowCounter() + 1);
            u1.setLendCounter(u1.getLendCounter() + 1);
            u2.setBorrowCounter(u2.getBorrowCounter() + 1);
            u2.setLendCounter(u2.getLendCounter() + 1);
            um.addUser(u1);
            um.addUser(u2);
            tm.addTrade(tr);
        }
    }

    private void closeFrame(){
        tp.closeFrame();
    }

    public void updateBut(){
        tp.updateFrame(tm.getIncomplete(currUser));
        tp.noTradeCurr();
    }

    public void submitBut(String tradeNum){
        tp.resetInputArea();
        if (!checkInput(tradeNum)){
            tp.wrongInput();
        }else{
            currTrade = getCurrTrade(tradeNum);
            tp.presentTradeInfo(tm.getTrade(currTrade));
            tp.updateSuccess();
        }
    }

    public void updateList(){
        tp.updateFrame(tm.getIncomplete(currUser));
    }

    public void resetCurr(){
        tp.noTradeCurr();
    }

    public void backBut(){
        fr.setVisible(true);
        tp.closeFrame();
    }

    public void noTradeSelected(){
        tp.noTradeCurr();
    }

    @Override
    public void performAction1() {
        action();
    }

    @Override
    public void performAction2() {

    }

    private  void checkCurrTrade(Trade trade){
        if (trade == null){
            tp.notTradeSelected();
        }
    }

    private void action(){
        if (currTrade == null){
            tp.notTradeSelected();
        }else{
            Trade tr = tm.getTrade(currTrade);
            ClientUser user = um.getUser(currUser);
            if (enterFirst()) {
                MPresenter mPresenter = new MainViewPresenter(tr.getMeeting(), user.getId(),
                        tr.getUsers(), true, tg.getFrame());
                mPresenter.run();
                mPresenter.setObserver(this);
            }else {
                MPresenter mPresenter = new MainViewPresenter(tr.getSecondMeeting(),
                        user.getId(), tr.getUsers(), false, tg.getFrame());
                mPresenter.run();
                mPresenter.setObserver(this);
            }
            closeFrame();
        }

    }
}
