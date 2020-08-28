package MeetingSystem.Gateway;

import MeetingSystem.Entity.Meeting;

import java.util.Map;
import java.util.UUID;

public interface IDataAccess {

    void updateFile(Meeting meeting); // write into the csv, read from the csv

    boolean hasMeeting(UUID meetingID); // read from meetingMap

    Meeting searchMeeting(UUID meetingID); // read from meetingMap

    void readFromCSVFile();

    void writeAllMeetingsToCSV();

}
