package Inventory;

import User.ClientUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [user.ClientUser Interface-Inventory]
 * show available items on the market.
 * select item.
 * show selected item information.
 * add item to user's wishBorrow list
 */
public class InventoryUI {
    private final InventoryPresenter ip;
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Item currItem;
    private final InventoryController ic;

    /**
     * [constructor]
     * @param currUser the user that is using the system.
     * call inventory presenter and controller
     */
    public InventoryUI(ClientUser currUser, Inventory iv){
        ip = new InventoryPresenter(currUser, iv);
        ic = new InventoryController(currUser, iv);
    }

    /**
     * run the system
     */
    public void run() {
        int exit = 0;
        while (exit !=1){
            while (true){
                ip.printAvailable();
                ip.selectItem();
                try {
                    String line = br.readLine();
                    if (line.equals("1")) {
                        exit = 1;
                        break;
                    }else{
                        if (!ic.selectItem(line)) {
                            ip.wrongInput();
                            throw new IOException();
                        } else{
                            currItem = ic.getItem(line);
                            itemAction();
                            break;
                        }
                    }
                } catch (IOException e) {
                    ip.wrongInput();
                }
            }
        }
    }

    /**
     * let user to select whether to add the item in the wishBorrow list.
     */
    private void itemAction(){
        while (true){
            ip.printItemInfo(currItem);
            ip.itemAction();
            try{
                String line2 = br.readLine();
                if (line2.equals("1")){
                    if (ic.isOwnItem(currItem)){
                        ip.addToWishBorrow(false);
                        break;
                    }
                    //move back to see inventory
                    if (ic.isInOwnWishList(currItem)){
                        ip.isInWishBorrow();
                    }
                    else{
                        ic.moveToWishList(currItem);
                        ip.addToWishBorrow(true);
                    }
                    break;
                }else if (line2.equals("2")){
                    break;
                }else{
                    System.out.println("wrong input, please type again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}





