package Inventory;

import User.ClientUser;

/**
 * present information of the inventory system to user.
 */
public class InventoryPresenter {
    ClientUser currUser;
    Inventory iv;

    /**
     * [constructor]
     * @param currUser the user that is using the system
     */
    InventoryPresenter(ClientUser currUser, Inventory iv){
        this.currUser = currUser;
        this.iv = iv;
    }

    /**
     * print the item that is not in the trade.
     */
    void printAvailable(){
        System.out.println(iv.getAvailableList());
    }

    /**
     * print name, description and owner of the item
     * @param item selected item
     */
    void printItemInfo(Item item) {
        System.out.println("item name: " + item.getName());
        System.out.println("item description: " + item.getDescription());
        System.out.println("item owner: " + item.getOwnerName());
    }

    /**
     * print the message
     */
    void wrongInput(){
        System.out.println("wrong input, please type again");
    }

    /**
     * print the guidance
     */
    void selectItem(){
        System.out.println("type '1' to exit, or select an item");
    }

    /**
     * print the menu.
     */
    void itemAction(){
        System.out.println("menu:\n type '1' to add to wish borrow list and return back to the inventory" +
                "\n type '2' to return back to inventory directly");
    }

    /**
     * print the notification
     */
    void addToWishBorrow(boolean isAdded){
        if (isAdded){
            System.out.println("the item has been moved to the wish list");
        }else{
            System.out.println("you can not add your own item to wish borrow list");
        }
    }

    /**
     * print the message
     */
    void isInWishBorrow(){
        System.out.println("the item has already been in your wish list");
    }



}
