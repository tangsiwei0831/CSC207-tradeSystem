package MeetingSystem.UseCase;

import MeetingSystem.Entity.Meeting;
import MeetingSystem.MeetingStatus;
import User.UseCase.UserManager;

import java.util.UUID;

/**
 * The display model of meeting
 */
public class MeetingModel implements Model {

    private UUID meetingID;
    private UUID currLogInUser;

    // use case
    MeetingManager meetingActionManager = new MeetingActionManager();
    UserManager userManager = new UserManager();

    /**
     * Construct meeting model with meeting id and current login user id
     * @param meetingID the id of meeting
     * @param currLogInUser the id of current login user
     */
    public MeetingModel(UUID meetingID, UUID currLogInUser) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;
    }

    /**
     * Returns Welcome text with currLoginUser name
     * @return Welcome text with currLoginUser name
     */
    @Override
    public String getCurrUser() {
        return "Welcome to Meeting System! \n" + "User: " + userManager.getUser(currLogInUser).getUsername();
    }

    /**
     * Returns meeting info of given meeting id
     * @param meetingID the id of meeting
     * @return meeting txt info in String
     */
    @Override
    public String getMeetingInfo(UUID meetingID) {
        Meeting m = getMeeting(meetingID);
        DateTime dt = new DateTime();

        if (m == null) {
            return "Meeting ID: " + null + "\n"
                    + "Meeting status: " + MeetingStatus.DNE + "\n"
                    + "Your edited time: " + 0 + "\n"
                    + "DateTime: " + null + "\n"
                    + "Place: " + null + "\n";
        } else {
            return "Meeting ID: " + m.getID() + "\n"
                    + "Meeting status: " + m.getStatus() + "\n"
                    + "Your edited time: " + m.getEditor(currLogInUser).getTimeOfEdition() + "\n"
                    + "DateTime: " + dt.convertLDTtoString(m.getDateTime()) + "\n"
                    + "Place: " + m.getPlace() + "\n";
        }

    }

    /**
     * Return Meeting object with given meeting ID
     * @param meetingID the id of the meeting
     * @return the meeting object
     */
    @Override
    public Meeting getMeeting(UUID meetingID) {
        if (meetingActionManager.isMeetingIdExist(meetingID)) {
            return meetingActionManager.getMeetingWithId(meetingID);
        } else {
            return null;
        }
    }

    /**
     * Returns if both input of date-time and place for given meeting (ID) changed
     * @param meetingID the id of meeting
     * @param inputTime input date-time in String
     * @param inputPlace input place in String
     * @return if both inputs changed
     */
    @Override
    public boolean isTimePlaceChanged(UUID meetingID, String inputTime, String inputPlace) {
        DateTime dt = new DateTime();
        Meeting m = getMeeting(meetingID);
        if (m == null) {
            return false;
        } else {
            return !(m.getDateTime().equals(dt.convertToLocalDateTime(inputTime)) && m.getPlace().equals(inputPlace));
        }
    }

    /**
     * Returns if the currLoginUser can still edit the meeting of given meeting ID
     * @param meetingID the id of meeting
     * @param currLogInUser the current login user id
     * @return if the currLoginUser can still edit the meeting
     */
    @Override
    public boolean isEditable(UUID meetingID, UUID currLogInUser) {
        Meeting m = getMeeting(meetingID);
        return m.getEditor(currLogInUser).editable();
    }

    /**
     * Return the meeting status
     * @param meetingID the id of meeting
     * @return the meeting status
     */
    @Override
    public MeetingStatus getMeetingStatus(UUID meetingID) {
        Meeting m = getMeeting(meetingID);
        MeetingStatus meetingStatus;

        if (m == null) {
            meetingStatus = MeetingStatus.DNE;
        } else {
            meetingStatus = m.getStatus();
        }

        return meetingStatus;
    }

    /**
     * Return if the other user agreed the meeting
     * @param meetingID the id of meeting
     * @return if the other user agreed the meeting
     */
    @Override
    public boolean otherUserAgreed(UUID meetingID) {
        UUID otherUser = getMeeting(meetingID).getLastEditUser();
        return getMeeting(meetingID).getAgreedStatusFull().get(otherUser);
    }

    /**
     * Returns if the last edit user is the curr login user
     * @return if the last edit user is the curr login user
     */
    @Override
    public boolean isLastUserCurrUser() {
        if (getMeeting(meetingID) == null) {
            return false;
        } else if (getMeeting(meetingID).getLastEditUser() == null) {
            return false;
        }else {
            return getMeeting(meetingID).getLastEditUser().equals(currLogInUser);
        }
    }

    /**
     * Set meeting model's meeting ID
     * @param meetingID the id of meeting
     */
    @Override
    public void setMeetingID(UUID meetingID) {
        this.meetingID = meetingID;
    }


}
