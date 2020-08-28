package MeetingSystem.GUIView;

import MeetingSystem.Adapter.TimePlaceInputController;
import MeetingSystem.Entity.Meeting;
import MeetingSystem.UseCase.DateTime;
import MeetingSystem.UseCase.Model;

import javax.swing.*;
import java.util.UUID;

/**
 * The edit view
 */
public class EditView extends InputTimePlaceView {

    // controller: validate time and address input
    TimePlaceInputController inputController = new TimePlaceInputController();

    @Override
    void setOnOK(JFormattedTextField timeFormattedTextField, JTextField placeTextField) {

        String timeFieldText = timeFormattedTextField.getText();
        String placeFieldText = placeTextField.getText();
        // check validation of input fields
        if (inputController.assessInput(timeFieldText, placeFieldText)) {

            UUID meetingID = getPresenter().getMeetingID();
            // check if both input fields has change
            if (getPresenter().getModel().isTimePlaceChanged(meetingID, timeFieldText, placeFieldText)) {

                // check if going to over edit threshold
                if (getPresenter().getModel().isEditable(meetingID, getPresenter().getCurrLogInUser())) {

                    // success window
                    JOptionPane.showMessageDialog(null, "Success!\n " +
                            "Date Time: " + timeFieldText + "\n" +
                            "Place: " + placeFieldText);
                } else {
                    // over edit threshold
                    // cancel window
                    JOptionPane.showMessageDialog(null, "Warn: Meeting Cancelled!\n ",
                            "Warn", JOptionPane.WARNING_MESSAGE);
                }

                // perform set up with inner app
                getPresenter().performAction(timeFieldText, placeFieldText);

                // set fields no longer editable
                timeFormattedTextField.setEnabled(false);
                placeTextField.setEnabled(false);

                // jump to main menu
                dispose();
            } else {
                // fail window
                JOptionPane.showMessageDialog(null, "Fail: Repeated Inputs.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                timeFormattedTextField.selectAll();
            }

        } else if (!inputController.assessDateTimeInput(timeFieldText)
                && inputController.assessPlaceInput(placeFieldText)) {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: DateTime Input failed.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();

        } else if (inputController.assessDateTimeInput(timeFieldText)
                && !inputController.assessPlaceInput(placeFieldText)) {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: Address Input failed.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();

        } else {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: Both Input failed.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();
        }
    }

    private void updateViewFromModel() {
        // set prev time, place fields
        DateTime dt = new DateTime();
        Model model = getPresenter().getModel();
        Meeting meeting = model.getMeeting(getPresenter().getMeetingID());
        getTimeFormattedTextField().setText(dt.convertLDTtoString(meeting.getDateTime()));
        getPlaceTextField().setText(meeting.getPlace());
    }

    /**
     * Opens and updates the view from the model
     */
    @Override
    public void open() {
        updateViewFromModel();
        this.pack();
        this.setVisible(true);
    }
}
