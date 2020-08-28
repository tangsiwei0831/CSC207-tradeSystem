package MeetingSystem;

import java.time.LocalDateTime;
import java.util.UUID;

class ConfirmSessionPresenter extends MeetingSessionPresenter {
    @Override
    void printIntro(LocalDateTime dateTime, String place) {
        System.out.println("<Confirm-Meeting Session> \n");
        System.out.println("The current proposed date-time, place, status info: \n"
                + "   - " + dateTime.toString() + "\n"
                + "   - " + place + "\n");

        System.out.println("Enter \"cc\" to confirm the meeting has been taken place, " +
                "or anything else to quit confirm-meeting session.");
    }

    @Override
    void printExit() {
        System.out.println("Exit Confirm Session.");
    }

    @Override
    void printSuccessInfo(UUID currLogInUser, Meeting meeting) {
        System.out.println("Success: Meeting has been confirmed by " + currLogInUser);
        super.printMeetingStatusInfo(meeting);
    }

    void RepeatedConfirmationError() {
        System.out.println("Error: Repeated confirmation error");
    }
}
