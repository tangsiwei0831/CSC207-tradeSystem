package MeetingSystem.Adapter;

import MeetingSystem.UseCase.Model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 * Blueprint for MainViewPresenter
 */
public interface MPresenter {
    void back();

    Model getModel();

    UUID getMeetingID();

    void setMeetingID(UUID meetingID);

    UUID getCurrLogInUser();

    void run();

    List<UUID> getUsers();

    void update(Observable o, Object arg);

    void setObserver(Observer observer);

    Observer getObserver();
}
