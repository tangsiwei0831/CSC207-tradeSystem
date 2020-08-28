package Inventory.Adaptor;

public interface iItemPresenter {

    void wrongInput();

    void addToWishBorrow(boolean isAdded);

    void isInWishBorrow();

    void noItemSelected();

    void resetCurr();

    void updateCurr(String itemInfo);

    void delSuccess(String itemName);

    void closeFrame();

    void resetInputArea();

    void voidItem();

    void nameUsed();

    void requestSuccess(String itemName);

    void editDesSuccess(String itemName);

    void updateList(String availableList);

    void addLSuccess();

    void itemInInv();


}
