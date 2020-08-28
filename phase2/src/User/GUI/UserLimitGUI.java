package User.GUI;

import User.Adapter.ClientUserController;
import User.Adapter.IUserController;

import javax.swing.*;
import java.awt.*;

public class UserLimitGUI {
    IUserController uc;
    JFrame pFrame;
    JFrame frame;
    /**
     * [Constructor]
     * @param pFrame frame
     */
    public UserLimitGUI(JFrame pFrame) {
        this.uc=new ClientUserController();
        this.pFrame=pFrame;
    }
    /**
     * @param name  name of user
     * create new frame
     */
    public void run(String name){
        frame = new JFrame("User Limit System");
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

        JButton editButton = new JButton("Weekly Transaction Limit");
        editButton.setPreferredSize(new Dimension(300, 30));

        JButton tradeButton = new JButton("Incomplete Transaction Limit");
        tradeButton.setPreferredSize(new Dimension(300, 30));

        JButton inventoryButton = new JButton("Difference between borrowed and lend");
        inventoryButton.setPreferredSize(new Dimension(300, 30));

        JButton pointButton = new JButton("Redeem standard for bonus point");
        inventoryButton.setPreferredSize(new Dimension(300, 30));

        JButton exitButton = new JButton("Back");
        exitButton.setPreferredSize(new Dimension(300, 30));

        panel.add(editButton);
        panel.add(tradeButton);
        panel.add(inventoryButton);
        panel.add(pointButton);
        panel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        editButton.addActionListener(e -> {
            frame.setVisible(false);
           TradeLimitGUI d=new TradeLimitGUI(frame);
            d.run(b);
        });
        tradeButton.addActionListener(e -> {
            frame.setVisible(false);
            IncompleteLimitGUI d=new IncompleteLimitGUI(frame);
            d.run(b);
        });
        inventoryButton.addActionListener(e -> {
            frame.setVisible(false);
            DiffLimitGUI d=new DiffLimitGUI (frame);
            d.run(b);
        });
        pointButton.addActionListener(e ->{
            frame.setVisible(false);
            ExStandardGUI d=new ExStandardGUI(frame);
            d.run(b);
        });
    }
}