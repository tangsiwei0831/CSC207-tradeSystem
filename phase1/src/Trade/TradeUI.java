package Trade;

import MeetingSystem.MeetingSystem;
import User.ClientUser;
import User.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * [user interface-trade]
 * trade system
 * allow users to confirm and edit trade
 */
public class TradeUI {
    ClientUser currUser;
    Trade trade;
    TradeController tc;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    TradePresenter tp;


    /**
     * constructor
     * @param currUser the user that is using the system
     * @param tradeId the trade id of the current trade
     */
    public TradeUI(ClientUser currUser, UUID tradeId, TradeManager tm, UserManager um){
        this.currUser = currUser;
        trade = tm.getTrade(tradeId);
        tc = new TradeController(currUser, trade, tm, um);
        tp = new TradePresenter(currUser, trade);

    }

    /**
     * run the system
     * @throws IOException can not update edition to file
     */
    public void run(){
        boolean becomeComplete = false;

        int exit = 0;
        while (exit != 1) {
            while (true) {
                if (becomeComplete){
                    tc.completeTrade();
                }
                tp.presentTradeUIInfo();
                tp.enterTrade();
                try {
                    String line = br.readLine();
                    if (line.equals("1")) {
                        exit = 1;
                        tp.exitTrade();
                        break;
                    } else {
                        switch (tc.checkTradeMeeting()) {
                            case "cancelled":
                                tp.cancelTrade();
                                break;
                            case "complete":
                                tp.completeTrade();
                                break;
                            case "confirm trade":
                                confirmTrade();
                                break;
                            case "first meeting" :
                                tp.enterFirstM();
                                MeetingSystem mt = new MeetingSystem(trade.getUsers(), true, trade.getMeeting());
                                mt.run(currUser.getId());
                                trade.changeMeeting(mt.getMeeting());
                                if (trade.getMeeting() == null){
                                    break;
                                }
                                ArrayList<Object> result = mt.runResult();
                                if (result.get(2).equals("completed")){
                                    if(trade.getDuration() == -1) {
                                        becomeComplete = true;
                                    }else{
                                        trade.changeSecondMeeting(mt.setUpSecondMeeting(trade.getMeeting()));
                                    }
                                }
                                if (result.get(2).equals("setUp")) {
                                    System.out.println(result);
                                    trade.setMeeting((LocalDateTime) result.get(0),
                                            (String) result.get(1), trade.getUsers());
                                    trade.changeMeeting(mt.getMeeting());
                                }

                                if (result.get(2).equals("cancelled")) {
                                    tc.cancelTrade();
                                }
                                break;
                            case "second meeting":
                                tp.enterSecondM();
                                MeetingSystem smt = new MeetingSystem(trade.getUsers(), false,
                                        trade.getSecondMeeting());
                                smt.run(currUser.getId());
                                trade.changeSecondMeeting(smt.getMeeting());
                                ArrayList<Object> result2 = smt.runResult();
                                if (result2.get(2).equals("completed")) {
                                    becomeComplete = true;
                                }
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * allow users to confirm unconfirmed trade
     */
    private void confirmTrade(){
        while (true) {
            if (trade.getCreator().equals(currUser.getId())){
                System.out.println("you can not confirm the trade, please wait for another user to confirm");
                break;
            }
            tp.selectConfirm();
            try {
                String confirm = br.readLine();
                if (confirm.equals("1")) {
                    tc.confirmTrade();
                    tp.confirmTrade();
                    break;
                }
                else if (confirm.equals("2")){
                    tc.cancelTrade();
                    tp.cancelTrade();
                    break;
                }else{
                    tp.wrongInput();
                }
            } catch (IOException e) {
                tp.wrongInput();
                e.printStackTrace();
            }
        }
    }

}

