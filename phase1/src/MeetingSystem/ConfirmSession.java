package MeetingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

class ConfirmSession {

    MeetingLogInfo meetingLog;

    MeetingActivities meetingActivities = new MeetingActivities();

    ConfirmSessionPresenter confirmSessionPresenter = new ConfirmSessionPresenter();

    private Meeting meeting;


    void runConfirmSession(UUID currLogInUser, Meeting meeting) throws IOException {
        // pre-set
        this.meeting = meeting;
        meetingLog = null;

        // show session intro
        confirmSessionPresenter.printIntro(meeting.getDateTime(), meeting.getPlace());

        // allow input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        try {
            if (input.equals("cc")) {
                if (meetingActivities.confirmMeeting(meeting, currLogInUser)) {
                    // print successful confirmation
                    confirmSessionPresenter.printSuccessInfo(currLogInUser, meeting);

                    // create log
                    meetingLog = new CreateLogRecord().createLogRecord(currLogInUser, MeetingAction.CONFIRM);
                } else {
                    confirmSessionPresenter.RepeatedConfirmationError();
                }
            } else {
                confirmSessionPresenter.printExit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Meeting getConfirmSessionResult() {
        return meeting;
    }

    MeetingLogInfo getSessionLog() {
        return meetingLog;
    }
}
