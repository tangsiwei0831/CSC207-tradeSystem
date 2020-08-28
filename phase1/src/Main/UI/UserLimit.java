package Main.UI;

import Main.GateWay;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import java.util.Scanner;

public class UserLimit {

    ClientUser user;
    Scanner sc;
    GateWay gw;
    UserManager um;
    ItemApprovalManager iam;
    public UserLimit(ClientUser user,GateWay gw){
        this.user=user;
        sc=new Scanner(System.in);
        um=new UserManager(gw);
        iam = new ItemApprovalManager(gw);
    }
    public void run(){
        System.out.println("Change user's limit");
        System.out.println("Menu\n1.Change trade limit\n2.Change Incomplete Transaction limit\n" +
                "3.Change the difference between borrowed and lend");
        int input30 = sc.nextInt();
        sc.nextLine();
        if (input30 == 1) {
            System.out.println("Which user do you want to change?");
            String input31 = sc.nextLine();
            if (input31.equals("-1")||um.getUser(input31) == null) {
                if(um.getUser(input31) == null){
                    System.out.println("You entered wrong username");
                }
            }else {
                ClientUser b = um.getUser(input31);
                System.out.println("Enter a number to change");
                int input33 = sc.nextInt();
                sc.nextLine();
                b.setWeekTransactionLimit(input33);
            }
        }
        else if(input30==2){
            System.out.println("Which user do you want to change?enter -1 to break");
            String input31=sc.nextLine();
            if (input31.equals("-1")||um.getUser(input31) == null) {
                if (um.getUser(input31) == null) {
                    System.out.println("You entered wrong username");
                }
            }else {
                ClientUser b = um.getUser(input31);
                System.out.println("Enter a number to change");
                int input33 = sc.nextInt();
                sc.nextLine();
                b.setIncompleteTransaction(input33);
            }
        }
        else if(input30==3){
            System.out.println("Which user do you want to change? enter -1 to break");
            String input31=sc.nextLine();
            if (input31.equals("-1")||um.getUser(input31) == null) {
                if (um.getUser(input31) == null) {
                    System.out.println("You entered wrong username");
                }
            }else {
                ClientUser b = um.getUser(input31);
                System.out.println("Enter a number to change");
                int input33 = sc.nextInt();
                sc.nextLine();
                b.setDiff(input33);
            }
        }
    }
}
