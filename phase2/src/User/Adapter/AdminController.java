package User.Adapter;

import User.Entity.ClientUser;
import User.UseCase.AdminActivityManager;

import java.util.List;
/**
 * [Controller]
 * controllers that control admin user
 */
public class AdminController extends ClientUserController implements IUserController, IAdminController{
    private final AdminActivityManager am;

    /**
     * [Constructor]
     */
    public AdminController() {
        super();
        this.am = new AdminActivityManager();

    }

    /**
     * @param username  name of user
     * @param diff  the difference between borrow amount and lend amount
     * set the difference standard for user
     */
    public void setDiff(String username,int diff) {
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setDiff(diff);
        um.addUser(user);
    }

    /**
     * @param name name of user
     * @param password  password of user
     * create new admin user
     */
    public void createAdmin(String name, String password) {
        am.addNewAdmin(name, password);
    }

    /**
     * @param a the user name
     * @param s  the freeze status of user
     * set the freeze status of user
     */
    public void setFreeze(String a,boolean s){
        am.setFreeze(a, s);
    }

    /**
     * @param username the user name
     * @param incompleteTransaction  the amount of incomplete transaction of user
     * set the amount of incomplete transaction of user
     */
    public void setIncompleteTransaction(String username,int incompleteTransaction) {
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setIncompleteTransaction(incompleteTransaction);
        um.addUser(user);
    }

    /**
     * @param username the user name
     * @param weekTransaction  the amount of week transaction of user
     * set the amount of week transaction of user
     */
    public void setWeekTransactionLimit(String username, int weekTransaction){
        ClientUser user = um.popUser(um.nameToUUID(username));
        user.setWeekTransactionLimit(weekTransaction);
        um.addUser(user);
    }

    @Override
    public void setLeft(String a, boolean s) {
        am.setLeft(a, s);
    }

    /**
     * @param exStandard the exchange standard of user
     * set the exchange standard of user
     */
    public void setExchangeStandard(int exStandard) {
        List<ClientUser> userList = um.getUserList();
        for (ClientUser user: userList) {
            ClientUser u = um.popUser(user.getId());
            u.setExStandard(exStandard);
            System.out.println("acb ex" + u.getUsername() + ": " + u.getExStandard());
            um.addUser(u);
            System.out.println("ac ex" + user.getUsername() + ": " + um.getUser(user.getId()).getExStandard());
        }
    }

    @Override
    public void deleteLItem(String username, String lendWish) {

    }

    @Override
    public void deleteBItem(String username, String borrowWish) {

    }
}
