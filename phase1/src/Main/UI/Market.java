package Main.UI;

import Inventory.Inventory;
import Inventory.Item;
import Main.GateWay;
import User.*;
import java.util.Scanner;

/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class Market {

    /**
     * read input
     */
    public Scanner sc;
    /**
     * user in user system
     */
    public ClientUser user;
    /**
     * the object in the Item list of input gateway
     */
    public Inventory iv;

    /**
     * [constructor]
     * @param u the input user
     * @param gw the place we store information
     */
    public Market(ClientUser u, GateWay gw) {
        this.iv = new Inventory(gw);
        user = u;
        sc = new Scanner(System.in);
    }

    /**
     * run the system
     */
    public void run(){
        System.out.println("Hello "+ user.getUsername());
        for(Item item:iv.getAvailableList()){
            System.out.println("Item:"+item);
        }
        System.out.println("--------------------------");
    }
}