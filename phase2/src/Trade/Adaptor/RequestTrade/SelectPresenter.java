package Trade.Adaptor.RequestTrade;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iPresent;

import java.util.List;

/**
 * present info of select item to trade session to users
 */
public class SelectPresenter implements iSelectPresenter {

    private final iPresent bta;

    /**
     * [constructor]
     * @param bta view
     */
    public SelectPresenter(BorderGUI bta){
        this.bta = bta;
    }

    /**
     * close current frame
     */
    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }

    /**
     * update available item list
     * @param il list of available item names
     */
    public void updateFrame(List<String> il){
        StringBuilder result = new StringBuilder();
        for (String it: il){
            result.append(it).append("\n");
        }
        bta.setListText(result.toString());
    }

    /**
     * reset input area
     */
    public void resetInputArea(){
        bta.setInput("input", "Trade Number");
    }

    /**
     * notify users if user's input is wrong
     */
    public void wrongInput(){
        bta.setMsgText("wrong input");
    }

    /**
     * set curr area with item info
     * @param itemInfo current item's info
     */
    public void selectItemInfo(String itemInfo){
        bta.setCurrText(itemInfo);
    }

    /**
     * notify users if the item info has been updated
     */
    public void updateSuccess(){
        bta.setMsgText("The item info has been updated");
    }

    /**
     * notify users if no item is selected
     */
    public void notItemSelected(){
        bta.setMsgText("no item is selected");
    }
}
