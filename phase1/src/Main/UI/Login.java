package Main.UI;

import Main.GateWay;
import Trade.TradeManager;
import User.UserManager;

import java.io.IOException;
import java.util.Scanner;

/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class Login {
    /**
     * read input
     */
    public Scanner sc;
    /**
     * the object that edits the user list of input gateway
     */
    public UserManager a;
    /**
     * the object that edits the Item list of input gateway
     */
    public TradeManager tm;

    /**
     * the place we store information
     */
    public GateWay gw;

    /**
     * [constructor]
     * @param gw the place we store information
     */
    public Login(GateWay gw){
        this.gw = gw;
        sc = new Scanner(System.in);
        a = new UserManager(gw);
        tm = new TradeManager(gw);
    }

    /**
     * run the system
     */
    public void run() throws IOException {
        int input=0;
        while (input==0) {
            String username;
            String password;
            System.out.println("Please enter your account username!");
            System.out.print(">");
            username = sc.nextLine();
                System.out.println("Please enter your password!");System.out.print(">");
                password = sc.nextLine();

            if (a.verifyUser(username, password)) {
                while (true) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Hello," + username);
                    System.out.println(username);
                    System.out.println(a.getUser(username));
                    (a.getAdmin(a.getUser("admin"))).incompleteTransaction(a.getUser(username));
                    (a.getAdmin(a.getUser("admin"))).tradeLimit(a.getUser(username));
                    if(a.getUser(username).readDiff()>=a.getUser(username).getDiff()){
                        (a.getAdmin(a.getUser("admin"))).freeze(a.getUser(username));
                    }
                    System.out.println("Freeze Status: " + a.getUser(username).getIsFrozen());
                    System.out.println("Trade limit: " + tm.getTradeNumber(a.getUser(username)) + "/"
                            + a.getUser(username).getWeekTransactionLimit());
                    System.out.println("Incomplete trade limit: " + (tm.getIncomplete(a.getUser(username)).size()
                            + "/" + a.getUser(username).getIncompleteTransactionLimit()));
                    System.out.println("Difference between borrow and lend:"+a.getUser(username).readDiff()+"/"+a.getUser(username).getDiff());
                    System.out.println("**************************************************************");
                    System.out.println("Actions:\n1.Edit information\n2.Trade\n3.Inventory\n4.Market\n0.quit to menu");
                    System.out.print(">");
                    int op = sc.nextInt();
                    sc.nextLine();
                    if (op == 1) {
                        EditInfo ei = new EditInfo(a.getUser(username), gw);
                        ei.run();
                    } else if (op == 2) {
                        UserTrade ut = new UserTrade(a.getUser(username), gw);
                        ut.run();
                    } else if (op == 3) {
                        UserInventory ui=new UserInventory(a.getUser(username), gw);
                        ui.run();
                    } else if (op == 4) {
                        Market m=new Market(a.getUser(username), gw);
                        m.run();
                    } else if (op == 0) {
                        input=1;
                        break;
                    } else {
                        System.out.println("Sorry I don't understand your command, plz enter it again");
                    }
                }
            } else {
                System.out.println("You have incorrect username or password, please try to login again, " +
                        "enter 0 to continue,any other number to quit ot menu.");
                input = sc.nextInt();
                sc.nextLine();
            }
        }
    }
}
