package User.GUI;

import User.Adapter.AdminController;
import User.Adapter.ClientUserController;
import User.Adapter.IUserController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class leftGUI {
    IUserController uc;
    JFrame frame;
    JFrame pFrame;
    String username;
    /**
     * [Constructor]
     * @param pFrame frame
     */
    public leftGUI(IUserController uc, JFrame pFrame){
        this.uc = uc;
        this.pFrame = pFrame;
    }

    /**
     * @param name  name of user
     * create new frame
     */
    void run(String name){
        this.username = name;

        frame = new JFrame("Set Left Date");
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
        JLabel yearLabel = new JLabel("Set Year");
        yearLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(yearLabel);

        JTextField yearInput = new JTextField(30);
        yearInput.setPreferredSize(new Dimension(300, 30));
        panel.add(yearInput);
        yearInput.setText("2020");

        JLabel monLabel = new JLabel("Set Month");
        monLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(monLabel);

        JTextField monInput = new JTextField(30);
        monInput.setPreferredSize(new Dimension(300, 30));
        panel.add(monInput);
        monInput.setText("08");

        JLabel dateLabel = new JLabel("Set Date");
        dateLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(dateLabel);

        JTextField dateInput = new JTextField(30);
        dateInput.setPreferredSize(new Dimension(300, 30));
        panel.add(dateInput);
        dateInput.setText("15");

        JLabel hourLabel = new JLabel("Set Hour");
        hourLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(hourLabel);

        JTextField hourInput = new JTextField(30);
        hourInput.setPreferredSize(new Dimension(300, 30));
        panel.add(hourInput);
        hourInput.setText("11");

        JLabel minLabel = new JLabel("Set Minute");
        minLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(minLabel);

        JTextField minInput = new JTextField(30);
        minInput.setPreferredSize(new Dimension(300, 30));
        panel.add(minInput);
        minInput.setText("00");

        JLabel secLabel = new JLabel("Set Second");
        secLabel.setPreferredSize(new Dimension(300, 30));
        panel.add(secLabel);

        JTextField secInput = new JTextField(30);
        secInput.setPreferredSize(new Dimension(300, 30));
        panel.add(secInput);
        secInput.setText("00");

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(submitButton);

        String str = yearInput.getText() + '-' + monInput.getText() + '-' + dateInput.getText()+' '+
                hourInput.getText()+':'+minInput.getText()+':'+secInput.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);


        System.out.println(dateTime);

        JButton exit = new JButton("Back");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);
        exit.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        submitButton.addActionListener(e -> {
//        System.out.println(uc.getUser(getUserName()).getIsFrozen());
        uc.setEnd(getUserName(), dateTime);
//        if(LocalDateTime.now().isAfter(dateTime)){
//            uc.setFreeze(getUserName(), false);
//            uc.setLeft(getUserName(), false);
//        }
        System.out.println(uc.getUser(getUserName()).getIsFrozen());
            JOptionPane.showMessageDialog(null, "Successfully set Left!");
        });
    }

    String getUserName() {
        return username;
    }
}
