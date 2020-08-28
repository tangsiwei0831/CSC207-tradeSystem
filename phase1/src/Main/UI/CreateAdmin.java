package Main.UI;

import Main.GateWay;
import User.AdministrativeUser;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import java.util.Scanner;

public class CreateAdmin {


    ClientUser user;
    Scanner sc;
    GateWay gw;
    UserManager um;
    ItemApprovalManager iam;
    public CreateAdmin(ClientUser user,GateWay gw){
        this.user=user;
        sc=new Scanner(System.in);
        um=new UserManager(gw);
        iam = new ItemApprovalManager(gw);
    }
    public void run(){
        System.out.println("Create new admin");
        System.out.println("Enter the username of new admin type 0 to quit.");
        String input4=sc.nextLine();
        if (!input4.equals("0")){
            System.out.println("Now enter the password of new admin");
            String input5555=sc.nextLine();
            ((AdministrativeUser)user).addNewUser(input4,input5555);
            System.out.println("New admin created successfully! Returning to menu");
        }
    }
}
