package MeetingSystem.GUIView;

import MeetingSystem.Adapter.TimePlaceInputController;

import javax.swing.*;

/**
 * The setup view
 */
public class SetupView extends InputTimePlaceView {

    // controller: validate time and address input
    TimePlaceInputController inputController = new TimePlaceInputController();

    @Override
    void setOnOK(JFormattedTextField timeFormattedTextField, JTextField placeTextField) {

        String timeFieldText = timeFormattedTextField.getText();
        String placeFieldText = placeTextField.getText();
        if (inputController.assessInput(timeFieldText, placeFieldText)) {

            // perform set up with inner app
            getPresenter().performAction(timeFieldText, placeFieldText);

            // set fields no longer editable
            timeFormattedTextField.setEnabled(false);
            placeTextField.setEnabled(false);

            // success window
            JOptionPane.showMessageDialog(null, "Success!\n " +
                    "Date Time: " + timeFieldText + "\n" +
                    "Place: " + placeFieldText);

            // jump to main menu
            dispose();

        } else if (!inputController.assessDateTimeInput(timeFieldText)
                && inputController.assessPlaceInput(placeFieldText)) {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: DateTime Input failed.", "Error", JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();

        } else if (inputController.assessDateTimeInput(timeFieldText)
                && !inputController.assessPlaceInput(placeFieldText)) {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: Address Input failed.", "Error", JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();

        } else {
            // fail window
            JOptionPane.showMessageDialog(null, "Fail: Both Input failed.", "Error", JOptionPane.ERROR_MESSAGE);
            timeFormattedTextField.selectAll();
        }
    }

    /**
     * Opens and updates the view from the model
     */
    @Override
    public void open() {
        this.pack();
        this.setVisible(true);
    }

}
