package Trade;

import Inventory.Item;
import Main.GateWay;
import MeetingSystem.MeetingStatus;
import User.ClientUser;
import User.UserManager;

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
public class TradeManager {
    public GateWay gw;

    public TradeManager(GateWay gw){
        this.gw = gw;

    }


    /**
     * Allow the currentUser to create a one-way trade with input otherUserId, item, and trade duration.
     * Update the trade history for both users
     * @param otherUserId the userId of another user.ClientUser in the particular trade.Trade.
     * @param item the only one Inventory.Item to be trade in this created trade.Trade.
     * @param duration the duration of this trade.Trade, unit (days). -1 means the trade.Trade is permanent.
     */
    Trade createOnewayTrade(UUID currUserId, UUID otherUserId, Item item, int duration, LocalDateTime time) {
        OnewayTrade newTrade = new OnewayTrade(currUserId, otherUserId, item, duration, time);
        item.setIsInTrade(true);
        gw.getTrades().add(newTrade);
        // Record this new trade.Trade in system

        //Update trade history for both users
        updateTradeHistory(currUserId, otherUserId, newTrade);
        return newTrade;

    }

    /**
     * if the user.ClientUser wants to make a two way trade, tradeManager will create a two way trade.
     * @param otherUserId the userId of another user.ClientUser in the particular trade.Trade
     * @param item1to2 the Inventory.Item to be trade in this created trade.Trade.
     * @param item2to1 the other Inventory.Item to be trade in this created trade.Trade.
     * @param duration the duration of this trade.Trade, unit (days). -1 means the trade.Trade is permanent.
     */
    Trade createTwowayTrade(UUID currUserId, UUID otherUserId, Item item1to2, Item item2to1, int duration,
                           LocalDateTime time){
        TwowayTrade newTrade = new TwowayTrade(currUserId, otherUserId, item1to2, item2to1, duration, time);
        gw.getTrades().add(newTrade);
        item1to2.setIsInTrade(true);
        item2to1.setIsInTrade(true);
        // Update trade history for both users
        updateTradeHistory(currUserId, otherUserId, newTrade);
        return newTrade;
    }



    void updateTradeHistory(UUID currUserId, UUID tarUserId, Trade newTrade) {
        // System.out.println("userList:"+userManager.getUserList());
        UserManager userManager = new UserManager(gw);
        ClientUser currentUser = userManager.getUser(currUserId);
        ClientUser tarUser = userManager.getUser(tarUserId);
        currentUser.getTradeHistory().add(newTrade.getId());
        tarUser.getTradeHistory().add(newTrade.getId());
    }


    public Trade getTrade(UUID id) {
        for (Trade trade : gw.getTrades()) {
            if (trade.getId().equals(id)) {
                return trade;
            }
        }
        return null;
    }

    /**
     * check the status of the current trade
     * @param currTrade the current trade
     * @return the status
     */
    String checkTradeMeeting(Trade currTrade) {
        if (currTrade.getStatus().equals(TradeStatus.unconfirmed)) {
            return "confirm trade";
        }else if (currTrade.getStatus().equals(TradeStatus.cancelled)) {
            return "cancelled";
        }else if (currTrade.getStatus().equals(TradeStatus.complete)) {
            return "complete";

        }else if (currTrade.getMeeting() == null ||
                currTrade.getMeeting().getStatus().equals(MeetingStatus.incomplete) ||
                currTrade.getMeeting().getStatus().equals(MeetingStatus.agreed)){
            return "first meeting";
        }else if (currTrade.getMeeting().getStatus().equals(MeetingStatus.cancelled)){
            currTrade.setStatus(TradeStatus.cancelled);
            return "cancelled";
        }else if (currTrade.getDuration()==Trade.temp){
            if (currTrade.getSecondMeeting().getStatus().equals(MeetingStatus.incomplete)){
                return "second meeting";
            }else{
                currTrade.setStatus(TradeStatus.complete);
                return "complete";
            }
        }else{
            return "complete";
        }
    }

    /**
     * confirm trade(agree with the trade)
     * @param currTrade current trade
     */
    void confirmTrade(Trade currTrade) {
        currTrade.setStatus(TradeStatus.incomplete);
    }

    /**
     * set the status of trade to complete and make trade
     * @param currTrade current trade
     */
    void completeTrade(Trade currTrade){
        currTrade.setStatus(TradeStatus.complete);
        makeTrade(currTrade);
    }

    /**
     * set the status of trade to cancelled
     * @param currTrade current trade
     */
    void cancelTrade(Trade currTrade){
        currTrade.setStatus(TradeStatus.cancelled);
    }

    void makeTrade(Trade currTrade) {
        UserManager um = new UserManager(gw);
        if (currTrade.getType().equals("oneway")){
            ClientUser bor = um.getUser(currTrade.getUsers().get(0));
            ClientUser lend = um.getUser(currTrade.getUsers().get(1));
            bor.getWishBorrow().remove(currTrade.getItemList().get(0).getName());
            lend.getWishLend().remove(currTrade.getItemList().get(0).getName());
            bor.setBorrowCounter(bor.getBorrowCounter()+1);
            lend.setLendCounter(bor.getLendCounter()+1);

        }else{
            ClientUser u1 = um.getUser(currTrade.getUsers().get(0));
            ClientUser u2 = um.getUser(currTrade.getUsers().get(1));
            u1.getWishBorrow().remove(currTrade.getItemList().get(1).getName());
            u1.getWishLend().remove(currTrade.getItemList().get(0).getName());
            u2.getWishBorrow().remove(currTrade.getItemList().get(0).getName());
            u2.getWishLend().remove(currTrade.getItemList().get(1).getName());
            u1.setBorrowCounter(u1.getBorrowCounter()+1);
            u1.setLendCounter(u1.getLendCounter()+1);
            u2.setBorrowCounter(u2.getBorrowCounter()+1);
            u2.setLendCounter(u2.getLendCounter()+1);
        }

    }

    /**
     * return the list of all trades that the user has
     */
    public List<Trade> getAllTrade(ClientUser user){
        ArrayList<Trade> b = new ArrayList<>();
        for(UUID i: user.getTradeHistory()){
            b.add(getTrade(i));
        }
        return b;
    }

    /**
     * return the list of all unconfirmed trades that the user has
     */
    public List<Trade> getUnconfirmed(ClientUser user) {
        List<Trade> trade=new ArrayList<>();
        for(Trade t: getAllTrade(user)){
            if(t.getStatus().equals(TradeStatus.unconfirmed)){
                trade.add(t);
            }
        }
        return trade;
    }


    /**
     * return the list of all incomplete trades that the user has
     */
    public List<Trade> getIncomplete(ClientUser user){
        List<Trade> trade=new ArrayList<>();
        for(Trade t: getAllTrade(user)){
            if(t.getStatus().equals(TradeStatus.incomplete)){
                trade.add(t);
            }
        }
        return trade;
    }

    /**
     * return the list of most recent three trades that the user has
     * if the user has less than three trades, return all the trades the user has
     */
    public List<Trade> getTradeHistoryTop(ClientUser user) {
        List<Trade> trade=new ArrayList<>();
        int y = 0;
        if(getAllTrade(user).size() < 3){
            trade.addAll(getAllTrade(user));
            return trade;
        }
        for (int i = getAllTrade(user).size(); i>0;i-- ) {
            if (((!(getAllTrade(user).get(i).getStatus().equals(TradeStatus.unconfirmed))) &&
                    (!(getAllTrade(user).get(i).getStatus().equals(TradeStatus.cancelled))))&&y!=3) {
                trade.add(getAllTrade(user).get(i));
                y++;
            }
        }
        return trade;
    }

    /**
     * return the number of incomplete transactions that the user has
     */
    public int getIncompleteTransaction(ClientUser user) {
        int number=0;
        for (UUID i : user.getTradeHistory()) {
            //System.out.println(getTrade(i));
            if (getTrade(i).getStatus().equals(TradeStatus.incomplete)) {
                number++;
            }
        }
        return number;
    }

    /**
     * return the number of transactions of the user has in seven days from the most recent trade
     */
    public int getTradeNumber(ClientUser user) {
        if(user.getTradeHistory().size() == 0){return 0;}
        Trade s = getTrade(user.getTradeHistory().get(user.getTradeHistory().size() - 1));
        LocalDateTime x  = s.getCreateTime();
        LocalDateTime y = x.minusDays(7);
        int number = 1;
        for (UUID i : user.getTradeHistory()){
            if(getTrade(i).getCreateTime().isAfter(y) && getTrade(i).getCreateTime().isBefore(x)){
                number ++;
            }
        }
        return number;
    }








}


