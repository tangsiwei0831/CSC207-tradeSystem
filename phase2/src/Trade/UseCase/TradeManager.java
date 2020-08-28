package Trade.UseCase;

import Inventory.Entity.Item;
import Inventory.UseCase.Inventory;
import Trade.Entity.OnewayTrade;
import Trade.Entity.Trade;
import Trade.Entity.TwowayTrade;
import User.Entity.ClientUser;
import User.Gateway.DataAccess;
import User.Gateway.UserDataAccess;
import Trade.GateWay.TradeDataAccess;
import Trade.TradeStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This is a use case class trade.TradeManager
 * store Trades in system
 * Allow users to set up trade, cancel trade
 * Automatically update the trade history for both users in the trade.
 */
public class TradeManager{
    DataAccess dataAccess = new TradeDataAccess();
    DataAccess userAccess = new UserDataAccess();

    /**
     * Allow the currentUser to create a one-way trade with input otherUserId, item, and trade duration.
     * Update the trade history for both users
     * @param otherUserId the userId of another user.ClientUser in the particular trade.Trade.
     * @param item the only one Inventory.Entity.Item to be trade in this created trade.Trade.
     * @param duration the duration of this trade.Trade, unit (days). -1 means the trade.Trade is permanent.
     */
    public UUID createOnewayTrade(UUID currUserId, UUID otherUserId, String item, int duration, LocalDateTime time){
        OnewayTrade newTrade = new OnewayTrade(currUserId, otherUserId, item, duration, time);
        Inventory iv = new Inventory();
        Item it = iv.popItem(item);
        it.setIsInTrade(true);
        iv.add(it);
        dataAccess.addObject(newTrade);
        // Record this new trade.Trade in system

        //Update trade history for both users
        //updateTradeHistory(currUserId, otherUserId, newTrade);
        return newTrade.getId();

    }

    public void addTrade(Trade tr){
        dataAccess.addObject(tr);
    }

    /**
     * if the user.ClientUser wants to make a two way trade, tradeManager will create a two way trade.
     * @param otherUserId the userId of another user.ClientUser in the particular trade.Trade
     * @param item1to2 the Inventory.Entity.Item to be trade in this created trade.Trade.
     * @param item2to1 the other Inventory.Entity.Item to be trade in this created trade.Trade.
     * @param duration the duration of this trade.Trade, unit (days). -1 means the trade.Trade is permanent.
     */
    public UUID createTwowayTrade(UUID currUserId, UUID otherUserId, String item1to2, String item2to1, int duration,
                                  LocalDateTime time){
        TwowayTrade newTrade = new TwowayTrade(currUserId, otherUserId, item1to2, item2to1, duration, time);
        dataAccess.addObject(newTrade);
        Inventory iv = new Inventory();
        Item it1 = iv.popItem(item1to2);
        Item it2 = iv.popItem(item2to1);
        it1.setIsInTrade(true);
        it2.setIsInTrade(true);
        iv.add(it1);
        iv.add(it2);
        // Update trade history for both users
        //updateTradeHistory(currUserId, otherUserId, newTrade);
        return newTrade.getId();
    }


    /**
     * get trade with trade id
     * @param id trade id
     * @return
     */
    public Trade getTrade(UUID id) {
        return (Trade) dataAccess.getObject(id);
    }

    /**
     * return the list of all trades that the user has
     */
    public List<Trade> getAllTrade(UUID userID) {
        ClientUser user = (ClientUser) userAccess.getObject(userID);

        ArrayList<Trade> b = new ArrayList<>();
        if (user == null) {
            System.out.println("user is null");
        }else{
            for (UUID i : user.getTradeHistory()) {
                b.add(getTrade(i));
            }
        }
        return b;
    }

    /**
     * return the list of all unconfirmed trades that the user has
     * @return a list of unconfirmed trade
     */
    public List<Trade> getUnconfirmed(UUID userID) {
        ClientUser user = (ClientUser) userAccess.getObject(userID);
        List<Trade> trade = new ArrayList<>();

        for (Trade t : getAllTrade(userID)) {
            if (t.getStatus().equals(TradeStatus.unconfirmed) && t.getCreator() != user.getId()) {
                trade.add(t);
            }
        }
        return trade;
    }


    /**
     * return the list of all incomplete trades that the user has
     */
    public List<Trade> getIncomplete(UUID userID) {
        List<Trade> trade = new ArrayList<>();
        for (Trade t : getAllTrade(userID)) {
            if (t.getStatus().equals(TradeStatus.incomplete)) {
                trade.add(t);
            }
        }
        return trade;
    }

    /**
     * return the list of all complete trades that the user has
     */
    public List<Trade> getComplete(UUID userID) {
        List<Trade> trade = new ArrayList<>();
        for (Trade t : getAllTrade(userID)) {
            if (t.getStatus().equals(TradeStatus.complete)) {
                trade.add(t);
            }
        }
        return trade;
    }

    /**
     * return the list of most recent three trades that the user has
     * if the user has less than three trades, return all the trades the user has
     */
    public List<Trade> getTradeHistoryTop(UUID userID) {

        List<Trade> trade = new ArrayList<>();
        int y = 0;
        if (getAllTrade(userID).size() < 3) {
            trade.addAll(getAllTrade(userID));
            return trade;
        }
        for (int i = getAllTrade(userID).size(); i > 0; i--) {
            if (((!(getAllTrade(userID).get(i).getStatus().equals(TradeStatus.unconfirmed))) &&
                    (!(getAllTrade(userID).get(i).getStatus().equals(TradeStatus.cancelled)))) && y != 3) {
                trade.add(getAllTrade(userID).get(i));
                y++;
            }
        }
        return trade;
    }

    /**
     * return the number of incomplete transactions that the user has
     */
    public int getIncompleteTransaction(UUID userID) {
        ClientUser user = (ClientUser) userAccess.getObject(userID);

        int number = 0;
        for (UUID i : user.getTradeHistory()) {

            if (getTrade(i).getStatus().equals(TradeStatus.incomplete)) {
                number++;
            }
        }
        return number;
    }

    /**
     * return the number of transactions of the user has in seven days from the most recent trade
     */
    public int getTradeNumber(UUID username) {
        ClientUser user = (ClientUser) userAccess.getObject(username);
        if(user.getTradeHistory().size() == 0){return 0;}
        LocalDateTime x  = LocalDateTime.now();
        LocalDateTime y = x.minusDays(7);
        int number = 0;
        for (UUID i : user.getTradeHistory()){
            if((!getTrade(i).getStatus().equals(TradeStatus.unconfirmed)) && getTrade(i).getCreateTime().isAfter(y) && getTrade(i).getCreateTime().isBefore(x)){
                number ++;
            }
        }
        return number;
    }

    /**
     * return a list of transactions of the user has in the most recent seven days
     *
     * @param username the username of the client user that is querying the list of trades in the most recent seven days
     */
    public List<Trade> getWeekTradeList(String username) {
        ClientUser user = (ClientUser) userAccess.getObject(username);

        List<Trade> result = new ArrayList<>();
        LocalDateTime x = LocalDateTime.now();
        LocalDateTime y = x.minusDays(7);
        for (UUID id : user.getTradeHistory()) {
            if (getTrade(id).getCreateTime().isAfter(y) && getTrade(id).getCreateTime().isBefore(x)) {
                result.add(getTrade(id));
            }
        }
        return result;
    }

    /**
     * get trade id
     * @param trade Trade
     * @return trade id
     */
    public UUID getId(Trade trade){
        return trade.getId();
    }

    /**
     * pop trade out from file with tradeId
     * @param tradeId trade id
     * @return trade
     */
    public Trade popTrade(UUID tradeId){
        if (dataAccess.hasObject(tradeId)) {
            Trade result =  (Trade) dataAccess.getObject(tradeId);
            dataAccess.removeObject(tradeId);
            return result;
        } else {
            return null;
        }
    }


}


