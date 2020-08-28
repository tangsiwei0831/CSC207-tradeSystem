package Inventory.Adaptor;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iInput;
import User.Entity.ClientUser;
import User.Entity.ItemApprovals;
import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;

/**
 * [Controller]
 * controllers that are responsible for agree requests
 */
public class AgreeReqController implements iItemController {

    private final Inventory iv;

    private final UserManager um;

    private final ApprovalManager iam;

    private final iInput bta;

    private String it;

    private final iItemPresenter ip;

    private final JFrame fr;

    /**
     * [constructor]
     */
    public AgreeReqController (BorderGUI bta, JFrame fr){
        iv = new Inventory();
        um = new UserManager();
        iam = new ApprovalManager();
        this.bta = bta;
        ip = new InventoryPresenter(bta);
        this.fr = fr;
    }

    /**
     * @return the string of list
     */
    public String printList() {
        String result = iam.AllItemApprovals();
        if (result.equals("")) {
            return "no requested items";
        }
        return result;

    }

    /**
     * get item from request list using item name
     * @param itemName: item name
     * @return item
     */
    private Item getItemByRequestList(String itemName){
        ItemApprovals k=iam.getItemApproval(itemName);
        Item item = iv.createItem(itemName,um.nameToUUID(k.getCurUserName()));
        item.setDescription(k.getSecString());
        return item;
    }

    /**
     * check input
     * @param name the item name
     * @return whether item approvals has the item
     */
    private boolean iamCheckInput(String name){
        return iam.hasItemApprovals(name);
    }

    /**
     * reset curr area
     */
    public void updateCurr(){
        ip.resetCurr();
    }

    /**
     * update list
     */
    public void updateBut(){
        updateList();
        updateCurr();
    }

    /**
     * perform agree action
     */
    @Override
    public void performActionOne() {
        agreeBut();
    }

    /**
     * perform disagree action
     */
    @Override
    public void performActionTwo() {
        disagreeBut();
    }

    /**
     * no use
     */
    @Override
    public void performActionThree() {

    }

    /**
     * update trade list
     */
    public void updateList(){
        ip.updateList(printList());

    }

    /**
     * set currTrade, present trade info
     */
    public void submitBut(){
        String input = bta.getInput("input");
        ip.resetInputArea();
        if (!iamCheckInput(input)){
            ip.wrongInput();
        }else{
            it = input;
            Item item = getItemByRequestList(input);
            ip.updateCurr(getItemInfo(item));
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
     * present item info with the item
     * @param it item name
     * @return item info
     */
    public String getItemInfo(Item it) {
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID());
    }

    /**
     * add the item to lending list(agree request
     * @param it: item
     */
    private void addToList(Item it) {
        if (!isInList(it.getName())){
            iv.add(it);
            ClientUser user = um.popUser(it.getOwnerUUID());
            user.getWishLend().add(it.getName());
            iam.removeItemApproval(it.getName());
            um.addUser(user);

        }else{
            ip.itemInInv();
        }
    }

    /**
     * whether the item is in user's lending list
     * @param name item name
     * @return boolean
     */
    public boolean isInList(String name){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    /**
     * agree with user's request
     */
    void agreeBut(){
        if (it == null){
            ip.noItemSelected();
        }else{
            addToList(getItemByRequestList(it));
            iam.removeItemApproval(it);
            ip.resetCurr();
            ip.addLSuccess();
            it = null;
            updateList();
        }
    }

    /**
     * disagree with user's request, delete the item from the itemApproval
     */
    void disagreeBut(){
        if (it == null) {
            ip.noItemSelected();
        }else{
            iam.removeItemApproval(it);
            ip.resetCurr();
            ip.delSuccess(it);
            it = null;
            updateList();
        }
    }
}
