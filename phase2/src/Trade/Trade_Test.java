package Trade;

import Inventory.UseCase.Inventory;
import Inventory.Entity.Item;
import Trade.Adaptor.TradeGUI_Main;
import Trade.UseCase.TradeManager;
import User.Entity.ClientUser;
import User.GUI.LoginGUI;
import User.UseCase.UserManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Trade_Test {
    public static void main(String[] args) throws IOException {
        File file = new File("phase2/src/itemList.ser");
        file.delete();
        boolean result = file.createNewFile();
        File file1 = new File("phase2/src/user.ser");
        file1.delete();
        boolean result1 = file.createNewFile();
        File file2 = new File("phase2/src/trade.ser");
        file.delete();
        boolean result2 = file.createNewFile();
        ClientUser daniel = new ClientUser("daniel", "123", false);
        ClientUser amy = new ClientUser("amy", "123", false);
        ClientUser admin = new ClientUser("admin", "123", true);
        Item apple = new Item("apple", amy.getId());
        Item pear = new Item("pear", daniel.getId());
        amy.addWishBorrow("pear");
        daniel.addWishes("pear");
        daniel.addWishBorrow("apple");
        amy.addWishes("apple");
        Inventory iv = new Inventory();
        UserManager um = new UserManager();
        iv.addItem(apple);
        iv.addItem(pear);
        um.addUser(admin);
        um.addUser(daniel);
        um.addUser(amy);
        JFrame f = new JFrame();

        TradeGUI_Main trmD = new TradeGUI_Main("daniel", f);
        trmD.run();
        TradeGUI_Main trmA = new TradeGUI_Main("amy", f);
        trmA.run();
        LoginGUI user = new LoginGUI();
        user.run();



    }
}
