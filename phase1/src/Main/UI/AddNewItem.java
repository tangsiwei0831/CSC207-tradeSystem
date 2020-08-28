package Main.UI;

import Inventory.Inventory;
import Inventory.Item;
import Main.GateWay;
import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class AddNewItem {
    ClientUser user;
    Scanner sc;
    GateWay gw;
    UserManager um;
    ItemApprovalManager iam;
    Inventory iv;
    public AddNewItem(ClientUser user, GateWay gw, Inventory iv){
        this.gw=gw;
        this.user=user;
        sc=new Scanner(System.in);
        um=new UserManager(gw);
        iam = new ItemApprovalManager(gw);
        this.iv=iv;
    }

    public void run(){
        System.out.println("add new item into the system");
        System.out.println("Menu:\n1.Add item for yourself.\n2.Approve request from users");
        String inputA=sc.nextLine();
        if(inputA.equals("1")) {
            int exit1 = 0;
            String name = "";
            while (exit1 == 0) {
                System.out.println("Type the name of the item");
                name = sc.nextLine();
                boolean t = false;
                for (Item n : iv.getLendingList()) {
                    if (n.getName().equals(name)) {
                        t = true;
                        break;
                    }
                }
                if (t) {
                    System.out.println("The item already exists, please enter the name again");
                } else {
                    exit1 = 1;
                }
            }
            System.out.println("Type the description of the item");
            String des = sc.nextLine();
            Item i = new Item(name, user.getUsername());
            i.setDescription(des);
            iv.addItem(i);
            user.addWishes(name);

            System.out.println("Added successfully!");
        }
        else if(inputA.equals("2")){
            int x=0;
            while(x==0) {
                ArrayList<ArrayList<String>> hii=iam.getItemApproval();
                for (int i=0;i<hii.size();i++) {
                    System.out.println("Item " + i + ": " + hii.get(i).get(1));
                    System.out.println("Description: " + hii.get(i).get(2));
                    System.out.println("Owner: " + hii.get(i).get(3));
                    System.out.println("**************************");
                }
                if(hii.size()==0){
                    System.out.println("There is no item currently");
                }
                System.out.println("Enter the item number to approve,enter -1 to quit.");
                String inputs = sc.nextLine();
                int k = Integer.parseInt(inputs);
                if (k > -1 && k < (hii.size())) {
                    Item i = new Item(hii.get(k).get(1), hii.get(k).get(3));
                    i.setDescription(hii.get(k).get(2));
                    um.getUser(hii.get(k).get(3)).addWishes(hii.get(k).get(1));
                    iv.addItem(i);
                    iam.getItemApproval().remove(k);
                    System.out.println("Approve successfully");
                }else if(k==-1){
                    x=1;
                }
                else {
                    System.out.println("You enter the wrong number!");
                }
            }
        }
    }
}
