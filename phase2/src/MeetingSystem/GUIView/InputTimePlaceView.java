package MeetingSystem.GUIView;

import MeetingSystem.Adapter.IPresenter;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class InputTimePlaceView extends JDialog implements InfoView {

    private JPanel contentPane;
    private JTextArea instructionTextArea;
    private JTextArea noteTextArea;
    private JButton buttonBack;
    private JButton buttonOK;
    private JButton buttonClear;
    private JFormattedTextField timeFormattedTextField;
    private JTextField placeTextField;

    // presenter
    private IPresenter presenter;

    public InputTimePlaceView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonClear.addActionListener(e -> onClear());

        buttonBack.addActionListener(e -> onBack());

        // call onBack() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onBack();
            }
        });

        // call onBack() on ESCAPE
        contentPane.registerKeyboardAction(e -> onBack(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        setOnOK(timeFormattedTextField, placeTextField);
    }

    abstract void setOnOK(JFormattedTextField timeFormattedTextField, JTextField placeTextField);

    private void onBack() {
        dispose();
    }

    private void onClear() {
        timeFormattedTextField.setText("");
        placeTextField.setText("");
    }

    private void createUIComponents() {
        instructionTextArea = new JTextArea("Please propose a time and place to meet!");
        instructionTextArea.setEditable(false);

        noteTextArea = new JTextArea("Note: \n " +
                "[Time] only digits allowed \n " +
                "[Address] only letters, digits, period, space allowed");
        noteTextArea.setEditable(false);

        timeFormattedTextField = new JFormattedTextField(new TextFieldFormatter().createFormatter());
        timeFormattedTextField.setToolTipText("yyyy-MM-dd hh:mm");

        placeTextField = new JTextField();
        placeTextField.setToolTipText("Must contain letters; \n Optional: digits, period, space");
    }

    public abstract void open();

    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    IPresenter getPresenter() {
        return presenter;
    }

    JFormattedTextField getTimeFormattedTextField() {
        return timeFormattedTextField;
    }

    JTextField getPlaceTextField() {
        return placeTextField;
    }

    public static class TextFieldFormatter {

        // source: https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
        MaskFormatter createFormatter() {
            MaskFormatter formatter = null;
            try {
                formatter = new MaskFormatter("####-##-## ##:##");
            } catch (java.text.ParseException exc) {
                System.err.println("formatter is bad: " + exc.getMessage());
                System.exit(-1);
            }
            return formatter;
        }
    }
}
