package MeetingSystem;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * [Controller class]
 * This is a meeting's main controller (facade controller).
 *
 * The meeting system controller that interacts with the user, makes decisions based on user input instructions, and
 * calls corresponding use case method.
 *
 * Main.Main functions of this controller class:
 *      1. allows setup meeting, once the trade.Trade is set up
 *      2. apply use case method of setting up meeting
 *      3. allows edit meeting, once the
 *      4. allows confirming the meeting
 *      5. update timeOfEdition in MeetingEditor
 */
public class MeetingSystem implements IMeetingSystem {

    private final ArrayList<UUID> users;
    private final boolean isFirst;
    private final ArrayList<MeetingLogInfo> meetingLog = new ArrayList<>();

    MeetingActivities meetingActivities = new MeetingActivities();
    SetUpSession setupSession = new SetUpSession();
    EditAgreeSession editAgreeSession = new EditAgreeSession();
    ConfirmSession confirmSession = new ConfirmSession();

    private LocalDateTime dateTime;
    private String place;
    private Meeting meeting;

    private boolean isSetUp;
    private boolean isCancel;

    private UUID lastEditUser;


    /**
     * Construct a MeetingSystem.MeetingSystem object with two client users (ids)
     *
     * @param users   the users involved in this meeting
     * @param isFirst if the input meeting is the first meeting
     * @param meeting the meeting object
     */
    public MeetingSystem(ArrayList<UUID> users, boolean isFirst, Meeting meeting) {
        this.users = users;
        this.isFirst = isFirst;
        this.meeting = meeting;
        if (meeting != null) {
            dateTime = meeting.getDateTime();
            place = meeting.getPlace();
        }
        isSetUp = false;
        isCancel = false;
    }

    /**
     * Run the Meeting system, which interacts with the user and makes decisions upon meeting progress.
     * <p>
     * 1. allows setting up a meeting, only when there is no meeting stored in trade.Trade (i.e. first meeting)
     * 2. allows editing the meeting / confirming the meeting, only when
     * - the meeting has been set up already;
     * - the meeting has not been cancelled (i.e edit time of each ClientUser < threshold of edition time)
     *
     * @param currLogInUser the user that is currently logging in and use the meeting system
     */
    @Override
    public void run(UUID currLogInUser) throws IOException {
        // first meeting
        if (isFirst) {
            if (meeting == null) {
                setupSession.runSetupSession(currLogInUser, users);
                updateSessionInfo(MeetingSessionName.SETUP, currLogInUser);
            } else if (meeting.getStatus().equals(MeetingStatus.incomplete)) {
                editAgreeSession.runEditAgreeSession(currLogInUser, meeting, meeting.getLastEditUser());
                updateSessionInfo(MeetingSessionName.EDIT_AGREE, currLogInUser);
            } else if (meeting.getStatus().equals(MeetingStatus.agreed)) {
                confirmSession.runConfirmSession(currLogInUser, meeting);
                updateSessionInfo(MeetingSessionName.CONFIRM, currLogInUser);
            }
        } else { // only second (temporary) meeting
            confirmSession.runConfirmSession(currLogInUser, meeting);
            updateSessionInfo(MeetingSessionName.CONFIRM, currLogInUser);
        }

    }

    private void updateSessionInfo(MeetingSessionName sessionName, UUID currLogInUser) {
        if (sessionName.equals(MeetingSessionName.SETUP)) {
            ArrayList<Object> result = setupSession.getSetupSessionResult();
            meeting = (Meeting) result.get(0);
            dateTime = (LocalDateTime) result.get(1);
            place = (String) result.get(2);
            isSetUp = (boolean) result.get(3);
            MeetingLogInfo log = setupSession.getSessionLog();
            if (log != null) {
                meetingLog.add(setupSession.getSessionLog());
            }

            if (isSetUp) {
                this.lastEditUser = currLogInUser;
                meetingActivities.updateLastEditUser(currLogInUser, meeting);
            }


        } else if (sessionName.equals(MeetingSessionName.EDIT_AGREE)) {
            ArrayList<Object> result = editAgreeSession.getEditAgreeSessionResult();
            meeting = (Meeting) result.get(0);
            dateTime = (LocalDateTime) result.get(1);
            place = (String) result.get(2);
            isCancel = (boolean) result.get(3);
            MeetingLogInfo log = editAgreeSession.getSessionLog();
            if (log != null) {
                meetingLog.add(editAgreeSession.getSessionLog());
            }

            boolean isEdited = (boolean) result.get(4);
            if (isEdited && !isCancel) {
                this.lastEditUser = currLogInUser;
                meetingActivities.updateLastEditUser(currLogInUser, meeting);
            }

        } else { // sessionName.equals(MeetingSessionName.CONFIRM)
            meeting = confirmSession.getConfirmSessionResult();
            MeetingLogInfo log = confirmSession.getSessionLog();
            if (log != null) {
                meetingLog.add(confirmSession.getSessionLog());
            }
        }

    }

    /**
     * Returns an arraylist of key results of the current meeting system.
     * The key results contains:
     *      - date-time object
     *      - place string
     *      - status string: "completed", "setUp", "cancel", "incomplete"
     *      - last edit user id
     *
     * @return an arraylist containing date-time object, place string, status string.
     */
    public ArrayList<Object> runResult(){
        // return time, place, status
        ArrayList<Object> result = new ArrayList<>(Arrays.asList(dateTime, place));
        String status;
        if (meeting.getStatus().equals(MeetingStatus.completed)) {
            status = "completed";
        } else if (isSetUp) {
            status = "setUp";
        } else if (isCancel) {
            status = "cancelled";
        } else {
            status = "incomplete";
        }
        result.add(status);

        result.add(this.lastEditUser);

        return result;

    }

    /**
     * Returns the current meeting object.
     * This method is used for updating the meeting stored in the trade.Trade system.
     *
     * @return the meeting object
     */
    public Meeting getMeeting(){
        return meeting;
    }


    /**
     * Sets up the second meeting exactly one month after the first meeting, and returns the second meeting object
     * itself, by given the first meeting object.
     * This is a shortcut method for setting up the second meeting from outside the meeting system (by trade system)
     *
     * @param firstMeeting the first Meeting object
     * @return the second meeting object
     */
    public Meeting setUpSecondMeeting(Meeting firstMeeting){

        dateTime = firstMeeting.getDateTime().plusMonths(1);
        place = firstMeeting.getPlace();

        Meeting m = meetingActivities.setUpMeeting(users, dateTime, place);
        isSetUp = true;
        meeting = m;
        return m;
    }

}