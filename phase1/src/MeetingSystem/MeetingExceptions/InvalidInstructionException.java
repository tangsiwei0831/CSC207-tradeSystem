package MeetingSystem.MeetingExceptions;

import java.io.IOException;

/**
 * an exception for the invalid instruction
 */
public class InvalidInstructionException extends IOException {
    /**
     * the exception for the invalid instruction
     */
    public InvalidInstructionException() {
        System.out.println("Error: Invalid instruction!");
    }
}
