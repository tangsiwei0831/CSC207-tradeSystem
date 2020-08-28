package Trade.Entity;

import Inventory.Entity.Item;
import Trade.TradeStatus;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * [entity class]
 * abstract class, a trade for users to change items
 */
public abstract class Trade implements Serializable {
    /**
     * first meeting of the trade
     */
    private UUID meeting;
    /**
     * status of the trade
     * unconfirmed: the trade has been requested, but not yet confirmed
     * incomplete: the trade has been confirmed, but not yet completed
     * complete: the trade is complete
     * cancelled: the trade has been cancelled
     */
    protected TradeStatus status;
    /**
     * id of the trade. Each trade has unique id.
     */
    private UUID id;
    /**
     * second meeting for the trade.
     */
    private UUID secondMeeting;
    /**
     * the number of days for borrower to keep the item.
     * -1 means permanent trade.
     */
    private final int duration;
    /**
     * the time the trade is created/requested
     */
    private final LocalDateTime createTime; //the time trade is created

    private UUID creator;


    /**
     * [constructor]
     * @param duration number of date, -1 means permanent, 30 means temporary.
     * @param time create time ot the trade
     */
    public Trade(int duration, LocalDateTime time){
        this.status = TradeStatus.unconfirmed;
        this.duration = duration;
        createTime  = time;
        id = UUID.randomUUID();
    }

    /**
     * return the id of the user
     */
    public UUID getCreator(){
        return creator;
    }

    /**
     * @param currUserID id we want to set
     * set the id equals the input id
     */
    public void setCreator(UUID currUserID){
        creator = currUserID;
    }



    /**
     * getter for createTime
     * @return createTime
     */
    public LocalDateTime getCreateTime(){
        return createTime;
    }


    /**
     * getter for id of the Trade
     * @return id of the Trade
     */
    public UUID getId(){
        return id;
    }

    public void setId(UUID id){this.id = id;}

    /**
     * getter for status
     * @return status
     */
    public TradeStatus getStatus(){
        return status;
    }

    /**
     * setter for status
     * @param newStatus unconfirmed, incomplete, complete or cancelled
     */
    public void setStatus(TradeStatus newStatus){
        status = newStatus;
    }

    /**
     * getter for duration
     * @return whether the trade is temporary or permanent
     */
    public int getDuration(){
        return duration;
    }


    // can input time and place and create new Meeting object based on Meeting constructor
    public void setMeeting(UUID meetingID){
        this.meeting = meetingID;

    }



    public void setSecondMeeting(UUID meetingID){
        this.secondMeeting = meetingID;
    }

    /**
     * getter for second meeting
     * @return second meeting
     */
    public UUID getSecondMeeting(){
        return secondMeeting;
    }

    /**
     * getter for first meeting
     * @return first meeting
     */
    public UUID getMeeting(){
        return meeting;
    }

    /**
     * get a list of users involved in the trade
     * @return a list of users
     */
    public abstract ArrayList<UUID> getUsers();

    /**
     * to string
     * @return trade information in a String format.
     */
    public String toString(){
        return "this is an abstract trade class";
    }

    /**
     * getter for items involved in the trade
     * @return a list of items
     */
    public abstract ArrayList<String> getItemList();

    /**
     * get whether the trade is a onewayTrade or a twowayTrade
     * @return onewayTrade or twowayTrade
     */
    public abstract String getType();



}
