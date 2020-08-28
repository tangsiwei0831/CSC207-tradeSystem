package Inventory.Adaptor;

import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iPresent;

/**
 * present information of the inventory system to user.
 */
public class InventoryPresenter implements iItemPresenter {
    private final iPresent bta;

    /**
     * [constructor]
     */
    InventoryPresenter(BorderGUI bta){
        this.bta = bta;
    }

    /**
     * print the message
     */
    public void wrongInput(){ bta.setMsgText("wrong input");
    }

    /**
     * print the notification
     */
    public void addToWishBorrow(boolean isAdded){
        if (isAdded){
            bta.setMsgText("the item has been moved to the wish list");
        }else{
            bta.setMsgText("you can not add your own item to wish borrow list");
        }
    }

    /**
     * if item requested is already in wish borrow list, notify users
     */
    public void isInWishBorrow(){
       bta.setMsgText("the item has already been in your wish list");
    }

    /**
     * if no item is selected, notify users
     */
    public void noItemSelected(){
        bta.setMsgText("no item has been selected");
    }

    /**
     * set currText to "no item selected"
     */
    public void resetCurr(){
        bta.setCurrText("no item selected");
    }

    /**
     * update currText to info of current item
     * @param itemInfo
     */
    public void updateCurr(String itemInfo){
        bta.setCurrText(itemInfo);
    }

    /**
     * notify users if the item has been deleted
     * @param itemName item name
     */
    public void delSuccess(String itemName){
        bta.setMsgText(itemName + " has been deleted");
    }

    /**
     * hide current frame
     */
    public void closeFrame(){
        bta.getFrame().setVisible(false);
    }

    /**
     * reset input area
     */
    public void resetInputArea(){
        bta.setInput("input","item name");
    }

    /**
     * item name is null when request an item
     */
    public void voidItem(){
        bta.setMsgText("please input item name");
    }

    /**
     * The item name has been used when requesting an item
     */
    public void nameUsed(){
        bta.setMsgText("the item name has already been used");
    }

    /**
     * request an item successfully
     * @param itemName item name
     */
    public void requestSuccess(String itemName){
        bta.setMsgText(itemName + " has been requested, please wait for admin user to agree");
    }

    /**
     * set item description successfully
     * @param itemName item name
     */
    public void editDesSuccess(String itemName){
        bta.setMsgText(itemName + "'s description has been changed");
    }

    /**
     * update the item list
     * @param availableList item list
     */
    public void updateList(String availableList){
        bta.setListText(availableList);
    }
    /**
    "notify admin users if the item has been added to the user's wish list"
     */
    public void addLSuccess(){
        bta.setMsgText("the item has been added to lend list successfully");
    }

    /**
     * notify users if the item name is existed
     */
    public void itemInInv(){
        bta.setMsgText("can't add the item since the item name already exist");
    }

}
