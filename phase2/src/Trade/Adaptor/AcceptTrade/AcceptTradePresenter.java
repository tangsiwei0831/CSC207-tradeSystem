package Trade.Adaptor.AcceptTrade;


import MeetingSystem.UseCase.MeetingActionManager;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iPresent;
import Trade.Adaptor.iTradePresenter;
import Trade.Entity.Trade;
import User.UseCase.UserManager;
import java.util.ArrayList;
import java.util.List;

/**
 * present info about accept trade session to users
 */
public class AcceptTradePresenter implements iTradePresenter {
    private final iPresent tg;

    /**
     * [constrctor]
     * @param tg: accept trade GUI
     */
    public AcceptTradePresenter(BorderGUI tg) {
        this.tg = tg;
    }

    /**
     * notify users if no trade is selected
     */
    public void notTradeSelected(){
        tg.setMsgText("no trade is selected");

    }

    /**
     * nofity users if action succeeds
     * @param tl a list of unconfirmed trade
     * @param agree whether the user wants to agree or refuse trade
     */
    @Override
    public void ActionSuccess(List<Trade> tl, boolean agree) {
        agreeTrade(tl, agree);
    }

    /**
     * nofity users if action succeeds
     * @param tl a list of unconfirmed trade
     * @param agree whether the user wants to agree or refuse trade
     */
    private void agreeTrade(List<Trade> tl, boolean agree){
        String result = "";
        for (int i = 0; i < tl.size(); i++) {
            result = result + i + ". " + tl.get(i).toString() + "\n";
        }
        if (result.equals("")){
            tg.setListText( "no available trade");
        }else{
            tg.setListText(result);
        }
        if (agree){
            tg.setMsgText("you have agreed the trade");
        }else{
            tg.setMsgText("you have refused the trade");

        }
        tg.setCurrText("no trade selected");
    }

    /**
     * notify users if the input is wrong
     */
    public void wrongInput(){
        tg.setMsgText("wrong input");
    }

    /**
     * reset input area
     */
    public void resetInputArea(){
        tg.setInput("input", "Trade Number");
    }

    /**
     * present input trade's information
     * @param trade trade
     */
    public void presentTradeInfo(Trade trade){
        tg.setCurrText(trade.toString());
    }

    /**
     * notify users if current trade's info has been updated
     */
    public void updateSuccess(){
        tg.setMsgText("Trade info has been updated successfully");
    }

    /**
     * close current frame
     */
    public void closeFrame(){
        tg.getFrame().setVisible(false);
    }

    /**
     * update presented trade list
     * @param tradeList unconfirmed trade list
     */
    public void updateFrame(List<Trade> tradeList){
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            ArrayList<String> users = new ArrayList<>();
            users.add(new UserManager().UUIDToName(tradeList.get(i).getUsers().get(0)));
            users.add(new UserManager().UUIDToName(tradeList.get(i).getUsers().get(1)));
            result = result + i + ". " + "\n" + "Traders: "+ users + "\n" + tradeList.get(i).toString();
            if (tradeList.get(i).getMeeting() != null){
                result = result + "First meeting: "
                        + new MeetingActionManager().getMeetingWithId(tradeList.get(i).getMeeting()).toString() + "\n";
            }
            if (tradeList.get(i).getSecondMeeting() != null){
                result = result + "Second meeting: "
                        + new MeetingActionManager().getMeetingWithId(tradeList.get(i).getSecondMeeting()).toString() + "\n";
            }
        }
        if (result.equals("")){
            tg.setListText("no available trade");
        }else{
            tg.setListText(result);
        }
    }

    /**
     * notify users if no trade is selected
     */
    public void noTradeCurr(){
        tg.setCurrText("no trade selected");
    }


}
