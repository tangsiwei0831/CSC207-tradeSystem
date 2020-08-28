package MeetingSystem.Entity;

import java.util.UUID;

/**
 * [Entity class]
 * A MeetingEditor of a meeting who can edit the meeting.
 * <p>
 * This class is used as a record of editing time of each user.ClientUser who attends to a meeting.
 */

public class MeetingEditor implements IMeetingAttendee {

    /**
     * This MeetingEditor's user id.
     */
    private final UUID userId;


    /**
     * This MeetingEditor's times of editing place and/or time of meeting.
     */
    private int timeOfEdition = 0;

    /**
     * Constructs a new MeetingEditor with user id userId.
     *
     * @param userId the user id
     */
    public MeetingEditor(UUID userId) {
        this.userId = userId;
    }

    /**
     * Returns this MeetingEditor's number of time for editing time and/or place of meeting.
     * @return the number of time for edition
     */
    public int getTimeOfEdition() {
        return timeOfEdition;
    }

    /**
     * Set the time of edition
     * @param time the new time of edition
     */
    public void setTimeOfEdition(Integer time){
        timeOfEdition = time;
    }

    /**
     * Update this MeetingEditor's number of time for editing time and/or place of meeting by 1.
     */
    public void updateTimeOfEdition() {
        this.timeOfEdition += 1;
    }

    /**
     * Returns if this MeetingEditor's number of time for editing the meeting has over the threshold.
     *
     * @return true or false
     */
    public boolean editsOverThreshold() {
        return timeOfEdition > getThreshold();
    }

    /**
     * Returns if one more edition is still permitted (not over the edition threshold)
     *
     * @return if one more edition is still permitted
     */
    public boolean editable() {
        return timeOfEdition + 1 <= getThreshold();
    }

    /**
     * Returns a string representation of the timeOfEdition
     *
     * @return the string representation of the timeOfEdition
     */
    @Override
    public String toString() {
        return String.valueOf(timeOfEdition);
    }

    /**
     * Returns the threshold of editing time to the meeting.
     *
     * @return an int of the threshold of editing time
     */
    @Override
    public int getThreshold() {
        return threshold;
    }
}


