package User.PointSystem;

import Trade.UseCase.TradeManager;
import User.Entity.ClientUser;
import User.UseCase.UserManager;
import java.util.*;

/**
 * [Use Case] class
 * Responsible for managing bonus points of all users
 *
 */
public class PointManager {
    private final UserManager um;
    private final TradeManager tm;

    /**
     * A list of all users with the bonus points they earn
     */
    private final Map<UUID, Integer> pointList;

    /**
     * Constructs the Point Manager to manage points for client users.
     */
    public PointManager(){
        this.um = new UserManager();
        this.tm = new TradeManager();
        this.pointList = new HashMap<>();
    }

    /**
     * Set (update) the bonus points for particular user and update the pointList.
     */
    public void setUserPoints(UUID userId) {
        ClientUser user = um.getUser(userId);
        int newPoint = this.tm.getComplete(userId).size() - um.getSelectedBonusTrades(userId).size() * user.getExStandard();
        ClientUser user1 = um.popUser(userId);
        user1.setBonusPoints(newPoint);
        this.pointList.put(user1.getId(), newPoint);
        um.addUser(user1);
    }

    /**
     * Return the number of bonus points for user
     * @param user the specific user
     */
    public int getUserPoints(ClientUser user) {
        return this.pointList.get(user.getId());
    }


}
