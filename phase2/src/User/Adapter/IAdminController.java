package User.Adapter;

public interface IAdminController extends IUserController {
    void createAdmin(String name, String password);

    void setDiff(String username,int diff);

    void setExchangeStandard(int exStandard);

    boolean checkUser(String name);

    void setFreeze(String a,boolean s);

    void setIncompleteTransaction(String username,int incompleteTransaction);

    void setWeekTransactionLimit(String username, int weekTransaction);

    void setLeft(String a,boolean s);

}
