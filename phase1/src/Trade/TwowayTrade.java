package Trade;

import Inventory.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class TwowayTrade extends Trade {
    private final UUID trader1Id;
    private final UUID trader2Id;
    private final Item item1to2;
    private final Item item2to1;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     *
     * @param trader1Id Trader who participates in the trade
     * @param trader2Id Trader who participates in the trade
     * @param item1 the item that trader1 give to trader2
     * @param item2 the item that trader2 give to trader1
     * @param duration whether the trade is temporary or permanent
     */
    public TwowayTrade(UUID trader1Id, UUID trader2Id, Item item1, Item item2, int duration, LocalDateTime time) {
        super(duration, time);
        this.trader1Id = trader1Id;
        this.trader2Id = trader2Id;
        item1to2 = item1;
        item2to1 = item2;
    }

    /**
     * getter for a list of users involved in the trade
     * @return a list of users
     */
    public ArrayList<UUID> getUsers(){
        ArrayList<UUID> users = new ArrayList<>();
        users.add(trader1Id);
        users.add(trader2Id);
        return users;
    }

    /**
     * to string
     * @return trade information in a string format
     */
    public String toString(){
        return "trade id: " + getId() + "\ntrade type: " + getType()+ "\ntrader: " + getUsers() + "\nitems: "
                + getItemList() + "\ncreate time: " + getCreateTime().format(formatter);
    }

    /**
     * get a list of items involved in the trade
     * @return a list of items
     */
    @Override
    public ArrayList<Item> getItemList() {
        ArrayList<Item> rst = new ArrayList<>();
        rst.add(item1to2);
        rst.add(item2to1);
        return rst;
    }

    /**
     * return the string shows the type of the trade
     */
    public String getType() {
        return "twoway";
    }


}