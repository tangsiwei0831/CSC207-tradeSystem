package Trade.Adaptor.CompleteTrade;

import MeetingSystem.UseCase.MeetingActionManager;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iPresent;
import Trade.Adaptor.iTradePresenter;
import Trade.Entity.Trade;
import User.UseCase.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * [presenter]
 * present info about complete trade session to users
 */
public class CTradePresenter implements iTradePresenter {
    private final iPresent tg;

    /**
     * [constructor]
     * @param tg view
     */
    public CTradePresenter(BorderGUI tg) {
        this.tg = tg;
    }

    /**
     * close current frame
     */
    public void closeFrame(){
        tg.getFrame().setVisible(false);
    }

    /**
     * update presentation of incomplete trade list
     * @param tradeList
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
     * reset input area
     */
    public void resetInputArea(){
        tg.setInput("input", "Trade Number");
    }

    /**
     * present trade info given the Trade object
     * @param trade Trade
     */
    public void presentTradeInfo(Trade trade){
        tg.setCurrText(trade.toString());
    }

    /**
     * notify users if trade info is updated
     */
    public void updateSuccess(){
        tg.setMsgText("Trade info has been updated successfully");
    }

    /**
     * notify users if input is not currect
     */
    public void wrongInput(){
        tg.setMsgText("wrong input");
    }

    /**
     * notify users if no trade is selected
     */
    public void noTradeCurr(){
        tg.setCurrText("no trade selected");
    }

    /**
     * notify users if no trade is selected
     */
    public void notTradeSelected(){
        tg.setMsgText("no trade selected");
    }

    /**
     * no use
     * @param tl na
     * @param agree na
     */
    public void ActionSuccess(List<Trade> tl, boolean agree) {
    }


}
