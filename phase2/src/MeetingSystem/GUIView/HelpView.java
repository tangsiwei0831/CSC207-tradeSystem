package MeetingSystem.GUIView;

import MeetingSystem.Adapter.IPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Help (or About) View
 */
public class HelpView extends JDialog implements InfoView {

    private JPanel contentPane;
    private JPanel backPanel;
    private JTextPane textPane1;
    private JButton backButton;

    // presenter
    IPresenter presenter;

    /**
     * Constructs HelpView
     */
    public HelpView() {
        createUIComponents();
        setContentPane(contentPane);
        setModal(true);

        backButton.addActionListener(e -> HelpView.this.dispose());
    }

    private void createUIComponents() {
        textPane1 = new JTextPane();
        textPane1.setEditable(false);
        textPane1.setText(getInstruction());
    }

    private String getInstruction() {
        return "    Instructions for Meeting System\n" +
                "1. Setup/Edit input: \n" +
                "       - Time: (yyyy-MM-dd hh:mm) only future time allowed; must be valid time input\n" +
                "       - Place: valid string input\n" +
                "2. Edit input: \n" +
                "       - as in 1 \n" +
                "       - content to submit must be edited\n" +
                "3. Once the meeting sets up, the two traders can only make actions (i.e. edit, agree, confirm) " +
                "in turns. \n" +
                "4. Each trader can successfully edit each meeting 3 times; the fourth time would cause cancelling " +
                "the meeting and the trade. \n" +
                "5. Meeting Status can be:\n" +
                "       DNE, INCOMPLETE, CANCELLED, AGREED, COMPLETED \n" +
                "       - DNE:          when meeting is not yet set up\n" +
                "       - INCOMPLETE:   when meeting can be edited and/or agreed\n" +
                "       - CANCELLED:    when one user edits over threshold (more than 3 times)\n" +
                "       - AGREED:       when both users agreed the meeting proposal\n" +
                "       - COMPLETED:    when both users confirmed the meeting occurred\n";
    }

    /**
     * Sets the presenter to the view
     *
     * @param presenter the presenter
     */
    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Opens the view
     */
    @Override
    public void open() {
        this.pack();
        this.setVisible(true);
    }
}
