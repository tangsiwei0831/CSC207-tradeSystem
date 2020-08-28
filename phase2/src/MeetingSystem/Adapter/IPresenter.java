package MeetingSystem.Adapter;

import MeetingSystem.UseCase.Model;

import java.util.List;
import java.util.UUID;

/**
 * The blueprint of Setup, Edit, Agree, Confirm, Help ViewPresenters
 */
public interface IPresenter {

    /**
     * Performs the action (for setup and edit)
     * @param inputDateTime the user input date-time string
     * @param inputAddress the user input address string
     */
    void performAction(String inputDateTime, String inputAddress);

    /**
     * Performs the action (for agree and confirm)
     */
    void performAction();

    /**
     * Returns the meeting model
     * @return the meeting model
     */
    Model getModel();

    /**
     * Returns the meeting ID
     * @return the meeting ID
     */
    UUID getMeetingID();

    /**
     * Returns the current login user's ID
     * @return the current login user's ID
     */
    UUID getCurrLogInUser();

    /**
     * Returns the list of the userIDs
     * @return the list of the userIDs
     */
    List<UUID> getUsers();

    /**
     * Runs the view
     */
    void run();

}
