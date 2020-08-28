package MeetingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * [Use Case class]
 * This is a set of meeting activities allowing the actual realisation of setting up meeting, editing meeting, agree on
 * meeting proposal, confirming meeting, and cancelling the meeting
 */

class MeetingActivities {
    /**
     * Sets up a meeting by constructing one meeting, and returning the meeting that is set up
     *
     * @param userIds  the ids of the users who attend this meeting
     * @param dateTime the proposed the date-time to meet
     * @param place    the proposed the place to meet
     * @return the meeting that is newly constructed
     */
    Meeting setUpMeeting(ArrayList<UUID> userIds, LocalDateTime dateTime, String place) {
        return new Meeting(dateTime, place, userIds);
    }

    /**
     * Edits the given meeting object with given date object and place string, and records the editing time of the user
     * with given userId. Cancels the meeting if this user edits over the editing threshold.
     *
     * @param meeting  the meeting to be edited
     * @param userId   the user ID of the user who performs the editing action to the meeting
     * @param dateTime the date-time after this user's edition
     * @param place    the place after this user's edition
     * @return Meeting object after edition
     */
    Meeting editMeeting(Meeting meeting, UUID userId, LocalDateTime dateTime, String place) {
        MeetingEditor editHistory = meeting.getEditor(userId);
        // first update edition time; then check if this time is over the threshold
        editHistory.updateTimeOfEdition();
        if (!editHistory.editsOverThreshold()) {
            meeting.editMeeting(dateTime, place);
        } else {
            updateStatus(meeting);
        }
        return meeting;
    }

    /**
     * Agree the proposal of date and time in the given meeting object by the user with given userId
     *
     * @param meeting the meeting contains current proposal of date and time to agree
     * @param userId  the user id of the user who performs agreement action
     * @return true if agreement action successes; false if the given userId did not register in this meeting or the
     * user has already agreed this meeting before this agreement process.
     */
    boolean agreeMeeting(Meeting meeting, UUID userId) {
        boolean agreed = false;
        HashMap<UUID, Boolean> status = meeting.getAgreedStatusFull();
        if (status.containsKey(userId)) {// if such id exists in this meeting
            // update agreedStatus
            if (!status.get(userId)) { // if idToStatus is false
                status.put(userId, true);
                meeting.setIdToAgree(userId);
                agreed = true;
            }
            //update meeting Status
            updateStatus(meeting);

        }else{ // if such id does NOT exist in this meeting
            System.out.println("Error: mismatch between the input id and id in meeting");
        }
        return agreed;
    }

    /**
     * Performs the confirmation action to the given meeting object by the user with given userId
     *
     * @param meeting the meeting to confirm
     * @param userId  the user id of the user who performs confirmation action
     * @return true if confirmation action successes; false if the given userId did not register in this meeting or the
     * user has already confirmed this meeting before this confirmation process.
     */
    boolean confirmMeeting(Meeting meeting, UUID userId) {
        boolean confirmed = false;
        HashMap<UUID, Boolean> status = meeting.getConfirmedStatusFull();
        if (status.containsKey(userId)) {// if such id exists in this meeting
            // update confirmedStatus
            if (!status.get(userId)) { // if idToStatus is false
                status.put(userId, true);
                meeting.setIdToConfirm(userId);
                confirmed = true;
            }

            //update meeting Status
            updateStatus(meeting);

        }else{ // if such id does NOT exist in this meeting
            System.out.println("Error: mismatch between the input id and id in meeting");
        }
        return confirmed;
    }

    /**
     * Cancels the meeting, and set the meeting status to "cancelled".
     *
     * @param meeting the meeting to be cancelled
     * @return true if the status of meeting is finally be set to "cancelled".
     */
    boolean cancelMeeting(Meeting meeting) {
        meeting.setStatus(MeetingStatus.cancelled);
        return meeting.getStatus().equals(MeetingStatus.cancelled);
    }

    /**
     * Update the status of the Meeting
     *
     * @param meeting the meeting to be updated
     */
    private void updateStatus(Meeting meeting) {
        ArrayList<Boolean> bothTrue = new ArrayList<>(Arrays.asList(true, true));

        if (meeting.getConfirmedStatuses().equals(bothTrue)) {
            meeting.setStatus(MeetingStatus.completed);
        } else if (meeting.getAgreedStatuses().equals(bothTrue)) {
            meeting.setStatus(MeetingStatus.agreed);
        } else if (meeting.isMeetingCancelled()) {
            meeting.setStatus(MeetingStatus.cancelled);
        }
    }

    void updateLastEditUser(UUID currLogInUser, Meeting meeting) {
        meeting.changeLastEditUser(currLogInUser);
    }
}

