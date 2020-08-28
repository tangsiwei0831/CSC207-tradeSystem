package Trade.Entity;

import Inventory.Entity.Item;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class OnewayTrade extends Trade implements Serializable {
    private final UUID lenderId;
    private final UUID borrowerId;
    private final String item;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * @param lender Trader who wants to lend item to others
     * @param borrower Trader who wants to borrow item from others
     * @param item the two item traders what to trade
     * @param duration whether the trade is temporary or permanent
     */
    public OnewayTrade(UUID borrower, UUID lender, String item, int duration, LocalDateTime time) {
        super(duration, time);
        borrowerId = borrower;
        lenderId = lender;
        this.item = item;
    }



    /**
     * getter for item of the trade
     * @return item
     */
    public String getItem() {
        return item;
    }

    /**
     * get users involved in the trade
     * @return a list of users
     */
    public ArrayList<UUID> getUsers(){
        ArrayList<UUID> users = new ArrayList<>();
        users.add(borrowerId);
        users.add(lenderId);
        return users;
    }


    /**
     * extends the to string method of the trade
     */
    public String toString(){
        return "Trade type: " + getType()  + "\nDuration: "+ getDuration()+ "\n" +
                "Status: " + getStatus().toString() + "\nItems: "
                + getItemList() + "\nCreate time: " + getCreateTime().format(formatter)+"\n";
    }

    /**
     * get item involved in the trade
     * @return item
     */
    public ArrayList<String> getItemList(){
        ArrayList<String> rst = new ArrayList<String>();
        rst.add(item);
        return rst;
    }


    /**
     * @return oneway
     */
    @Override
    public String getType() {
        return "one way";
    }


}

