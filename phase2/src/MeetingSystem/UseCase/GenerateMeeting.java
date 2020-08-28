package MeetingSystem.UseCase;

import MeetingSystem.Entity.Meeting;
import MeetingSystem.Entity.MeetingEditor;
import MeetingSystem.MeetingStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * [Use Case class]
 * Generate Meeting Object with given information.
 */
public class GenerateMeeting {

    /**
     * Create a totally new Meeting Object with given information from the parameters
     *
     * @param dateTime the dateTime of this meeting
     * @param place    the place of this meeting
     * @param users    the (two) users joining in this meeting
     * @return the meeting object
     */
    public Meeting createMeeting(LocalDateTime dateTime, String place, ArrayList<UUID> users) {
        Meeting meeting = new Meeting(UUID.randomUUID(), dateTime, place, users);
        meeting.setLastEditUser(null);
        for (UUID user : users) {
            MeetingEditor meetingEditor = constructMeetingEditor(user);
            meeting.setIdToEditor(user, meetingEditor);
        }

        return meeting;
    }

    /**
     * reconstruct a meeting of known meetingID
     *
     * @param uuid                the meetingID of this meeting
     * @param dateTime            the datetime of this meeting
     * @param place               the place of this meeting
     * @param status              the status of this meeting
     * @param lastEditUser        the lastEditUser of this meeting
     * @param users               the users of this meeting
     * @param idToEditTime        the idToEditTime of this meeting
     * @param idToAgreedStatus    the idToAgreedStatus of this meeting
     * @param idToConfirmedStatus the idToConfirmedStatus of this meeting
     * @return the meeting object
     */
    public Meeting constructMeeting(UUID uuid, LocalDateTime dateTime, String place, MeetingStatus status,
                                    UUID lastEditUser, ArrayList<UUID> users, HashMap<UUID, Integer> idToEditTime,
                                    HashMap<UUID, Boolean> idToAgreedStatus,
                                    HashMap<UUID, Boolean> idToConfirmedStatus) {
        Meeting meeting = new Meeting(uuid, dateTime, place, users);
        meeting.setStatus(status);
        meeting.setLastEditUser(lastEditUser);

        for (HashMap.Entry<UUID, Integer> entry : idToEditTime.entrySet()) {
            meeting.getEditor(entry.getKey()).setTimeOfEdition(entry.getValue());
        }

        for (Map.Entry<UUID, Boolean> entry : idToAgreedStatus.entrySet()) {
            if (entry.getValue().equals(true)) {
                meeting.setIdToAgree(entry.getKey());
            }
        }

        for (Map.Entry<UUID, Boolean> entry : idToConfirmedStatus.entrySet()) {
            if (entry.getValue().equals(true)) {
                meeting.setIdToConfirm(entry.getKey());
            }
        }

        return meeting;
    }

    /*
    Construct a new MeetingEditor with zero edition time.
     */
    private MeetingEditor constructMeetingEditor(UUID uuid) {
        MeetingEditor meetingEditor = new MeetingEditor(uuid);
        meetingEditor.setTimeOfEdition(0);
        return meetingEditor;
    }
}
