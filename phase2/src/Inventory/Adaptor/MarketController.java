package Inventory.Adaptor;

import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iInput;
import User.UseCase.UserManager;
import Inventory.Entity.Item;
import javax.swing.*;

/**
 * [Controller]
 * Controller for market system
 */
public class MarketController implements iItemController {
    Inventory iv;
    iInput bta;
    JFrame fr;
    String it;
    iMarketPresenter ip;

    /**
     *
     * @param bta BorderGUI
     * @param fr
     */
    public MarketController(BorderGUI bta, JFrame fr){
        this.iv = new Inventory();
        this.bta = bta;
        ip = new MarketPresenter(bta);
        this.fr = fr;
    }


    /**
     * print available items
     * @return string that contains info about available items
     */
    public String printList(){
        StringBuilder result = new StringBuilder();
        for (String it: iv.getAvailableList()){
            result.append(it).append("\n");
        }
        if (result.toString().equals("")){
            return "no available item";
        }
        return result.toString();

    }


    /**
     * print item info from item
     * @param it item
     * @return item info
     */
    public String getItemInfo(Item it) {
        UserManager um = new UserManager();
        if (iv.getName(it) == null) {
            return "No Item Selected";
        } else {
            return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                    "item description: " + iv.getDescription(it)
                    + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
        }
    }

    /**
     * change currItem with inputs
     */
    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        System.out.println("market controller" + iv.getAvailableList() + iv.getItem(input));
        System.out.println(iv.getAvailableList().contains(input));
        if (!iv.getAvailableList().contains(input)){
            ip.wrongInput();
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    /**
     * update available list
     */
    public void updateList(){
        ip.updateListM(printList());
    }

    /**
     * update button
     */
    @Override
    public void updateBut() {
        updateList();
        updateCurr();
    }

    /**
     * back to last frame
     */
    public void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    /**
     * test whether the item is in the available list
     * @param name: item name
     * @return boolean
     */
    @Override
    public boolean isInList(String name){
        return false;
    }

    /**
     * reset curr area
     */
    public void updateCurr(){
        ip.resetCurr();
    }

    @Override
    public void performActionOne() { }

    @Override
    public void performActionTwo() { }

    @Override
    public void performActionThree() {
    }



}
