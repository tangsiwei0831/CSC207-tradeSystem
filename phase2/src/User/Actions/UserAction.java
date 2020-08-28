package User.Actions;

import java.io.Serializable;

public interface UserAction extends Serializable {

    String getIndicator();

    String getType();

    String getUsername();

    String getItem();

}
