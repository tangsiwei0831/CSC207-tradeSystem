package MeetingSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


/**
 * This is a demo of how to use the MeetingSystem.
 *
 * This takes a Temporary trade.Trade, which generally takes two meetings in total, as an example.
 * Assumes trade.Trade stores:
 *      - tradeIds: an arraylist of two ids, which associating with two users who are part of the trade
 *      - meeting: the first meeting
 *      - meeting2: the second meeting
 * For each meeting, the demo imitates the two users entering meeting system in turns, by making no more than one action
 * (setup meeting, edit meeting, confirm meeting) per entry.
 *
 *
 * Allow each user a max of 3 times of successful edition;
 * cancels the meeting when one of the user edits at its fourth time of edition
 */
public class MeetingSystemDemo {
    // two ClientUsers
    private static final ArrayList<UUID> tradeIds = new ArrayList<>(Arrays.asList(
            UUID.fromString("251abe4f-1ea2-4ef9-a5a5-6f3931e7b375"),
            UUID.fromString("123abe4f-1ea2-4ef9-a5a5-6f3931e7b375")));

    private static Meeting meeting = null;
    private static Meeting meeting2 = null;

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to meeting system!");

        // the start of the first meeting
        MeetingSystem mt1 = new MeetingSystem(tradeIds, true, meeting); // create a meeting system for the first meeting
        boolean turnIsUser1 = true;
        while (meeting == null || meeting.getStatus().equals(MeetingStatus.incomplete) || meeting.getStatus().equals(MeetingStatus.agreed)) {
            // allow the users make actions only when
            // the meeting is not "cancelled" or "completed"

            if (turnIsUser1) {
                System.out.println("===========================");
                System.out.println("user.Login id:" + tradeIds.get(0));

                mt1.run(tradeIds.get(0));

                // new change: check null
                if (meeting != null) {
                    ArrayList<Object> result = mt1.runResult();
                }
                meeting = mt1.getMeeting();

                turnIsUser1 = false;
                System.out.println("===========================");
            } else {
                System.out.println("===========================");
                System.out.println("user.Login id:" + tradeIds.get(1));

                mt1.run(tradeIds.get(1));

                // new change: check null
                if (meeting != null) {
                    ArrayList<Object> result = mt1.runResult();
                }
                meeting = mt1.getMeeting();

                turnIsUser1 = true;
                System.out.println("===========================");
            }

        }
        System.out.println(meeting.toString());
        // here is the end of the first meeting


        if (meeting.getStatus().equals(MeetingStatus.completed)) { // first meeting status: "completed"
            // the start of the second meeting
            System.out.println("Welcome to meeting system!");
            System.out.println("== SECOND MEETING ==");
            MeetingSystem mt2 = new MeetingSystem(tradeIds, false, meeting2); // create a meeting system for the second meeting
            meeting2 = mt2.setUpSecondMeeting(meeting); // trade system sets up the second meeting according to the rule (1 month duration)
            boolean turnIsUser1_m2 = true;
            while (meeting2 == null || meeting2.getStatus().equals(MeetingStatus.incomplete)) {
                // allow the users make actions only when
                // the meeting is not "cancelled" or "completed"

                if (turnIsUser1_m2) {
                    System.out.println("===========================");
                    mt2.run(tradeIds.get(0));
                    mt2.runResult();
                    meeting2 = mt2.getMeeting();

                    turnIsUser1_m2 = false;
                    System.out.println("===========================");
                } else {
                    System.out.println("===========================");
                    mt2.run(tradeIds.get(1));
                    mt2.runResult();
                    meeting2 = mt2.getMeeting();

                    turnIsUser1_m2 = true;
                    System.out.println("===========================");
                }

            } // here is the end of the second meeting
        } else{ // first meeting status: "cancelled"
            System.out.println("Error: trade.Trade cancelled");
        }
    }
}

























        // If ClientUser A sets up the meeting,
        // only when ClientUser B edits/confirms the meeting,
        // ClientUser A could then edits/confirms the meeting.
        // Otherwise, ClientUser A could only sees the meeting time/place that A proposed
        // and proposed meeting not responded info.
//        String input;
//        while (input.equals("quit MS"))
//        if (meeting == null){
//            mt.runSetupSession();
//        }else{
//            mt.runEditConfirmSession();
//        }

        // hardcode two client user's activities
//        mt.runSetupSession(1);
//        mt.runEditConfirmSession(2);
//        mt.runEditConfirmSession(2);
////        mt.runEditConfirmSession(1);
//        mt.runEditConfirmSession(2);
////        mt.runEditConfirmSession(1);
//
//        mt.runEditConfirmSession(2);



//
//
//        //============================================================================
//        // TESTING: MeetingSystem.MeetingSystem.DateTime.java
//        //============================================================================
//        String datetime1 = "2020-10-02 12:00";
//        String datetime2 = "2020-02-02 12:00";
//        String datetime3 = "2020-02-02";
//        String datetime4 = "2020-10-02";
//
//        // print current time
//        LocalDateTime now = MeetingSystem.MeetingSystem.DateTime.getCurrentTime();
//        System.out.println(now);
//
//        // check if of valid format
//        System.out.println(MeetingSystem.MeetingSystem.DateTime.isValidFormat(datetime1));
//        System.out.println(MeetingSystem.MeetingSystem.DateTime.isValidFormat(datetime2));
//        System.out.println(MeetingSystem.MeetingSystem.DateTime.isValidFormat(datetime3));
//        System.out.println(MeetingSystem.MeetingSystem.DateTime.isValidFormat(datetime4));
//
//        // convert string to LocalDateTime;
//        // check if input MeetingSystem.MeetingSystem.DateTime in the future
//        LocalDateTime datetime11 = MeetingSystem.MeetingSystem.DateTime.convertToLocalDateTime(datetime1);
//        System.out.println(datetime11.isAfter(now)); // true
//
//        LocalDateTime datetime22 = MeetingSystem.MeetingSystem.DateTime.convertToLocalDateTime(datetime2);
//        System.out.println(datetime22.isAfter(now)); // false


//        HashMap<Integer, MeetingEditor> idToEditor = new HashMap<Integer, MeetingEditor>();
//        MeetingEditor me1 = new MeetingEditor(1);
//        System.out.println("meeting editor: " + me1);
//        MeetingEditor em = idToEditor.put(1, me1);
//        System.out.println("result: " + em);
//
//        HashMap<Integer, String> idToString = new HashMap<Integer, String>();
//        String e = idToString.put(2, "Hey!");
//        System.out.println("result: " + e);



//    }

