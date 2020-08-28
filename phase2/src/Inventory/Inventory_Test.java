package Inventory;

import Inventory.Adaptor.InventoryGUI;
import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Inventory_Test {
    public static void main(String[] args) throws IOException {
        File file = new File("phase2/src/itemList.ser");
        file.delete();
        boolean result = file.createNewFile();
        File file1 = new File("phase2/src/user.ser");
        file1.delete();
        boolean result1 = file.createNewFile();
        JFrame frame = new JFrame();
        ClientUser daniel = new ClientUser("daniel", "123", false);
        ClientUser amy = new ClientUser("amy", "123", false);
        ClientUser admin = new ClientUser("admin", "123", true);
        Item apple = new Item("apple", amy.getId());
        Item pear = new Item("pear", daniel.getId());
        amy.addWishes("apple");
        daniel.addWishes("pear");
        Inventory iv = new Inventory();
        iv.addItem(apple);
        iv.addItem(pear);
        UserManager um = new UserManager();
        um.addUser(daniel);
        um.addUser(amy);
        um.addUser(admin);
        InventoryGUI igAmy = new InventoryGUI("amy", frame);
        igAmy.run();
        InventoryGUI ar = new InventoryGUI("admin", frame);
        ar.run();
    }
}
