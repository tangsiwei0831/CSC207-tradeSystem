package MeetingSystem.Gateway;

import MeetingSystem.Entity.Meeting;
import MeetingSystem.Entity.MeetingEditor;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.DateTime;
import MeetingSystem.UseCase.GenerateMeeting;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * [GateWay class]
 * Manages the saving and loading of Meetings.
 */
public class ReadWriteMeeting implements IDataAccess {

    private final String meetingDataFile = "phase2/src/MeetingSystem/MeetingData.csv";

    /**
     * A mapping of meeting ids to Meetings.
     */
    private final Map<UUID, Meeting> meetingMap = new HashMap<>();

    // use case
    DateTime dt = new DateTime();
    GenerateMeeting generateMeeting = new GenerateMeeting();

    /**
     * Creates a new empty ReadWriteMeetingManager.
     */
    public ReadWriteMeeting() {
        // Reads String objects from file.
        // Populates the record list using stored data, if it exists.
        File file = new File(meetingDataFile);
        if (file.exists()) {
            readFromCSVFile();
        }

    }

    @Override
    public boolean hasMeeting(UUID meetingID) {
        readFromCSVFile();
        return meetingMap.containsKey(meetingID);
    }

    @Override
    public Meeting searchMeeting(UUID meetingID) {
        readFromCSVFile();
        return meetingMap.get(meetingID);
    }

    /**
     * Populates the records map from the MeetingData.csv file at path meetingDataFile.
     */
    @Override
    public void readFromCSVFile() {
        meetingMap.clear();
        try {
            // FileInputStream can be used for reading raw bytes, like an image.
            Scanner scanner = new Scanner(new FileInputStream(meetingDataFile));

            // skip the header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String[] record = scanner.nextLine().split(",");

                Meeting meeting = convertToMeetingObject(record);
                this.meetingMap.put(meeting.getID(), meeting);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * A helper function of readFromCSVFile(). To convert a meeting string record to a meeting object
     */
    private Meeting convertToMeetingObject(String[] record) {
        // ID
        UUID meetingID = UUID.fromString(record[0]);

        // datetime
        //'2020-12-12T12:12' remove 'T'
        String ymd = record[1].substring(0, 10);
        String hm = record[1].substring(11);
        String dateTimeStr = ymd + " " + hm;
        LocalDateTime dateTime = dt.convertToLocalDateTime(dateTimeStr);

        // place
        String place = record[2];

        // status
        MeetingStatus status = MeetingStatus.valueOf(record[3]);

        // last edit user
        UUID lastEditUser;
        if (record[4].equals("null")) {
            lastEditUser = null;
        } else {
            lastEditUser = UUID.fromString(record[4]);
        }

        // ids of two users
        ArrayList<UUID> userIDs = new ArrayList<>();
        for (String i : record[5].split(";")) {
            userIDs.add(UUID.fromString(i));
        }

        // idToEditTime
        HashMap<UUID, Integer> idToEditTime = new HashMap<>();
        String res = record[6].trim();
        for (String i : res.split(";")) {
            UUID id = UUID.fromString(i.split("=")[0]);
            int editTime = Integer.parseInt(i.split("=")[1]);
            idToEditTime.put(id, editTime);
        }

        // idToAgreedStatus
        HashMap<UUID, Boolean> idToAgreedStatus = new HashMap<>();
        for (String i : record[7].split(";")) {
            UUID id = UUID.fromString(i.split("=")[0]);
            boolean isAgreed = Boolean.parseBoolean(i.split("=")[1]);
            idToAgreedStatus.put(id, isAgreed);
        }

        // idToConfirmedStatus
        HashMap<UUID, Boolean> idToConfirmedStatus = new HashMap<>();
        for (String i : record[8].split(";")) {
            UUID id = UUID.fromString(i.split("=")[0]);
            boolean isConfirmed = Boolean.parseBoolean(i.split("=")[1]);
            idToConfirmedStatus.put(id, isConfirmed);
        }

        // construct meeting object
        return generateMeeting.constructMeeting(meetingID, dateTime, place, status, lastEditUser, userIDs,
                idToEditTime, idToAgreedStatus, idToConfirmedStatus);
    }

    @Override
    public void writeAllMeetingsToCSV() {
        try {
            // create a writer
            FileWriter fw = new FileWriter(meetingDataFile, false);
            BufferedWriter writer = new BufferedWriter(fw);

            // write header record
            writer.write("ID (0), datetime (1), place (2), status (3), lastEditUser (4), idToEditor (5), " +
                    "idToEditTime (6), idToAgreedStatus (7), idToConfirmedStatus (8)");
            writer.newLine();

            // write the records
            List<List<String>> meetings = createAllMeetingsStrRecord();
            for (List<String> meeting : meetings) {
                String writeInContent = String.join(",", meeting);
                writer.write(writeInContent);
                writer.newLine();
            }

            //close the writer
            writer.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void updateFile(Meeting meeting) {
        // read: Map <- CSV
        readFromCSVFile();

        // Action: -> Map
        if (!hasMeeting(meeting.getID())) {
            meetingMap.put(meeting.getID(), meeting);
        }

        // write: Map -> CSV
        writeAllMeetingsToCSV();
    }


    /**
     * A helper method for writeAllMeetingsToCSV()
     */
    private List<List<String>> createAllMeetingsStrRecord() {

        // create a list of meeting records from meetingMap
        List<List<String>> result = new ArrayList<>();
        for (Meeting meeting : meetingMap.values()) {
            result.add(createMeetingStrRecord(meeting));
        }
        return result;
    }


    /**
     * A helper method for writeAllMeetingsToCSV(), or createAllMeetingsStrRecord()
     */
    private List<String> createMeetingStrRecord(Meeting meeting) {
        // create a list of objects

        // last edit user
        String lastEditUserStr;
        if (meeting.getLastEditUser() == null) {
            lastEditUserStr = "null";
        } else {
            lastEditUserStr = meeting.getLastEditUser().toString();
        }

        // construct idToEditTime
        HashMap<UUID, Integer> idToEditTime = new HashMap<>();
        for (HashMap.Entry<UUID, MeetingEditor> entry : meeting.getIdToEditor().entrySet()) {
            UUID userID = entry.getKey();
            MeetingEditor meetingEditor = entry.getValue();
            Integer editionTime = meetingEditor.getTimeOfEdition();
            idToEditTime.put(userID, editionTime);
        }

        // separated by ";": idToEditor
        List<String> list = new ArrayList<>();
        for (HashMap.Entry<UUID, MeetingEditor> entry : meeting.getIdToEditor().entrySet()) {
            list.add(entry.getKey().toString());
        }
        String idToEditor = String.join(";", list);

        // separated by ";": idToEditTimeStr
        list = new ArrayList<>();
        for (HashMap.Entry<UUID, Integer> entry : idToEditTime.entrySet()) {
            list.add(entry.toString());
        }
        String idToEditTimeStr = String.join(";", list);

        // separated by ";": agreedStatuses
        list = new ArrayList<>();
        for (HashMap.Entry<UUID, Boolean> entry : meeting.getAgreedStatusFull().entrySet()) {
            list.add(entry.toString());
        }
        String agreedStatusFullStr = String.join(";", list);

        // separated by ";": confirmedStatuses
        List<String> confirmedStatusesList = new ArrayList<>();

        assert !meeting.getConfirmedStatusFull().entrySet().isEmpty();

        for (HashMap.Entry<UUID, Boolean> entry : meeting.getConfirmedStatusFull().entrySet()) {
            confirmedStatusesList.add(entry.toString());
        }
        String confirmedStatusFullStr = String.join(";", confirmedStatusesList);


        // id, datetime, place, status, lastEditUser, idToEditor, idToAgreedStatus, idToConfirmedStatus
        List<String> record = new ArrayList<>();
        record.add(0, meeting.getID().toString());
        record.add(1, meeting.getDateTime().toString());
        record.add(2, meeting.getPlace());
        record.add(3, meeting.getStatus().toString());
        record.add(4, lastEditUserStr);
        record.add(5, idToEditor);
        record.add(6, idToEditTimeStr);
        record.add(7, agreedStatusFullStr);
        record.add(8, confirmedStatusFullStr);

        return record;
    }

}

