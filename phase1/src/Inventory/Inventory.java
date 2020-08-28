package Inventory;

import java.io.*;
import java.util.ArrayList;
import Main.GateWay;

/**
 * [use case class]
 * the object that edits Item list in gateway
 */
public class Inventory {
    /**
     * the place we store information
     */
    GateWay gw;


    /**
     * [Constructor]
     * @param gw the place we store information
     * get lendingList from file (read file will move to another class).
     */
    public Inventory(GateWay gw){
        this.gw = gw;
    }

    /**
     * getter for the lending list
     * @return lendingList
     */
    public ArrayList<Item> getLendingList() {
        return gw.getInv();
    }

    /**
     * get a list of items that is not in the trade
     * @return available item list
     */
    public ArrayList<Item> getAvailableList() {
        ArrayList<Item> result = new ArrayList<Item>();
        for (Item item : gw.getInv()) {
            if (!item.getIsInTrade()) {
                result.add(item);
            }
        }
        return result;
    }


    /**
     * add the item into the inventory
     * @param item the item added
     */
    public void addItem(Item item){
        gw.getInv().add(item);
    }


    /**
     *
     * @param item the deleted item
     * @throws IOException when the item is not found in the inventory
     */
    public void deleteItem(Item item) throws IOException {
        if (gw.getInv().contains(item)) {
            gw.getInv().remove(item);
        } else {
            throw new IOException("the item is not in the inventory");
        }
    }


    /**
     * get item through its name
     * @param name the item name you want to get
     * @return item
     */
    public Item getItem(String name){
        for (Item item: gw.getInv()){
            if (item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }
}