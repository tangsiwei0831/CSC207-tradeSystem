package User.GUI;

import User.Adapter.ClientUserController;
import User.Adapter.IUserController;

import javax.swing.*;
import java.awt.*;

public class UserFreezeSystem {
    IUserController uc;
    JFrame pFrame;
    JFrame frame;
    /**
     * [Constructor]
     * @param pFrame frame
     */
    public UserFreezeSystem(JFrame pFrame) {
        this.uc= new ClientUserController();
        this.pFrame=pFrame;
    }
    /**
     * @param name  name of user
     * create new frame
     */
    public void run(String name){
        frame = new JFrame("User Freeze System");
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

        JLabel freezeStatus = new JLabel("Freeze Status: "+ uc.getIsFrozen(uc.nameToUUID(b)));
        freezeStatus.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeStatus);


        JButton editButton = new JButton("Request to remove freeze");
        editButton.setPreferredSize(new Dimension(300, 30));

        JButton tradeButton = new JButton("Freeze User");
        tradeButton.setPreferredSize(new Dimension(300, 30));

        JButton inventoryButton = new JButton("Unfreeze User");
        inventoryButton.setPreferredSize(new Dimension(300, 30));

        JButton exitButton = new JButton("Back");
        exitButton.setPreferredSize(new Dimension(300, 30));

        if(!uc.getIsAdmin(b)){
            tradeButton.setEnabled(false);
            inventoryButton.setEnabled(false);
        }
        if(!uc.getIsFrozen(uc.nameToUUID(b))){
            editButton.setEnabled(false);
        }
        panel.add(editButton);
        panel.add(tradeButton);
        panel.add(inventoryButton);
        panel.add(exitButton);
        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        editButton.addActionListener(e -> {
            frame.setVisible(false);
            RequestUnfreezeTicketGUI d= new RequestUnfreezeTicketGUI(frame);
            d.run(b);
        });
        tradeButton.addActionListener(e -> {
            frame.setVisible(false);
            FreeUserGUI d=new FreeUserGUI(frame);
            d.run(b);
        });
        inventoryButton.addActionListener(e -> {
            frame.setVisible(false);
            UnfreezeGUI d=new UnfreezeGUI(frame);
            d.run(b);
        });
    }
}
