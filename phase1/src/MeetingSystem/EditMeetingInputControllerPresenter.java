package MeetingSystem;

import java.time.LocalDateTime;

class EditMeetingInputControllerPresenter extends MeetingInputControllerPresenter {

    void printEditMenu() {
        System.out.println("------------------------------");
        System.out.print("Menu: \n " +
                "1. Enter '1': only change time \n" +
                "2. Enter '2': only change place \n" +
                "3. Enter '3': change both time and place \n" +
                "4. Enter '..' to quit edition process \n");
        System.out.println("------------------------------");
    }

    // Edit time part
    @Override
    void printDateTimeIntro() {
        System.out.print("Enter the new date-time: (\"yyyy-MM-dd HH:mm\") \n");
    }

    void printDateTimeUnchangedError() {
        System.out.println("Error: Invalid input date-time! The same as the old time");
    }

    @Override
    void printTimeSuccess(LocalDateTime dateTime) {
        System.out.print("New Edition Successful!");
        super.printTimeSuccess(dateTime);
    }

    // Edit place part
    @Override
    void printPlaceIntro() {
        System.out.print("Enter the new place: ");
    }

    void printPlaceUnchangedError() {
        System.out.println("Error: Invalid input place! The same as the old place");
    }

    @Override
    void printPlaceSuccess(String place) {
        System.out.print("New Edition Successful!");
        super.printPlaceSuccess(place);
    }
}
