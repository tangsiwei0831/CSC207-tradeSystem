package Main.UI;
import User.ClientUser;
import java.util.Scanner;

public class ChangePass {
    Scanner sc;
    ClientUser user;

    public ChangePass(ClientUser user){
        sc=new Scanner(System.in);
        this.user=user;

    }
    public void run(){
        System.out.println("Change password");
        System.out.println("Type in the password you wanted to change, type 0 to quit.");
        String input2=sc.nextLine();
        if (!input2.equals("0")){
            user.setPassword(input2);
            System.out.println("Changed password successfully!");
        }
    }
}
