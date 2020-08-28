package Main.UI;

import Inventory.Inventory;
import Inventory.Item;
import Main.GateWay;
import User.AdministrativeUser;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * [ClientUser Interface]
 * shows the interface that the user uses
 */
public class EditInfo {

    /**
     * read input
     */
    public Scanner sc;
    /**
     * the object that edits the user list of input gateway
     */
    public UserManager a;

    /**
     * user in user system
     */
    public ClientUser user;

    /**
     * the object in the Item list of input gateway
     */
    public Inventory v;

    /**
     * the place we store information
     */
    public ItemApprovalManager iam ;
    public GateWay gw;
    /**
     * [constructor]
     * @param u the input user
     * @param gw the input gateway
     */
    public EditInfo(ClientUser u, GateWay gw){
        user=u;
        sc = new Scanner(System.in);
        a=new UserManager(gw);
        v = new Inventory(gw);
        iam = new ItemApprovalManager(gw);
        this.gw=gw;
    }

    /**
     * run the system
     */
    public void run(){
        Scanner sc=new Scanner(System.in);
        int exit=-1;
        while(exit!=0) {
            System.out.println("--------------------\nEdit user information");
            System.out.println("Hello,user," + user.getUsername());
            System.out.println("Admin:"+user.getIsAdmin());
            System.out.println("Actions:\n1.Change password\n2.ClientUser Freeze System");
            if (user.getIsAdmin()) {
                System.out.print("3.Change user's limit\n4.add new item into the system\n");
                if(user.getUsername().equals("admin")){
                    System.out.print("5.create new admin\n");
                }
            }
            System.out.println("0.exit");
            System.out.print(">");
            int input = sc.nextInt();
            sc.nextLine();
            System.out.println("-----------------------------");
            switch (input) {
                case 1:
                    ChangePass cp=new ChangePass(user);
                    cp.run();
                    break;
                case 2:
                    UserFreezeSystem ufs=new UserFreezeSystem(user,gw);
                    ufs.run();
                    break;
                case 3:
                    UserLimit ul=new UserLimit(user,gw);
                    ul.run();
                    break;
                case 5:
                    CreateAdmin ca=new CreateAdmin(user,gw);
                    ca.run();
                    break;
                case 4:
                    AddNewItem ani=new AddNewItem(user,gw,v);
                    ani.run();
                    break;
                case 0:
                    exit=0;
                    break;
            }
        }
    }
}
