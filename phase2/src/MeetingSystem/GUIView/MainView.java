package MeetingSystem.GUIView;

import MeetingSystem.Adapter.*;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.Model;

import javax.swing.*;
import java.util.UUID;

/**
 * The Main Menu view for the meeting system
 */
public class MainView implements MView {
    private JFrame frame;
    private JPanel panel1;
    private JTextArea welcomeTextArea;
    private JTextArea meetingInfoTextArea;
    private JButton backButton;
    private JButton setUpButton;
    private JButton editButton;
    private JButton agreeButton;
    private JButton confirmButton;
    private JButton helpButton;


    private boolean isFirst; // is first meeting

    // Presenter
    private MPresenter presenter;

    /**
     * Constructs MainView
     */
    public MainView() {
        initComponents();
    }

    /**
     * Updates the view regarding to the model
     *
     * @param isFirst if the meeting is the first meeting (for temporary trade)
     */
    @Override
    public void updateViewFromModel(boolean isFirst) {
        this.isFirst = isFirst;
        Model model = getPresenter().getModel();
        UUID meetingID = getPresenter().getMeetingID();
        MeetingStatus meetingStatus = model.getMeetingStatus(meetingID);

        // update welcome view, meeting info view
        welcomeTextArea.setText(model.getCurrUser());
        meetingInfoTextArea.setText(model.getMeetingInfo(meetingID));


        if (isFirst) { // first meeting view

            // update Button view
            if (meetingStatus.equals(MeetingStatus.DNE)) {
                // set up session
                setUpButton.setVisible(true);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(false);
            } else if (meetingStatus.equals(MeetingStatus.INCOMPLETE)) {
                setUpButton.setVisible(false);
                editButton.setVisible(!model.otherUserAgreed(meetingID) && !model.isLastUserCurrUser());
                agreeButton.setVisible(!model.isLastUserCurrUser());
                confirmButton.setVisible(false);
            } else if (meetingStatus.equals(MeetingStatus.AGREED)) {
                welcomeTextArea.setText("Meeting Agreed by both users!");
                setUpButton.setVisible(false);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(!model.isLastUserCurrUser());
            } else {
                // cancelled or completed
                setUpButton.setVisible(false);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(false);
            }

            if (model.isLastUserCurrUser()) {
                setUpButton.setVisible(false);
                editButton.setVisible(false);
                agreeButton.setVisible(false);
                confirmButton.setVisible(false);
                welcomeTextArea.setText("Please wait the other user to edit/agree/confirm!");
            }

            if (meetingStatus.equals(MeetingStatus.CANCELLED)) {
                welcomeTextArea.setText("Meeting Cancelled!");
            } else if (meetingStatus.equals(MeetingStatus.COMPLETED)) {
                welcomeTextArea.setText("Meeting Confirmed by both users!");
            }
        } else { // second meeting view

            confirmButton.setVisible(true);
            setUpButton.setVisible(false);
            editButton.setVisible(false);
            agreeButton.setVisible(false);

            if (model.isLastUserCurrUser()) {
                confirmButton.setVisible(false);
                welcomeTextArea.setText("Please wait the other user to confirm!");
            }

            if (meetingStatus.equals(MeetingStatus.COMPLETED)) {
                confirmButton.setVisible(false);
                welcomeTextArea.setText("Meeting Confirmed by both users!");
            }
        }


    }

    /**
     * Opens the view
     */
    @Override
    public void open() {
        frame.setVisible(true);
    }

    /**
     * Gets the presenter of this view
     *
     * @return the presenter
     */
    protected MPresenter getPresenter() {
        return presenter;
    }

    /**
     * Sets the presenter to the view
     *
     * @param presenter the presenter
     */
    @Override
    public void setPresenter(MPresenter presenter) {
        this.presenter = presenter;
    }

    private void initComponents() {
        frame = new JFrame("Meeting System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.getContentPane().add(panel1);
        frame.setVisible(true);

        backButton.addActionListener(e -> {
            // back to Trade System
            frame.setVisible(false);
            backActionPerformed(e);
        });
        setUpButton.addActionListener(e -> {
            // go to setup view
            SetupViewPresenter setupViewPresenter =
                    new SetupViewPresenter(getPresenter().getMeetingID(), getPresenter().getCurrLogInUser(),
                            getPresenter().getUsers(), getPresenter().getObserver());
            setupViewPresenter.run();
            getPresenter().setMeetingID(setupViewPresenter.getMeetingID());
            updateViewFromModel(isFirst);
        });
        editButton.addActionListener(e -> {
            // go to edit view
            EditViewPresenter editViewPresenter =
                    new EditViewPresenter(getPresenter().getMeetingID(), getPresenter().getCurrLogInUser(),
                            getPresenter().getObserver());
            editViewPresenter.run();
            updateViewFromModel(isFirst);
        });
        agreeButton.addActionListener(e -> {
            // go to agree view
            AgreeViewPresenter agreeViewPresenter =
                    new AgreeViewPresenter(getPresenter().getMeetingID(), getPresenter().getCurrLogInUser());
            agreeViewPresenter.run();
            updateViewFromModel(isFirst);
        });
        confirmButton.addActionListener(e -> {
            // go to confirm view
            ConfirmViewPresenter confirmViewPresenter =
                    new ConfirmViewPresenter(getPresenter().getMeetingID(), getPresenter().getCurrLogInUser(),
                            getPresenter().getObserver());
            confirmViewPresenter.run();
            updateViewFromModel(isFirst);
        });
        helpButton.addActionListener(e -> {
            // go to help view
            HelpViewPresenter helpViewPresenter = new HelpViewPresenter();
            helpViewPresenter.run();
        });
    }

    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        getPresenter().back();
        frame.setVisible(false);
    }


}
