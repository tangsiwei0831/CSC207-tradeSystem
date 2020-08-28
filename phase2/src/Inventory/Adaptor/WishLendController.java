package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iInput;
import User.Actions.AddWishBorrowUserAction;
import User.Actions.AddWishLendUserAction;
import User.Adapter.ActionController;
import User.Adapter.ClientUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.util.UUID;

/**
 * [controller]
 * controller for wish lend session
 */
public class WishLendController implements iItemController {
    /**
     * the inventory of the system.
     */
    private final Inventory iv;
    /**
     * the user that is using the system.
     */
    private final UUID currUser;

    private final UserManager um;

    private final ApprovalManager iam;

    private final ActionController am;
    private final iInput bta;

    private String it;

    private final iItemPresenter ip;

    private final JFrame fr;

    /**
     * [constructor]
     * @param currUser current user name
     * @param bta view
     * @param fr past frame
     */
    public WishLendController(String currUser, BorderGUI bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        iam = new ApprovalManager();
        am=new ActionController();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }


    /**
     * delete item from wish lend list
     * @param it item name
     */
    public void deleteItem(String it){
        if (iv.deleteItem(it)){
            ClientUser user = um.popUser(currUser);
            user.getWishLend().remove(it);
            um.addUser(user);
        }
    }

    /**
     * set item description
     * @param des description
     * @param itemName item name
     */
    private void setDescription(String des, String itemName){
        Item item = iv.popItem(itemName);
        item.setDescription(des);
        iv.addItem(item);
    }

    /**
     * delete item from lending list
     */
    void delBut(){
        if (it == null){
            ip.noItemSelected();
        }else{
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
     * turn wish lend list info to string
     * @return wish lend info
     */
    @Override
    public String printList() {
        String result = "";
        UserManager um = new UserManager();
        for (String it: um.getUser(currUser).getWishLend()){
            result = result + it + "\n";
        }
        if (result.equals("")){
            return "no item";
        }else{
            return result;
        }
    }

    /**
     * update list area
     */
    public void updateList(){ ip.updateList(printList()); }

    /**
     * add button
     */
    void addBut(){
        String itemName = bta.getInput("name");
        String description = bta.getInput("des");
        if (itemName.equals("")) {
            ip.voidItem();
        }else if(iv.itemExist(itemName)){
            ip.nameUsed();
        }
        else {
            addToList(itemName, description);
            ip.resetCurr();
            ip.requestSuccess(itemName);
        }

    }

    /**
     *  addthe item to approval list
     * @param name
     * @param des
     */
    public void addToList(String name, String des){
        iam.addApprovals(um.getUser(currUser),name,des);
        am.addAction(um.getUsername(currUser),new AddWishLendUserAction(um.getUser(currUser),name));
        iam.AllItemApprovals();
    }

    /**
     * update button
     */
    public void updateBut(){
        updateList();
        updateCurr();
    }

    /**
     * action one: add
     */
    @Override
    public void performActionOne() {
        addBut();

    }

    /**
     * action two: delete
     */
    @Override
    public void performActionTwo() {
        delBut();
    }

    /**
     * action three: edit description
     */
    @Override
    public void performActionThree() {
        editDes();
    }

    /**
     * submit button
     */
    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!um.getWishLend(currUser).contains(input)){
            ip.wrongInput();;
        }else{
            it = input;
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }

    /**
     * back button
     */
    public void backBut(){
        fr.setVisible(true);
        ip.closeFrame();
    }

    public String getItemInfo(Item it) {
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
    }

    @Override
    public boolean isInList(String name) {
        return false;
    }

    /**
     * edit description
     */
    void editDes(){
        if (it == null){
            ip.noItemSelected();
        }else{
            String description = bta.getInput("des");
            setDescription(description, it);
            ip.editDesSuccess(it);
            ip.updateCurr(getItemInfo(iv.getItem(it)));
        }
    }
}
