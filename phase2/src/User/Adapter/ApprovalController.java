package User.Adapter;

import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;

/**
 * [Controller]
 * controllers that control approval manager
 */
public class ApprovalController {
    ApprovalManager am = new ApprovalManager();
    UserManager um = new UserManager();

    /**
     * return the string of all approval users
     */
    public String AllUserApprovals(){
        return am.AllUserApprovals();
    }

    /**
     * @param ua string that represents the approval user
     * remove users
     */
    public void removeUserApproval(String ua){
        am.removeUserApproval(ua);
    }

    /**
     * @param name the name of user
     * @param des  description
     *             add approvals with description
     */
    public void addApprovals(String name, String des) {
        am.addApprovals(um.getUser(name), des);
    }


    /**
     * Check if user with username has approval
     *
     * @param username the username of the user
     * @return if user with username has approval
     */
    public boolean hasUserApproval(String username) {
        return am.hasUserApproval(username);
    }
}
