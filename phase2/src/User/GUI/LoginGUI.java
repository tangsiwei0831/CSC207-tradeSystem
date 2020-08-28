package User.GUI;
import Inventory.Adaptor.MarketBuilder;
import Main.DataAccessFull;
import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUIEngineer;
import Trade.Adaptor.GUIPlan;
import User.Adapter.ClientUserController;
import User.Adapter.ClientUserPresenter;
import User.Adapter.LoginSystemPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class LoginGUI{

    JFrame frame;

    // presenter
    LoginSystemPresenter lsp;

    /**
     * create new frame
     */
    public void run() {
        lsp = new LoginSystemPresenter(frame);
        frame = new JFrame("Login/Register");
        frame.setSize(330, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
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
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField nameInput = new JTextField(20);
        nameInput.setBounds(100,20,80,25);
        panel.add(nameInput);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordInput = new JPasswordField(20);
        passwordInput.setBounds(100,50,80,25);
        panel.add(passwordInput);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(190,80,80,25);
        panel.add(registerButton);

        JButton logInButton = new JButton("Login");
        logInButton.setBounds(100,80,80,25);
        panel.add(logInButton);

        JButton exploreButton = new JButton("Explore");
        exploreButton.setToolTipText("Mandatory 3: Explore the market as a visitor");
        panel.add(exploreButton);

        JButton exitButton = new JButton("Quit");
        exitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(exitButton);


        logInButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = new String(passwordInput.getPassword());
            boolean response = lsp.login(name, password);
            if (!response) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Login! Please check the username and/or password!");
            }
            else {
                ClientUserController controller = new ClientUserController();
                UUID uuid = controller.getIDbyName(name);
                ClientUserPresenter clientUserPresenter = new ClientUserPresenter(uuid, frame);
                clientUserPresenter.run();

                ClientUserGUI a = new ClientUserGUI(frame, name);
                a.run();
                frame.setVisible(false);
            }
        });

        registerButton.addActionListener(e -> {
            String name = nameInput.getText();
            String password = new String(passwordInput.getPassword());
            if(!name.equals("")&&!password.equals("")){
                if(lsp.register(name,password)){
                    JOptionPane.showMessageDialog(null, "Success");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Username already exist");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "There is problem with either the username exit or the empty password");
            }

        });


        exploreButton.addActionListener(e -> {
            frame.setVisible(false);
            BorderGUIBuilder builder = new MarketBuilder(frame);
            BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
            engineer.constructGUI();
            GUIPlan tg = engineer.getGUI();
            tg.run();
        });

        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            DataAccessFull adf = new DataAccessFull();
            adf.updateFile();

        });



    }

}
