package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iInput;
import User.Actions.AddWishBorrowUserAction;
import User.Actions.AddWishLendUserAction;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

/**
 * [Controller]
 * controller for adding items to wish borrow list session
 */
public class WishBorrowAddController implements iItemController {
    /**
     * the inventory of the system.
     */
    private final Inventory iv;
    /**
     * the user that is using the system.
     */
    private final UUID currUser;

    UserManager um;

    iInput bta;

    String it;

    iItemPresenter ip;

    JFrame fr;

    /**
     *
     * @param currUser current User name
     * @param bta view
     * @param fr last frame
     */
    public WishBorrowAddController(String currUser, BorderGUI bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }

    /**
     * move current item into wish borrow list
     */
    void moveToWishList(){
        ClientUser user = um.popUser(currUser);
        user.getWishBorrow().add(it);
        um.addUser(user);
        System.out.println(user.getUsername());
        System.out.println(it);
        um.addAction(user.getUsername(),new AddWishBorrowUserAction(user,it));

    }

    /**
     * check whether the item is in wish borrow list
     * @param name item name
     * @return boolean
     */
    public boolean isInList(String name){
        for (String it: um.getWishBorrow(currUser)){
            if (it.equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * reset curr area
     */
    public void updateCurr(){
        ip.resetCurr();
    }


    /**
     * back to last frame
     */
    public void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    /**
     * print item info
     * @param it item name
     * @return item info
     */
    public String getItemInfo(Item it) {
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
    }

    private boolean isOwnItem(){
        return iv.getOwnerUUID(it).equals(currUser);
    }

    private boolean noItemFound(){
        return it == null;
    }

    public void updateBut(){
        updateList();
        updateCurr();
    }

    @Override
    public void performActionOne() {
        addToWishBorrow();
    }

    @Override
    public void performActionTwo() {
    }

    @Override
    public void performActionThree() {
    }


    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!iv.getAvailableList().contains(input)){
            ip.wrongInput();
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    public String printList(){
        String result = "";
        for (String it: iv.getAvailableList()){
            result = result + it + "\n";
        }
        if (result.equals("")){
            return "no available item";
        }
        return result;

    }


    public void updateList(){
        ip.updateList(printList());
    }

    private void addToWishBorrow(){
        if (noItemFound() || isOwnItem()) {
            ip.addToWishBorrow(false);
        } else if (isInList(it)) {
            ip.isInWishBorrow();
        } else {
            moveToWishList();
            ip.updateList(printList());
            ip.addToWishBorrow(true);
        }
    }
}

