package MeetingSystem.UseCase;

import MeetingSystem.Entity.Meeting;
import MeetingSystem.MeetingStatus;

import java.util.UUID;

/**
 * The display model of meeting
 */
public interface Model {
    String getMeetingInfo(UUID meetingID);

    Meeting getMeeting(UUID meetingID);

    boolean isTimePlaceChanged(UUID meetingID, String inputTime, String inputPlace); // return if input had difference to the record

    boolean isEditable(UUID meetingID, UUID currLogInUser);

    MeetingStatus getMeetingStatus(UUID meetingID);

    boolean otherUserAgreed(UUID meetingID);

    boolean isLastUserCurrUser();

    String getCurrUser();

    void setMeetingID(UUID meetingID);
}
