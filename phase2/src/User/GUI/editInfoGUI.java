package User.GUI;

import User.Adapter.ClientUserController;
import User.Adapter.IUserController;
import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;

public class editInfoGUI {
    IUserController uc;
    JFrame frame;
    JFrame PFrame;

    /**
     * [Constructor]
     * @param pFrame frame
     */
    public editInfoGUI(JFrame pFrame) {
        this.uc = new ClientUserController();
        this.PFrame=pFrame;
    }

    /**
     * @param name  name of user
     * create new frame
     */
    public void run(String name){
        frame = new JFrame("Edit User Information");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Hello, " + name);
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

        JLabel isAdmin = new JLabel("Admin: "+ uc.getIsAdmin(b));
        isAdmin.setPreferredSize(new Dimension(300, 30));
        panel.add(isAdmin);

        JLabel action = new JLabel("Actions: ");
        action.setPreferredSize(new Dimension(300, 30));
        panel.add(action);

        JButton changePass = new JButton("Change Password");
        changePass.setPreferredSize(new Dimension(300, 30));
        panel.add(changePass);

        JButton freezeSystem = new JButton("ClientUser Freeze System");
        freezeSystem.setPreferredSize(new Dimension(300, 30));
        panel.add(freezeSystem);

        JButton limitSystem = new JButton("ClientUser Limit System");
        limitSystem.setPreferredSize(new Dimension(300, 30));
        panel.add(limitSystem);

        JButton CreateAdminGUI = new JButton("Create new Admin");
        CreateAdminGUI.setPreferredSize(new Dimension(300, 30));
        panel.add(CreateAdminGUI);

        JButton ReverseSystem = new JButton("Reverse System");
        ReverseSystem.setPreferredSize(new Dimension(300, 30));
        panel.add(ReverseSystem);

        JButton isLeft = new JButton("set Left");
        isLeft.setPreferredSize(new Dimension(300, 30));
        panel.add(isLeft);

        JButton exit = new JButton("Back");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);
        if(!uc.getIsAdmin(b)){
            limitSystem.setEnabled(false);
            ReverseSystem.setEnabled(false);
            CreateAdminGUI.setEnabled(false);

        }
        exit.addActionListener(e -> {
            frame.setVisible(false);
            PFrame.setVisible(true);
            UserManager um=new UserManager();
            for(ClientUser i:um.getUserList()){
                System.out.println(i.getUsername());
                System.out.println(i.getPassword());
                System.out.println("----------");
            }
        });
        limitSystem.addActionListener(e -> {
            frame.setVisible(false);
            UserLimitGUI d = new UserLimitGUI(frame);
            d.run(b);
        });
        CreateAdminGUI.addActionListener(e -> {
            frame.setVisible(false);
         CreateAdminGUI d = new CreateAdminGUI(frame);
            d.run(b);
        });
       changePass.addActionListener(e -> {
            frame.setVisible(false);
            ChangePassGUI d = new ChangePassGUI(frame);
            d.run(b);
        });
        freezeSystem.addActionListener(e -> {
            frame.setVisible(false);
            UserFreezeSystem d = new UserFreezeSystem(frame);
            d.run(b);
        });

        ReverseSystem.addActionListener(e -> {
            frame.setVisible(false);
            ReverseSystemGUI d = new ReverseSystemGUI(frame);
            d.run(b);
        });

        isLeft.addActionListener(e -> {
            frame.setVisible(false);
            leftGUI d = new leftGUI(uc, frame);
            d.run(b);
        });

    }
}
