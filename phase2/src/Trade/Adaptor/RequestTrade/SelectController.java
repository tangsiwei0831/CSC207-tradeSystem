package Trade.Adaptor.RequestTrade;

import Inventory.Adaptor.iItemController;
import Inventory.UseCase.Inventory;
import Inventory.Entity.Item;
import Trade.Adaptor.*;
import User.UseCase.UserManager;
import Trade.UseCase.TradeManager;
import javax.swing.*;
import java.util.List;
import java.util.UUID;

/**
 * [controller]
 * controller for select item to request trade session
 */
public class SelectController implements iItemController {
    UUID currUser;
    UserManager um;
    Inventory iv;
    String currItem;
    BorderGUI bta;
    TradeManager tm;
    iSelectPresenter sp;
    List<String> wishList;
    JFrame fr;

    /**
     *
     * @param currUser current user name
     * @param bta view
     * @param fr last frame
     */
    public SelectController(String currUser, BorderGUI bta, JFrame fr){
        this.um = new UserManager();
        this.iv = new Inventory();
        this.bta = bta;
        this.tm = new TradeManager();
        sp = new SelectPresenter(bta);
        this.fr = fr;
        this.currUser = um.nameToUUID(currUser);
    }

    /**
     * update current area
     */
    @Override
    public void updateCurr() {
        sp.notItemSelected();
    }

    /**
     * turn wish borrow list to string
     * @return string
     */
    public String printList(){
        String result = "";
        for (String it: um.getWishBorrow(currUser)){
            result = result + it + "\n";
        }
        return result;
    }

    /**
     * check whether input string is available
     * @param str input string
     * @return boolean
     */
    private boolean checkInput(String str){
        return um.getWishBorrow(currUser).contains(str);
    }


    /**
     * get item info based on the item
     * @param it input item
     * @return string
     */
    public String getItemInfo(Item it){
        return "Item Info:\nitem name: " + iv.getName(it) + "\n" +
                "item description: " + iv.getDescription(it)
                + "\n" + "item owner: " + um.UUIDToName(it.getOwnerUUID()) ;

    }

    /**
     * no use
     * @param name na
     * @return na
     */
    @Override
    public boolean isInList(String name) {
        return false;
    }

    private void enterRTradeGUI(){
        if (currItem == null){
            sp.notItemSelected();
        }else{
            bta.getFrame().setVisible(false);
            BorderGUIBuilder builder = new RTradeGUIBuilder(currUser, currItem, bta.getFrame());
            BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
            engineer.constructGUI();
            GUIPlan tg = engineer.getGUI();
            tg.run();
            sp.closeFrame();
        }


    }

    private List<String> getWishBorrow(){
        wishList = um.getWishBorrow(currUser);
        return wishList;
    }

    /**
     * update presentation of wish borrow list
     */
    public void updateList(){
        sp.updateFrame(getWishBorrow());
    }

    /**
     * action of update button
     */
    @Override
    public void updateBut() {
        updateList();
        updateCurr();

    }

    /**
     * action of entering request trade GUI
     */
    @Override
    public void performActionOne() {
        enterRTradeGUI();
    }

    /**
     * no use
     */
    @Override
    public void performActionTwo() {

    }

    /**
     * no use
     */
    @Override
    public void performActionThree() {

    }


    /**
     * set curr area to current item info
     */
    public void submitBut(){
        String name = bta.getInput("input");
        sp.resetInputArea();
        if (!checkInput(name)){
            sp.wrongInput();
        }else{
            currItem = name;
            sp.selectItemInfo(getItemInfo(iv.getItem(name)));
            sp.updateSuccess();
        }
    }

    /**
     * back to last frame
     */
    public void backBut(){
        fr.setVisible(true);
        sp.closeFrame();
    }
}
