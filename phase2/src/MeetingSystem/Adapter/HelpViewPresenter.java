package MeetingSystem.Adapter;

import MeetingSystem.GUIView.HelpView;
import MeetingSystem.GUIView.InfoView;
import MeetingSystem.UseCase.*;

import java.util.List;
import java.util.UUID;

public class HelpViewPresenter implements IPresenter{

    // View
    InfoView view;

    /**
     * Construct HelpViewPresenter
     */
    public HelpViewPresenter() {
        // set View
        view = new HelpView();
    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public void run() {
        view.setPresenter(this);
        view.open();
    }

    @Override
    public void performAction(String inputDateTime, String inputAddress) {
        // do nothing
    }

    @Override
    public void performAction() {
        // do nothing
    }

    @Override
    public UUID getMeetingID() {
        // do nothing
        return null;
    }

    @Override
    public UUID getCurrLogInUser() {
        // do nothing
        return null;
    }

    @Override
    public List<UUID> getUsers() {
        // do nothing
        return null;
    }
}
