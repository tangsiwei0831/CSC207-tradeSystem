package MeetingSystem.Adapter;

import MeetingSystem.UseCase.DateTime;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class TimePlaceInputController {

    // use case
    DateTime dt = new DateTime();

    /**
     * Assesses if both input date-time and place are valid
     * @param inputDateTime input date-time String
     * @param inputAddress input place String
     * @return if both input date-time and place are valid
     */
    public boolean assessInput(String inputDateTime, String inputAddress) {
        return assessDateTimeInput(inputDateTime) && assessPlaceInput(inputAddress);
    }

    /**
     * Assesses if input date-time is valid (future time, yyyy-MM-dd hh:mm format)
     * @param inputDateTime input date-time String
     * @return if input date-time is valid
     */
    public boolean assessDateTimeInput(String inputDateTime) {
        if (dt.isValidFormat(inputDateTime)) {
            if (dt.isFutureTime(inputDateTime)) {
                // answer acceptable
                LocalDateTime dateTime_input = dt.convertToLocalDateTime(inputDateTime);
                return true;
            }
        }
        return false;
    }

    /**
     * Assesses if input place is valid (only letters, digits, space and periods allowed)
     * @param inputAddress input place String
     * @return if input place is valid
     */
    public boolean assessPlaceInput(String inputAddress) {
        Pattern pattern = Pattern.compile("^\\w+([ .\\w]*)$");
        String trimmedInput = inputAddress.trim();
        return pattern.matcher(trimmedInput).matches();
    }


}

