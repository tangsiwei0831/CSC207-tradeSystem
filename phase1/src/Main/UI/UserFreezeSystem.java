package Main.UI;

import Main.GateWay;
import User.AdministrativeUser;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class UserFreezeSystem {
    ClientUser user;
    Scanner sc;
    GateWay gw;
    UserManager um;
    ItemApprovalManager iam;
    public UserFreezeSystem(ClientUser user,GateWay gw){
        this.user=user;
        sc=new Scanner(System.in);
        um=new UserManager(gw);
        iam = new ItemApprovalManager(gw);
    }

    public void run() {
        System.out.println("User Freeze System");
        System.out.println("Menu");
        if (user.getIsFrozen()) {
            System.out.println("1.request to remove freeze");
        }
        if (user.getIsAdmin()) {
            System.out.println("2.Freeze user\n3.unfreeze user");
        }
        if (!user.getIsFrozen() && !user.getIsAdmin()) {
            System.out.println("Returning to menu.....");
        } else {
            int inputF = sc.nextInt();
            sc.nextLine();
            if (inputF == 2) {
                System.out.println("Type in the username of user you want to freeze, type 0 to quit.");
                String input3 = sc.nextLine();
                if (!input3.equals("0")) {
                    ClientUser ha = um.getUser(input3);
                    if (ha == null) {
                        System.out.println("Sorry there is no such user, returning to main menu.");
                    } else {
                        ((AdministrativeUser) um.getUser("admin")).freeze(ha);
                        System.out.println("user.ClientUser:" + ha.getUsername() + " account has been frozen");
                        System.out.println("Username: " + ha.getUsername());
                        System.out.println("Username: " + ha.getPassword());
                    }
                }
            } else if (inputF == 3) {
                ArrayList<ArrayList<String>> usa = iam.getUserApproval();
                for (int i = 0; i < usa.size(); i++) {
                    System.out.println("User" + i + ": " + usa.get(i).get(1));
                    System.out.println("Reason: " + usa.get(i).get(2));
                    System.out.println("**************************");
                }
                if (usa.size() == 0) {
                    System.out.println("Currently there is no user freeze request");
                }
                System.out.println("Enter the ClientUser number to approve,enter -1 to quit.");
                String inputU = sc.nextLine();
                int k = Integer.parseInt(inputU);
                if (k < usa.size() && k > -1) {
                    um.getUser(usa.get(k).get(1)).setFrozen(false);
                    usa.remove(k);
                }
            } else if (inputF == 1) {
                System.out.println("Please enter the reason why you should unfreeze...enter -1 to quit");
                String des = sc.nextLine();
                if (!des.equals("-1")) {
                    ArrayList<String> b = new ArrayList<>();
                    b.add("2");
                    b.add(user.getUsername());
                    b.add(des);
                    iam.getUserApproval().add(b);
                    System.out.println("Request successfully");
                    System.out.println("Please wait for the administrator to approve");
                }
            }
        }
    }
}
