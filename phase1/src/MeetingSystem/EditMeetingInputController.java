package MeetingSystem;

import MeetingSystem.MeetingExceptions.InvalidInstructionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is a input port for edit meeting action, which interacts with the user and obtains the meeting editing
 * information.
 * <p>
 * The user.ClientUser can interact with prompts and choose to
 * - edit time
 * - edit place
 * - edit both time and place
 */
class EditMeetingInputController {

    private LocalDateTime dateTime;
    private String place;

    private boolean backToPreviousMenu = false;

    DateTime dt = new DateTime();

    EditMeetingInputControllerPresenter editMeetingInputControllerPresenter = new EditMeetingInputControllerPresenter();


    /**
     * Obtain user prompts of editing time and/or place.
     */
    EditMeetingInputController(LocalDateTime dateTime, String place) {
        // Set the instance variables "dateTime", "place" with  before editing
        this.dateTime = dateTime;
        this.place = place;

        // Obtain user.ClientUser input of edition info
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean isInputValid = true;
        do {
            try {
                editMeetingInputControllerPresenter.printEditMenu();

                String input = br.readLine();
                // instruction 1: edits time only
                // instruction 2: edits place only
                // instruction 3: edits time and place
                // instruction "..": back to prev menu
                // other instructions: informs invalid and asks input again
                switch (input) {
                    case "1": {
                        editTimeInputController();
                        break;
                    }
                    case "2": {
                        editPlaceInputController();
                        break;
                    }
                    case "3":
                        editTimeInputController();
                        editPlaceInputController();
                        break;
                    case "..": {
                        backToPreviousMenu = true;
                        break;
                    }
                    default:
                        isInputValid = false;
                        throw new InvalidInstructionException();
                }
            } catch (IOException ignored) {

            }
        } while (!isInputValid);
    }


    private void editTimeInputController() {
        Scanner user_input = new Scanner(System.in);
        boolean good = false;
        do {
            editMeetingInputControllerPresenter.printDateTimeIntro();
            String dateTimeStr = user_input.nextLine();
            // change time: must be valid (valid format + in the future); different from the old input

            // check valid format
            if (!dt.isValidFormat(dateTimeStr)) {
                editMeetingInputControllerPresenter.printInvalidFormatError();
            } else {
                // check if time in future
                LocalDateTime now = dt.getCurrentTime();
                LocalDateTime newDateTime = dt.convertToLocalDateTime(dateTimeStr);
                if (!newDateTime.isAfter(now)) {
                    editMeetingInputControllerPresenter.printInvalidDateTimeError();
                } else {
                    // check if different from the old time input
                    if (!newDateTime.isEqual(dateTime)) {
                        this.dateTime = newDateTime;
                        good = true;
                    } else {
                        editMeetingInputControllerPresenter.printDateTimeUnchangedError();

                    }
                }
            }
        } while (!good);
        editMeetingInputControllerPresenter.printTimeSuccess(dateTime);
    }

    private void editPlaceInputController() {
        Scanner user_input = new Scanner(System.in);
        boolean good = false;
        do {
            editMeetingInputControllerPresenter.printPlaceIntro();
            String newPlace = user_input.nextLine();
            if (!newPlace.equals(place)) {
                this.place = newPlace;
                good = true;
            } else {
                editMeetingInputControllerPresenter.printPlaceUnchangedError();
            }
        } while (!good);
        editMeetingInputControllerPresenter.printPlaceSuccess(place);
    }

    /**
     * Returns the results, including time and place, and whether back to previous menu, from this input controller menu
     *
     * @return an arraylist of the date-time, place, if back to previous menu
     */
    ArrayList<Object> editMeetingInputControllerResult() {
        return new ArrayList<>(Arrays.asList(dateTime, place, backToPreviousMenu));
    }

}