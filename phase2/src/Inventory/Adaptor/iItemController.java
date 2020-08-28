package Inventory.Adaptor;

import Inventory.Entity.Item;


public interface iItemController {

    void updateCurr();
    String printList();
    void submitBut();
    void backBut();
    String getItemInfo(Item it);
    boolean isInList(String name);
    void updateList();
    void updateBut();
    void performActionOne();
    void performActionTwo();
    void performActionThree();

}
