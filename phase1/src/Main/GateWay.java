package Main;

import Inventory.Item;
import Trade.Trade;
import User.ClientUser;

import java.util.ArrayList;

public class GateWay {
    private final ArrayList<ClientUser> users;
    private final ArrayList<Trade> trades;
    private final ArrayList<Item> inventory;
    private final ArrayList<ArrayList<String>> ApprovalItem;
    private final ArrayList<ArrayList<String>> ApprovalUser;

    public GateWay(){
        users = new ArrayList<>();
        trades = new ArrayList<Trade>();
        inventory = new ArrayList<>();
        ApprovalItem=new ArrayList<>();
        ApprovalUser=new ArrayList<>();
    }

    public ArrayList<ClientUser> getUsers(){
        return users;
    }

    public ArrayList<Trade> getTrades(){
        return trades;
    }

    public ArrayList<Item> getInv(){
        return inventory;
    }

    public ArrayList<ArrayList<String>> getApprovalItem() {
        return ApprovalItem;
    }

    public ArrayList<ArrayList<String>> getApprovalUser() {
        return ApprovalUser;
    }
}
