package MeetingSystem.Adapter;

import MeetingSystem.Entity.Meeting;
import MeetingSystem.GUIView.AgreeView;
import MeetingSystem.GUIView.OKCancelView;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.MeetingActionManager;
import MeetingSystem.UseCase.MeetingManager;
import MeetingSystem.UseCase.MeetingModel;
import MeetingSystem.UseCase.Model;

import java.util.List;
import java.util.UUID;

public class AgreeViewPresenter implements IPresenter {

    private final UUID currLogInUser;
    private final UUID meetingID;

    // Use case
    MeetingManager meetingManager = new MeetingActionManager();
    Model meetingModel;

    // View
    OKCancelView view;


    /**
     * Construct AgreeViewPresenter with meetingID and currLoginUserID
     * @param meetingID the meeting id of the meeting
     * @param currLogInUser the id of the current login user
     */
    public AgreeViewPresenter(UUID meetingID, UUID currLogInUser) {

        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        view = new AgreeView();

        // get meeting status
        MeetingStatus meetingStatus = meetingModel.getMeetingStatus(meetingID);

    }

    @Override
    public void performAction(String inputDateTime, String inputAddress) {
        // do nothing
    }

    @Override
    public void performAction() {
        performAgree(currLogInUser);
    }

    private void performAgree(UUID currLogInUser) {

        assert meetingManager.isMeetingIdExist(meetingID);
        Meeting m = meetingManager.getMeetingWithId(meetingID);

        meetingManager.agreeMeeting(m, currLogInUser);
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
