package User.GUI;


import User.Adapter.AdminController;
import User.Adapter.IAdminController;
import User.Adapter.IUserController;

import javax.swing.*;
import java.awt.*;

public class DiffLimitGUI {
    IAdminController ac;
    JFrame pFrame;
    JFrame frame;

    /**
     * [Constructor]
     * @param pFrame frame
     */
    public DiffLimitGUI(JFrame pFrame) {
        this.pFrame=pFrame;
        ac = new AdminController();
    }

    /**
     * @param name  name of user
     * create new frame
     */
    public void run(String name){
        frame = new JFrame("Trade Limit");
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

        JLabel textLabel = new JLabel("Please enter the user's username and the limit below");
        textLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(textLabel);


        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

        JTextField userInput1 = new JTextField(30);
        userInput1.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput1);

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
            frame.setVisible(false);
            ac.setDiff(userInput.getText(),Integer.parseInt(userInput1.getText()));
            JOptionPane.showMessageDialog(null, "Successfully changed limit");
            UserFreezeSystem d = new UserFreezeSystem(frame);
            d.run(b);
        });
    }
}
