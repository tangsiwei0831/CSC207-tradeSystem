package User;

import Inventory.Item;
import Main.GateWay;

import java.util.ArrayList;

public class ItemApprovalManager {
    private GateWay gw;

    public ItemApprovalManager(GateWay gw){
        this.gw = gw;
    }

    public ArrayList<ArrayList<String>> getItemApproval(){
        return gw.getApprovalItem();
    }

    public ArrayList<ArrayList<String>> getUserApproval(){
        return gw.getApprovalUser();
    }
}
