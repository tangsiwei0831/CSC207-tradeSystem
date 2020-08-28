package MeetingSystem;

import java.time.LocalDateTime;
import java.util.UUID;

class EditAgreeSessionPresenter extends MeetingSessionPresenter {

    @Override
    void printIntro(LocalDateTime dateTime, String place) {
        System.out.println("<Edit-Agree-Meeting Session>");
        System.out.println("The current proposed date-time, place info: \n"
                + "   - " + dateTime.toString() + "\n"
                + "   - " + place + "\n");
        System.out.println("Enter \"ee\" to edit, or enter \"aa\" to agree the proposal, " +
                "or anything else to quit this session.");

    }

    @Override
    void printExit() {
        System.out.println("Exit Edit-Agree Session.");
    }


    // for edit session
    void printNoEditionAllowed(LocalDateTime dateTime, String place) {
        System.out.println("The current proposed date-time, place info: \n"
                + "   - " + dateTime.toString() + "\n"
                + "   - " + place + "\n");
        System.out.println("Warn: You are not allowed to edit until the other edit or agree!");
    }

    void printNoEditionAllowed() {
        System.out.println("Warn: You are not allowed to edit!");
    }

    @Override
    void printSuccessInfo(LocalDateTime dateTime, String place) {
        System.out.println("Success: Meeting has been edited!");
        super.printSuccessInfo(dateTime, place);
    }

    void printEditionTime(UUID currLogInUser, Meeting meeting) {
        System.out.println("user.ClientUser " + currLogInUser + " current edit time:" +
                meeting.getEditor(currLogInUser).getTimeOfEdition());
    }

    void cancelRespond(Meeting meeting) {
        System.out.println("Meeting Cancelled!");
        super.printMeetingStatusInfo(meeting);
    }

    void noEditionRespond() {
        System.out.println("Meeting has NOT been edited!");
    }

    // for agree session
    void printNoAgreeAllowed(LocalDateTime dateTime, String place) {
        System.out.println("The current proposed date-time, place info: \n"
                + "   - " + dateTime.toString() + "\n"
                + "   - " + place + "\n");
        System.out.println("Warn: You are not allowed to agree until the other edit or agree!");
    }

    @Override
    void printSuccessInfo(UUID currLogInUser, Meeting meeting) {
        System.out.println("Success: Meeting has been agreed by " + currLogInUser);
        super.printMeetingStatusInfo(meeting);
    }

    void RepeatedAgreementError() {
        System.out.println("Error: Repeated agreement error");
    }

}
