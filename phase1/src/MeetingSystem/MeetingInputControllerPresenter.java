package MeetingSystem;

import java.time.LocalDateTime;

abstract class MeetingInputControllerPresenter {
    abstract void printDateTimeIntro();

    abstract void printPlaceIntro();

    void printTimeSuccess(LocalDateTime dateTime) {
        System.out.println("proposed date-time is: " + dateTime.toString() + "\n");
    }

    void printPlaceSuccess(String place) {
        System.out.print("Proposed place: " + place + "\n");
    }

    void printInvalidFormatError() {
        System.out.println("Error: Invalid input format!");
    }

    void printInvalidDateTimeError() {
        System.out.println("Error: Invalid input date-time! Only future time accepted");
    }
}
