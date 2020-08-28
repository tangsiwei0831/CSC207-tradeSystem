package User.GUI;

import User.Actions.FreezeTicketUserAction;
import User.Adapter.*;

import javax.swing.*;
import java.awt.*;

public class UnfreezeGUI {
    ApprovalController avc;
    ActionController am;
    IAdminController ac;
    JFrame pFrame;
    JFrame frame;
    /**
     * [Constructor]
     * @param pFrame frame
     */
    public UnfreezeGUI(JFrame pFrame) {
        am=new ActionController();
        this.avc= new ApprovalController();
        this.ac = new AdminController();
        this.pFrame=pFrame;
    }
    /**
     * @param name  name of user
     * create new frame
     */
    public void run(String name){
        frame = new JFrame("UnFreeze User");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        placeComponents(frame, panel, name);
        frame.setVisible(true);
    }

    /**
     * @param frame new frame
     * @param panel the new panel
     * @param b name of user
     * set new frame
     */
    private void placeComponents(JFrame frame, JPanel panel, String b){

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(scrollPane, c);

        textArea.setText(avc.AllUserApprovals());
        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(submitButton);

        JButton exit = new JButton("Back");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);

        exit.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        submitButton.addActionListener(e -> {
            if(ac.getUser(userInput.getText())!=null){
                avc.removeUserApproval(userInput.getText());
                am.removeAction(userInput.getText(),new FreezeTicketUserAction(ac.getUser(userInput.getText())));
                ac.setFreeze(userInput.getText(),false);
                textArea.setText(avc.AllUserApprovals());
                JOptionPane.showMessageDialog(null,"Unfreeze successfully");
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid User");
            }
        });
    }
}
