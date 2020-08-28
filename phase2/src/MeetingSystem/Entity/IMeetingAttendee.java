package MeetingSystem.Entity;

/**
 * This is a meeting attendee.
 * <p>
 * Allows at most 3 times of editing
 */
interface IMeetingAttendee {
    /**
     * This IMeetingAttendee's threshold of times to edit place and/or time of meeting; Set to 3 by default.
     */
    int threshold = 3;

    /**
     * Returns the threshold of editing time to the meeting.
     *
     * @return an int of the threshold of editing time
     */
    int getThreshold();
}
