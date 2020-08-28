package User;

import Main.GateWay;
import Trade.Trade;
import Trade.TradeManager;

import java.util.*;

/**
 * [UseCase class]
 * The renew and modification of users
 */
public class UserManager {
    /**
     * the place we store information
     */
    GateWay gw;
    /**
     * the list of users
     */
    ArrayList<ClientUser> userList;

    /**
     * [constructor]
     * @param gw the place we store information
     */
    public UserManager(GateWay gw){
        this.gw = gw;
        userList = gw.getUsers();
    }

    /**
     * return list of users
     */
    public ArrayList<ClientUser> getUserList(){
        return userList;
    }

    /**
     * @param name the name of the user that the manager wants to get
     * find the user by the user name
     */
    public ClientUser getUser(String name) {
        FileEditor f = new FileEditor(gw);
        //ArrayList<ClientUser> userList = splitUser(readFile());
        for(ClientUser u : gw.getUsers()){
            if(u.getUsername().equals(name))
                return u;
        }
        return null;
    }

    /**
     * @param userId the ID of the user that the manager wants to get
     * find the user by the user ID
     */
    public ClientUser getUser(UUID userId) {
        FileEditor f = new FileEditor(gw);
        //ArrayList<ClientUser> userList = splitUser(readFile());
        for(ClientUser u : gw.getUsers()){
            if(u.getId().equals(userId))
                return u;
        }
        return null;
    }

    /**
     * @param user the input user
     *get the administrative user by id
     */
    public AdministrativeUser getAdmin(ClientUser user){
        return new AdministrativeUser(user.getUsername(), user.getPassword(),
                true, new TradeManager(gw), new UserManager(gw));
    }


    /**
     * @param name the name of the user that the manager check
     * @param password the password of the user that the manager check
     * Check if the name matches with the password
     */
    public boolean verifyUser(String name, String password) {
        //ArrayList<ClientUser> userList = splitUser(readFile());
        for(ClientUser u : gw.getUsers()){
            if(u.getUsername().equals(name) && u.getPassword().equals(password)) {
                return true;}
        }
        return false;
    }

    /**
     * @param tm the object that edits the Item list of input gateway
     * @param user the input user
     * return the list of most frequent three traders that the user trades with
     * if the user trades with less than three traders, return all the traders the user trades with
     */
    public List<String> getFrequentUser(TradeManager tm, ClientUser user) {
        List<Trade> a = tm.getAllTrade(user);
        HashMap<UUID, Integer> b = new HashMap<>();
        for (Trade c : a) {
            for (UUID d : c.getUsers()) {
                if (!(d.equals(user.getId()))) {
                    if (b.containsKey(d)) {
                        b.replace(d, b.get(d) + 1);
                    } else {
                        b.put(d, 1);
                    }
                }
            }
        }
        if(b.size() == 0){
            List<String> EmptyList = new ArrayList<>(Collections.emptyList());
            EmptyList.add("no user");
            return EmptyList;}
        int e = 0;
        ArrayList<String> g = new ArrayList<>();
        int maxValueInMap = (Collections.max(b.values()));
        for (Map.Entry<UUID, Integer> entry : b.entrySet()) {
            if (entry.getValue() == maxValueInMap && e != 3) {
                g.add(getUser(entry.getKey()).getUsername());
                e++;
                b.remove(entry.getKey());
            }
        }
        return g;
    }
}

