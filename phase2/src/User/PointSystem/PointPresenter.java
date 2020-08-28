package User.PointSystem;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iPresent;
import Trade.Entity.Trade;

import java.util.List;

public class PointPresenter implements iPointPresenter {
    iPresent tg;

    /**
     * [Constructor]
     * Constructs the PointPresenter for presenting text on GUI
     */
    public PointPresenter(BorderGUI tg){
        this.tg = tg;
    }

    /**
     * Close the frame
     */
    public void closeFrame(){
        tg.getFrame().setVisible(false);

    }

    /**
     * Update the frame with list of trades
     * @param tradeList the new list of trades
     */
    public void updateFrame(List<Trade> tradeList){
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            result = result + (i + 1) + ". " + tradeList.get(i).toString()+"\n";
        }
        tg.setListText(result);
    }

    /**
     * Reset the input area where the user inputs trade number to blank
     */
    public void resetInputArea(){
        tg.setInput("input", "Trade Number");
    }

    /**
     * Present the information of trade
     */
    public void presentTradeInfo(Trade trade){
        tg.setCurrText(trade.toString());
    }

    /**
     * Indicates the user that the selected trade is set to bonus successfully
     */
    public void changeSuccess(){
        tg.setMsgText("The trade has been set to bonus");
    }

    /**
     * Indicates the user that the trade information has been updated successfully
     */
    public void updateSuccess(){
        tg.setMsgText("Trade info has been updated successfully");
    }

    /**
     * Indicates invalid input
     */
    public void wrongInput(){
        tg.setMsgText("Wrong input");
    }

    /**
     * Warning that points is less than needed to exchange for a bonus trade
     */
    public void pointNotEnough() {tg.setMsgText("Not enough points to exchange for bonus trade");}

    /**
     * Indicates that there is no trade selected currently
     */
    public void noTradeCurr(){
        tg.setCurrText("No trade selected");
    }

    /**
     * Indicates that there is no trade selected currently
     */
    public void notTradeSelected(){
        tg.setMsgText("No trade is selected");
    }

    /**
     * Indicates the available points the user has
     */
    public void updatePoint(int points){
        tg.setInput("Points", String.valueOf(points));
    }

    /**
     * Indicates the points needed for exchanging one bonus trade
     */
    public void updateStandard(int exStandard) {tg.setInput("Exchange Standard", String.valueOf(exStandard));}

}
