package Trade.Adaptor.RequestTrade;

import Inventory.Entity.Item;
import Trade.Adaptor.iPresent;
import User.Entity.ClientUser;

import java.util.ArrayList;
import java.util.List;

/**
 * [presenter]
 * present the info about request trade session
 */
public class RTradePresenter implements iRTradePresenter {
    private final iPresent bta;

    /**
     * [constructor]
     * @param bta: interface for presenters to present information
     */
    public RTradePresenter(iPresent bta){
        this.bta = bta;
    }


    /**
     * notify users if the input is not correct
     */
    public void wrongInput(){
        bta.setMsgText("wrong input");
    }

    /**
     * present trade info
     * @param currUser current user
     * @param item item to request the trade
     * @param secondList items in lending list
     * @param suggestList items suggested as second item for two way trade
     */
    public void presentTradeInfo(ClientUser currUser, Item item, List<String> secondList, ArrayList<String> suggestList){
        bta.setCurrText("Current User: " + currUser.getUsername() + "\n" + "Item to request the trade: " + item.getName()
                + "\n" + "items in your lending list: "+ secondList
                + "\n" + "Suggest item to lend if make a two way trade: " + suggestList);
    }

    /**
     * notify users if the items that is used to request the trade is already in the trade
     */
    public void inTradeError(){
        bta.setMsgText("The item is already in the trade");
    }

    /**
     * notify users if current user's account is frozen
     */
    public void currAccountFrozen(){
        bta.setMsgText("your account is frozen");
    }

    /**
     * notify users if target user's account is frozen
     */
    public void tarAccountFrozen(){
        bta.setMsgText("target user's account is Frozen");
    }

    /**
     * notify users if target user is not found
     */
    public void tarUserNotFound(){
        bta.setMsgText("target user is not found");
    }

    /**
     * notify users if selected item is the user's own item
     */
    public void selfItem(){
        bta.setMsgText("you can not make trade with yourself");
    }

    /**
     * notify users if the trade is requested
     * @param dur type of the trade
     */
    public void createSuccess(String dur){
        bta.setMsgText("the trade" + dur + "has been created, please wait for another user to confirm");
    }

    /**
     * set input area
     */
    public void updateInputArea(){
        bta.setInput("input","type item name here");
    }

    /**
     * close current frame
     */
    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }




}
