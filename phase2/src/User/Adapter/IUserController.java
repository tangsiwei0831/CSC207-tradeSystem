package User.Adapter;

import User.Actions.UserAction;
import User.Entity.ClientUser;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IUserController {

    String getPassword(String username);

    void setPassword(String name, String password);

    UUID nameToUUID(String name);

    boolean getIsFrozen(UUID userID);

    int getTradeNumber(String username);

    String UUIDToName(UUID userID);

    int getWeekTransactionLimit(String a);

    int readDiff(String username);

    int getDiff(String a);

    int getIncompleteTransactionLimit(String a);

    int getIncompleteTransaction(UUID userId);

    boolean checkUser(String name);

    boolean getIsAdmin(String username);

    ClientUser getUser(String username);

    List<ClientUser> getUserList();

    void setLeft(String a,boolean s);

    void setFreeze(String a,boolean s);


    void setEnd(String username, LocalDateTime end);

    boolean getIsLeft(UUID userID);

    void deleteLItem(String username, String lendWish);

    void deleteBItem(String username, String borrowWish);

    boolean checkActionExist(String text, ArrayList<String> strings);

}
