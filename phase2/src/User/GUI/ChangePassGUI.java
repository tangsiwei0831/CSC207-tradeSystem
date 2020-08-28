package User.GUI;

import User.Actions.PasswordUserAction;
import User.Adapter.ActionController;
import User.Adapter.ClientUserController;
import User.Adapter.IUserController;

import javax.swing.*;
import java.awt.*;

public class ChangePassGUI {

    IUserController uc;
    ActionController ac;
    JFrame pFrame;
    JFrame frame;
    String userName;

    /**
     * [Constructor]
     * @param pFrame frame
     */
    public ChangePassGUI(JFrame pFrame) {
        this.uc = new ClientUserController();
        this.pFrame=pFrame;
        ac=new ActionController();
    }

    /**
     * @param name  name of user
     * create new frame
     */
    public void run(String name){
        this.userName = name;

        frame = new JFrame("Change your password");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome: " + name);
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(welcomeLabel);
        frame.add(panel);

        placeComponents(frame, panel);
        frame.setVisible(true);
    }

    /**
     * @param frame new frame
     * @param panel the new panel
     * set new frame
     */
    private void placeComponents(JFrame frame, JPanel panel){
        JLabel curPass = new JLabel("Current password:"+getUserPass());
        curPass.setPreferredSize(new Dimension(300, 30));
        panel.add(curPass);
        JLabel nameLabel = new JLabel("Change your password");
        nameLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(nameLabel);

        JTextField passInput = new JTextField(30);
        passInput.setPreferredSize(new Dimension(300, 30));
        panel.add(passInput);

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
            String prePass=getUserPass();
            uc.setPassword(getUserName(), passInput.getText());
            ac.addAction(userName,new PasswordUserAction(uc.getUser(userName),prePass));
            curPass.setText("Current password:"+getUserPass());
            JOptionPane.showMessageDialog(null, "Successfully changed the password!");
        });
    }

    String getUserName() {
        return userName;
    }
    String getUserPass(){
        return uc.getPassword(userName);
    }
}
