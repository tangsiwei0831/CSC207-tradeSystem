package Inventory.Adaptor;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iPresent;

/**
 * [presenter]
 * Presenter for market system
 */
public class MarketPresenter implements iMarketPresenter {
    private final iPresent bta;

    /**
     * [constructor]
     * @param bta view
     */
    public MarketPresenter(BorderGUI bta){
        this.bta = bta;
    }

    /**
     * reset input area
     */
    public void resetInputArea(){
        bta.setInput("input","item name");
    }

    /**
     * update presentation of available list
     * @param availableList available item list of inventory
     */
    public void updateListM(String availableList){
        bta.setListText(availableList);
    }

    /**
     * close current frame
     */
    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }

    /**
     * reset current area
     */
    public void resetCurr(){
        bta.setCurrText("not item selected");
    }

    /**
     * notify users about wrong input
     */
    public void wrongInput(){
        bta.setMsgText("wrong input");
    }

    /**
     * update current item with Item Info
     * @param itemInfo item info
     */
    public void updateCurr(String itemInfo){

        bta.setCurrText(itemInfo);
    }
}
