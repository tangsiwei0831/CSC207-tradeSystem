package MeetingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


class EditAgreeSession {

    MeetingLogInfo meetingLog;

    MeetingActivities meetingActivities = new MeetingActivities();

    EditAgreeSessionPresenter editAgreeSessionPresenter = new EditAgreeSessionPresenter();


    private Meeting meeting;
    private LocalDateTime dateTime;
    private String place;
    private boolean isCancel = false;

    private boolean edited = false;

    void runEditAgreeSession(UUID currLogInUser, Meeting meeting, UUID lastEditUser) throws IOException {
        // pre-set
        this.meeting = meeting;
        dateTime = meeting.getDateTime();
        place = meeting.getPlace();
        meetingLog = null;
        edited = false;

        // show session intro
        editAgreeSessionPresenter.printIntro(dateTime, place);

        // allow input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        try {
            switch (input) {
                case "ee":
                    if (lastEditUser != null && lastEditUser.equals(currLogInUser)) {
                        editAgreeSessionPresenter.printNoEditionAllowed(dateTime, place);
                        break;
                    }

                    UUID otherUser = meeting.getLastEditUser();
                    boolean otherUserAgreed = this.meeting.getAgreedStatusFull().get(otherUser);
                    if (otherUserAgreed) {
                        editAgreeSessionPresenter.printNoEditionAllowed();
                        break;
                    }

                    if (isEditable(currLogInUser)) { // meeting can be edited

                        EditMeetingInputController editMeeting = new EditMeetingInputController(dateTime, place);
                        LocalDateTime enteredDateTime = (LocalDateTime) editMeeting.editMeetingInputControllerResult().get(0);
                        String enteredPlace = (String) editMeeting.editMeetingInputControllerResult().get(1);
                        boolean backToPrevMenu = (boolean) editMeeting.editMeetingInputControllerResult().get(2);

                        if (backToPrevMenu) {
                            EditAgreeSession editAgreeSession = new EditAgreeSession();
                            editAgreeSession.runEditAgreeSession(currLogInUser, meeting, lastEditUser);
                            ArrayList<Object> result = editAgreeSession.getEditAgreeSessionResult();
                            this.meeting = (Meeting) result.get(0);
                            dateTime = (LocalDateTime) result.get(1);
                            place = (String) result.get(2);
                            isCancel = (boolean) result.get(3);
                            meetingLog = editAgreeSession.getSessionLog();
                            edited = (boolean) result.get(4);
                        } else if (isEdited(enteredDateTime, enteredPlace)) {
                            // update meeting, datetime, place
                            dateTime = enteredDateTime;
                            place = enteredPlace;
                            this.meeting = meetingActivities.editMeeting(this.meeting, currLogInUser, dateTime, place);

                            // update if edited/agreed
                            edited = true;

                            // print successful edition
                            editAgreeSessionPresenter.printSuccessInfo(dateTime, place);

                            // create log
                            meetingLog = new CreateLogRecord().createLogRecord(currLogInUser, MeetingAction.EDIT);

                            // print time of edition of this user
                            editAgreeSessionPresenter.printEditionTime(currLogInUser, meeting);

                        } else {
                            editAgreeSessionPresenter.noEditionRespond();
                        }
                    } else { // not editable -> cancels the meeting
                        boolean cancelled = meetingActivities.cancelMeeting(meeting);
                        assert cancelled;
                        editAgreeSessionPresenter.cancelRespond(meeting);
                        isCancel = true;
                    }
                    break;


                case "aa":
                    if (lastEditUser != null && lastEditUser.equals(currLogInUser)) {
                        editAgreeSessionPresenter.printNoAgreeAllowed(dateTime, place);
                        break;
                    }

                    if (meetingActivities.agreeMeeting(meeting, currLogInUser)) {
                        // update if edited/agreed
                        edited = true;

                        // print successful agreement
                        editAgreeSessionPresenter.printSuccessInfo(currLogInUser, meeting);

                        //create log
                        meetingLog = new CreateLogRecord().createLogRecord(currLogInUser, MeetingAction.AGREE);

                    } else {
                        editAgreeSessionPresenter.RepeatedAgreementError();
                    }
                    break;


                default:
                    editAgreeSessionPresenter.printExit();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isEdited(LocalDateTime enteredDateTime, String enteredPlace) {
        return !enteredDateTime.equals(dateTime) || !enteredPlace.equals(place);
    }


    private boolean isEditable(UUID currLogInUser) {
        // edits <= 3: editable
        MeetingEditor editor = meeting.getEditor(currLogInUser);
        return editor.editable();
    }

    ArrayList<Object> getEditAgreeSessionResult() {
        return new ArrayList<>(Arrays.asList(this.meeting, dateTime, place, isCancel, edited));
    }

    MeetingLogInfo getSessionLog() {
        return meetingLog;
    }
}
