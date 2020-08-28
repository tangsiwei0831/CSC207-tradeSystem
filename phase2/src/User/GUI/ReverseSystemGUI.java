package User.GUI;


import User.Adapter.ActionController;
import User.Adapter.ClientUserController;
import User.Adapter.IUserController;
import User.Entity.ClientUser;
import User.UseCase.ApprovalManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReverseSystemGUI {
    IUserController uc;
    ActionController aac;
    JFrame pFrame;
    JFrame frame;
    public ReverseSystemGUI(JFrame pFrame) {
        this.uc=new ClientUserController();
        this.pFrame=pFrame;
        aac=new ActionController();
    }
    public void run(String name){
        frame = new JFrame("Reverse System");
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
        StringBuilder hi= new StringBuilder("Username:\n");
        ArrayList<ClientUser> name= (ArrayList<ClientUser>) uc.getUserList();
        if(name.size()==0){
            hi.append("No users currently\n");
        }
        for (ClientUser user : name) {
            hi.append(user.getUsername()).append("\n");
        }
        textArea.setText(hi.toString());
        JTextField userInput = new JTextField(30);
        userInput.setPreferredSize(new Dimension(300, 30));
        panel.add(userInput);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(300, 30));
        panel.add(submitButton);

        JButton exit = new JButton("Back");
        exit.setPreferredSize(new Dimension(300, 30));
        panel.add(exit);

        JTextArea textArea2=new JTextArea(5,20);
        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        textArea2.setEditable(false);
        panel.add(scrollPane2, c);


        JButton submitButton2 = new JButton("Reverse action");
        submitButton2.setPreferredSize(new Dimension(300, 30));
        panel.add(submitButton2);

        submitButton2.setEnabled(false);
        exit.addActionListener(e -> {
            frame.setVisible(false);
            pFrame.setVisible(true);
        });
        submitButton.addActionListener(e -> {
            updateTextArea(textArea2,userInput,submitButton2);
        });

        submitButton2.addActionListener(e -> {
            aac.reverse(userInput.getText());
            updateTextArea(textArea2,userInput,submitButton2);
        });
    }

    public void updateTextArea(JTextArea textArea,JTextField userInput,JButton submitButton){
        ClientUser user=uc.getUser(userInput.getText());
        if(user!=null){
            StringBuilder k= new StringBuilder();
            for(int i=0;i<user.getActions().size();i++){
                k.append(user.getActions().get(i).toString()).append("\n");
            }
            if(!(k.toString().equals(""))){
                textArea.setText(k.toString());
                submitButton.setEnabled(true);
            }else{
                textArea.setText("Currently there is no actions");
            }
        }
    }
}