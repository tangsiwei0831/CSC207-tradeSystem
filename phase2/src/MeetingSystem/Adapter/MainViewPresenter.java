package MeetingSystem.Adapter;

import MeetingSystem.GUIView.MView;
import MeetingSystem.GUIView.MainView;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.MeetingModel;
import MeetingSystem.UseCase.Model;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

// presenter
public class MainViewPresenter extends Observable implements MPresenter {

    private final UUID currLogInUser;
    private UUID meetingID;
    private final List<UUID> users;
    private final boolean isFirst;

    // Use case
    Model meetingModel;

    // View
    MView mainView; // meeting main view
    JFrame frame; // trade view

    // Observer
    Observer observer;


    /**
     * Construct MainViewPresenter with given info
     * @param meetingID the id of the meeting
     * @param currLogInUser the id of the user
     * @param users the list of userIDs
     * @param isFirst if the meeting is of the first meeting of the trade
     * @param frame the frame in Trade connecting meeting system
     */
    public MainViewPresenter(UUID meetingID, UUID currLogInUser, List<UUID> users, boolean isFirst,
                             JFrame frame) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;
        this.users = users;
        this.isFirst = isFirst;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        mainView = new MainView();
        this.frame = frame;

        // get meeting status
        MeetingStatus meetingStatus = meetingModel.getMeetingStatus(meetingID);

    }

    /**
     * Back to the Trade System View
     */
    @Override
    public void back() {
        // back to Trade System View
        frame.setVisible(true);
    }

    /**
     * Returns the model
     * @return the model
     */
    @Override
    public Model getModel() {
        return meetingModel;
    }

    /**
     * Returns the meeting id
     * @return the meeting id
     */
    @Override
    public UUID getMeetingID() {
        return meetingID;
    }

    /**
     * Set the (first) meeting id in the presenter
     * @param meetingID the meeting id of the meeting
     */
    @Override
    public void setMeetingID(UUID meetingID) {
        this.meetingID = meetingID;
        getModel().setMeetingID(meetingID);

        // notify trade controller - meetingID
        setChanged();
        notifyObservers(meetingID);
    }

    /**
     * Returns current login user's id
     * @return current login user's id
     */
    @Override
    public UUID getCurrLogInUser() {
        return currLogInUser;
    }

    /**
     * Runs the view
     */
    @Override
    public void run() {
        mainView.setPresenter(this);
        mainView.updateViewFromModel(isFirst);
        mainView.open();
    }

    /**
     * Returns the list of users' id
     * @return the list of users' id
     */
    @Override
    public List<UUID> getUsers() {
        return users;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SetupViewPresenter) {
            setMeetingID((UUID) arg);
            mainView.updateViewFromModel(isFirst);
        }
    }

    /**
     * Sets the Observer to this presenter
     * @param observer the object implements Observer
     */
    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    /**
     * Return the Observer
     * @return the Observer
     */
    @Override
    public Observer getObserver() {
        return observer;
    }

}
