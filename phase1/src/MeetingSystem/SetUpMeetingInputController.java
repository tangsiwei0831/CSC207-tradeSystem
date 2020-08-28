package MeetingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Input port of Meeting System, functioned for setting up a meeting only
 */
class SetUpMeetingInputController {
    private LocalDateTime dateTime;
    private final String place;

    DateTime dt = new DateTime();

    SetUpMeetingInputControllerPresenter setUpMeetingInputControllerPresenter =
            new SetUpMeetingInputControllerPresenter();

    /**
     * Feeds the prompts of the Meeting info, including dateTime, place
     */
    SetUpMeetingInputController() {

        Scanner user_input = new Scanner(System.in);
        boolean good = false;
        do {
            setUpMeetingInputControllerPresenter.printDateTimeIntro();
            String dateTimeStr = user_input.nextLine();

            // valid datetime format + in the future than now
            if (dt.isValidFormat(dateTimeStr)) {
                LocalDateTime now = dt.getCurrentTime();
                LocalDateTime datetime = dt.convertToLocalDateTime(dateTimeStr);
                if (datetime.isAfter(now)) {
                    good = true;
                    dateTime = dt.convertToLocalDateTime(dateTimeStr);
                    setUpMeetingInputControllerPresenter.printTimeSuccess(dateTime);
                } else {
                    setUpMeetingInputControllerPresenter.printInvalidDateTimeError();
                }
            } else {
                setUpMeetingInputControllerPresenter.printInvalidFormatError();
            }
        }
        while (!good);


        setUpMeetingInputControllerPresenter.printPlaceIntro();
        place = user_input.nextLine();
        setUpMeetingInputControllerPresenter.printPlaceSuccess(place);


        if (user_input.nextLine().equals("close")) {
            user_input.close();
        }

    }

    ArrayList<Object> setUpMeetingInputControllerResult() {
        return new ArrayList<>(Arrays.asList(dateTime, place));
    }

}
