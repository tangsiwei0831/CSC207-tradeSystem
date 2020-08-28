package Main.UI;

import Main.GateWay;
import User.ClientUser;
import User.FileEditor;
import User.UserManager;

import java.util.Scanner;

/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class Register {
    /**
     * read input
     */
    public Scanner sc;
    /**
     * the object that edits the user list of input gateway
     */
    public UserManager a;
    /**
     * the object that edit the information from username.txt
     */
    public FileEditor fe;

    /**
     * [constructor]
     * @param gw the place we store information
     */
    public Register(GateWay gw){
        sc = new Scanner(System.in);
        a=new UserManager(gw);
        fe=new FileEditor(gw);
    }

    /**
     * run the system
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        while (input != 1) {
            System.out.println("--------------------\nRegister");
            System.out.println("Please enter your username!");
            System.out.print(">");
            String username = sc.nextLine();
            System.out.println("Please enter your password!");
            System.out.print(">");
            String password = sc.nextLine();
            if (a.getUser(username) == null) {
                ClientUser user1 = new ClientUser(username, password, false);
                fe.addToUsers(user1);
                System.out.println("Your account has been successfully created!");
                System.out.println("Your id: " + user1.getId());
                System.out.println("Your username: " + user1.getUsername());
                System.out.println("Your password: " + user1.getPassword());
                input=1;
            }
            else {
                System.out.println("The username already exists, please try to register again, " +
                        "enter any number to continue. Enter 1 to exit.");
                input = sc.nextInt();
                sc.nextLine();
            }
        }
    }
}
