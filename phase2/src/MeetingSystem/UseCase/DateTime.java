package MeetingSystem.UseCase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * [Use Case class]
 * This class provides basic usage of date-time.
 */

public class DateTime {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    LocalDateTime currentTime = LocalDateTime.now();

    /**
     * Returns the system's current date-time
     *
     * @return system's current date-time
     */
    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Returns the formatter of the date-time
     *
     * @return the formatter
     */
    public DateTimeFormatter getFormat() {
        return formatter;
    }

    /**
     * Returns a String object of current date-time.
     * @return the string of current date-time
     */
    @Override
    public String toString() {
        return "DateTime{" + "currentTime=" + currentTime + '}';
    }


    /**
     * Return if the input date-time string is of Valid Format
     * https://stackoverflow.com/questions/226910/how-to-sanity-check-a-date-in-java
     *
     * @param inputDateTimeString String of input datetime
     * @return true if date-time is of valid format
     */
    public boolean isValidFormat(String inputDateTimeString) throws DateTimeParseException {
        boolean valid = true;
        try {
            formatter.parse(inputDateTimeString);
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * Return if the input date-time string is in the future than the current time
     *
     * @param inputDateTimeString String of input datetime
     * @return true if date-time is in the future
     */
    public boolean isFutureTime(String inputDateTimeString) {
        LocalDateTime now = getCurrentTime();
        LocalDateTime thisDatetime = convertToLocalDateTime(inputDateTimeString);
        return thisDatetime.isAfter(now);
    }

    /**
     * Converts the date-time string to LocalDateTime object
     * precondition: the inputDateTimeString must be of valid format
     *
     * @param inputDateTimeString String of input datetime
     * @return LocalDateTime object
     * https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html#ixzz6PvuyR5EV
     */
    public LocalDateTime convertToLocalDateTime(String inputDateTimeString) {
        return LocalDateTime.parse(inputDateTimeString, formatter);
    }

    /**
     * Converts the LocalDateTime object, localDateTime, to a String
     *
     * @param localDateTime the LocalDateTime object to be converted
     * @return a string of designated format
     */
    public String convertLDTtoString(LocalDateTime localDateTime) {
        return localDateTime.toString().replace("T", " ");
    }

}
