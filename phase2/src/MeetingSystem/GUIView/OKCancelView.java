package MeetingSystem.GUIView;

import MeetingSystem.Adapter.IPresenter;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The OKCancelView
 */
public abstract class OKCancelView extends JDialog implements InfoView {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea questionTextArea;

    // presenter
    private IPresenter presenter;

    /**
     * Constructs the OKCancelView
     */
    public OKCancelView() {
        createUIComponents();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        setOnOK();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        questionTextArea = new JTextArea();
        setTextArea(questionTextArea);
    }

    abstract void setOnOK();

    abstract void setTextArea(JTextArea questionTextArea);

    IPresenter getPresenter() {
        return presenter;
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
