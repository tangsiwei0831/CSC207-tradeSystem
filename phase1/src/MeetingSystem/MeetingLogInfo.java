package MeetingSystem;

import java.util.UUID;

/**
 * A Record of an action of the Client user.ClientUser who attends this meeting.
 * <p>
 * Log Action Types:
 * + SETUP
 * + EDIT
 * + AGREE
 * + CONFIRM
 */
class MeetingLogInfo {

    UUID userId;
    MeetingAction action;

    MeetingLogInfo(UUID userId, MeetingAction action) {
        this.userId = userId;
        this.action = action;
    }

    /**
     * Returns the string representation of MeetingLogInfo.
     * @return the string representation of MeetingLogInfo
     */
    @Override
    public String toString() {
        return "MeetingLogInfo{" +
                "userId=" + userId +
                ", action='" + action + '\'' +
                '}';
    }
}