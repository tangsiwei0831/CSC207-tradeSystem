package MeetingSystem.Adapter;

import MeetingSystem.GUIView.InputTimePlaceView;
import MeetingSystem.GUIView.SetupView;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.*;

import java.time.LocalDateTime;
import java.util.*;


// Supervising controller
public class SetupViewPresenter extends Observable implements IPresenter {
    private final UUID currLogInUser;
    private UUID meetingID;
    private final List<UUID> users;

    // Use case
    MeetingManager meetingManager = new MeetingActionManager();
    Model meetingModel;
    DateTime dt = new DateTime();

    // View
    InputTimePlaceView view;


    /**
     * Construct SetupViewPresenter with given info
     * @param meetingID the id of the meeting
     * @param currLogInUser the id of the user
     * @param users the list of userIds
     * @param observer the observer to be notified
     */
    public SetupViewPresenter(UUID meetingID, UUID currLogInUser, List<UUID> users, Observer observer) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;
        this.users = users;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        view = new SetupView();

        // get meeting status
        MeetingStatus meetingStatus = meetingModel.getMeetingStatus(meetingID);

        // set observers
        addObserver(observer);
    }

    @Override
    public void performAction(String inputDateTime, String inputAddress) {
        this.meetingID = performSetUp(inputDateTime, inputAddress, users, currLogInUser);
    }

    @Override
    public void performAction() {
        // do nothing
    }

    private UUID performSetUp(String inputDateTime, String inputAddress, List<UUID> users, UUID currLogInUser) {
        LocalDateTime setDateTime = dt.convertToLocalDateTime(inputDateTime);
        String setPlace = inputAddress.trim();
        meetingID = meetingManager.setUpMeeting((ArrayList<UUID>) users, setDateTime, setPlace, currLogInUser);

        // notify observers
        setChanged();
        notifyObservers(meetingID);

        return meetingID;
    }

    @Override
    public Model getModel() {
        return meetingModel;
    }

    @Override
    public UUID getMeetingID() {
        return meetingID;
    }

    @Override
    public UUID getCurrLogInUser() {
        return currLogInUser;
    }

    @Override
    public List<UUID> getUsers() {
        return users;
    }

    @Override
    public void run() {
        view.setPresenter(this);
        view.open();
    }

}
