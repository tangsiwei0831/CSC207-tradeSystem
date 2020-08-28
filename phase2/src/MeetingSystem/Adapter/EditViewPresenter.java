package MeetingSystem.Adapter;

import MeetingSystem.Entity.Meeting;
import MeetingSystem.GUIView.EditView;
import MeetingSystem.GUIView.InputTimePlaceView;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class EditViewPresenter extends Observable implements IPresenter {
    private final UUID currLogInUser;
    private final UUID meetingID;

    // Use case
    MeetingManager meetingManager = new MeetingActionManager();
    Model meetingModel;
    DateTime dt = new DateTime();

    // View
    InputTimePlaceView view;


    /**
     * Construct EditViewPresenter with given info
     * @param meetingID the id of the meeting
     * @param currLogInUser the id of the user
     * @param observer the observer to be notified
     */
    public EditViewPresenter(UUID meetingID, UUID currLogInUser, Observer observer) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        view = new EditView();

        // get meeting status
        MeetingStatus meetingStatus = meetingModel.getMeetingStatus(meetingID);

        // set observers
        addObserver(observer);
    }

    @Override
    public void performAction(String inputDateTime, String inputAddress) {
        performEdition(inputDateTime, inputAddress, currLogInUser);
    }

    @Override
    public void performAction() {
        // do nothing
    }

    private void performEdition(String inputDateTime, String inputAddress, UUID currLogInUser) {
        LocalDateTime setDateTime = dt.convertToLocalDateTime(inputDateTime);
        String setPlace = inputAddress.trim();

        assert meetingManager.isMeetingIdExist(meetingID);
        Meeting m = meetingManager.getMeetingWithId(meetingID);

        meetingManager.editMeeting(m, currLogInUser, setDateTime, setPlace);

        // notify observers
        setChanged();
        notifyObservers(meetingID);
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
        return null;
    }

    @Override
    public void run() {
        view.setPresenter(this);
        view.open();
    }
}

