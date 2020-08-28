package MeetingSystem;

import java.util.UUID;

/**
 * This is a repository class of creating a log record.
 */
class CreateLogRecord {
    /**
     * create a log record of the user making an action.
     *
     * @param userId the user-id of the user who makes action
     * @param action a String represents the type of action
     * @return a Log
     */
    MeetingLogInfo createLogRecord(UUID userId, MeetingAction action) {
        return new MeetingLogInfo(userId, action);
    }
}

