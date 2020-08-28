package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.*;
import User.Actions.AddWishBorrowUserAction;
import User.Adapter.ActionController;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

/**
 * [Controller]
 * controller for wish borrow session
 */
public class WishBorrowController implements iItemController {

    private final Inventory iv;
    private final UUID currUser;

    ActionController ac=new ActionController();
    UserManager um;

    iInput bta;

    String it;

    iItemPresenter ip;

    JFrame fr;

    /**
     *
     * @param currUser current user name
     * @param bta borderGUI
     * @param fr last frame
     */
    public WishBorrowController(String currUser, BorderGUI bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }


    /**
     * delete item from wish borrow list
     * @param it the item name that wanted to be deleted
     */
    public void deleteItem(String it){
        ClientUser user = um.popUser(currUser);
        user.getWishBorrow().remove(it);
        um.addUser(user);
        ac.removeAction(um.getUsername(currUser),new AddWishBorrowUserAction(um.getUser(currUser),it));
    }

    /**
     * delete button
     */
    public void delBut() {
        if (it == null){
            ip.noItemSelected();
        }else {
            deleteItem(it);
            ip.noItemSelected();
            ip.delSuccess(it);
            ip.resetCurr();
            it = null;
            updateBut();
        }
    }

    /**
     * reset curr area
     */
    public void updateCurr(){
        ip.resetCurr();
    }

    /**
     * update item list
     */
    public void updateList(){
        ip.updateList(printList());
    }

    /**
     * change list to string
     * @return string
     */
    public String printList(){
        StringBuilder result = new StringBuilder();
        for (String it: um.getUser(currUser).getWishBorrow()){
            result.append(it).append("\n");
        }
        if (result.toString().equals("")){
            return "no available item";
        }
        return result.toString();
    }


    /**
     * action of add button
     */
    void addBut(){
        it = null;
        updateBut();
        ip.closeFrame();
        BorderGUIBuilder builder = new WishBorrowAddBuilder(um.UUIDToName(currUser), bta.getFrame());
        BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
        engineer.constructGUI();
        GUIPlan tg = engineer.getGUI();
        tg.run();
    }

    /**
     * action for submit button
     */
    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishBorrow(currUser).contains(input)){
            ip.wrongInput();;
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    /**
     * return to last frame
     */
    public void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    /**
     * get item info
     * @param it: Item
     * @return item info
     */
    public String getItemInfo(Item it) {
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
    }

    /**
     * check whether the item is in the wish borrow list
     * @param name item name
     * @return boolean
     */
    @Override
    public boolean isInList(String name) {
        return false;
    }

    /**
     * action for update button
     * reset list info and curr
     */
    public void updateBut(){
        updateList();
        updateCurr();
    }

    /**
     * add the item
     */
    @Override
    public void performActionOne() {
        addBut();

    }

    /**
     * delete the item
     */
    @Override
    public void performActionTwo() {
        delBut();
    }

    @Override
    public void performActionThree() {

    }


}
