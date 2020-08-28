package User.Gateway;

import java.util.List;
import java.util.UUID;

public interface DataAccess {

    List<Object> getList();

    Object getObject(String name);

    Object getObject(UUID uuid);

    void addObject(Object o);

    boolean hasObject(Object o);

    void removeObject(String o);

    void removeObject(UUID o);

    void updateSer(); // write list to ser

    void deSerialize(); // write ser to list

    void setList(List<Object> list);

}
