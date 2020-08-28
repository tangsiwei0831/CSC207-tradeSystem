package MeetingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

class SetUpSession {

    MeetingLogInfo meetingLog = null;

    MeetingActivities meetingActivities = new MeetingActivities();

    SetUpSessionPresenter setUpSessionPresenter = new SetUpSessionPresenter();

    private Meeting meeting;
    private LocalDateTime dateTime;
    private String place;
    private boolean isSetUp = false;

    /**
     * Run this session only when the meeting is not yet been set up.
     *
     * @param currLogInUser the user id of the ClientUser who sets up the meeting
     * @param users         the ids of the users who attend this meeting
     * @throws IOException unpredicted situation error
     */
    void runSetupSession(UUID currLogInUser, ArrayList<UUID> users) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        setUpSessionPresenter.printIntro(dateTime, place);

        String input = br.readLine();
        try {
            // precondition:
            //  - no meeting has been scheduled yet
            if (input.equals("ok")) {
                SetUpMeetingInputController setUpMeeting = new SetUpMeetingInputController();
                dateTime = (LocalDateTime) setUpMeeting.setUpMeetingInputControllerResult().get(0);
                place = (String) setUpMeeting.setUpMeetingInputControllerResult().get(1);
                meeting = meetingActivities.setUpMeeting(users, dateTime, place);
                isSetUp = true;

                // print successful setting-up
                setUpSessionPresenter.printSuccessInfo(dateTime, place);

                // create log
                meetingLog = new CreateLogRecord().createLogRecord(currLogInUser, MeetingAction.SETUP);
            } else {
                setUpSessionPresenter.printExit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<Object> getSetupSessionResult() {
        return new ArrayList<>(Arrays.asList(meeting, dateTime, place, isSetUp));
    }

    MeetingLogInfo getSessionLog() {
        return meetingLog;
    }
}
