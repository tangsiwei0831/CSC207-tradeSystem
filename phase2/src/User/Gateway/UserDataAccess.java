package User.Gateway;

import User.Entity.ClientUser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * [GateWay]
 */
public class UserDataAccess implements DataAccess {

    private final String serFilePath = "phase2/src/user.ser";
    private List<ClientUser> userList;

    /**
     * [constructor]
     */
    // https://stackoverflow.com/questions/1205995/what-is-the-list-of-valid-suppresswarnings-warning-names-in-java
    @SuppressWarnings("all")
    public UserDataAccess() {
        userList = new ArrayList<>();

        try {
            File serFile = new File(serFilePath);
            if (serFile.exists()) {
                deSerialize();
            } else {
                serFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * return list of users
     */
    @Override
    public List<Object> getList() {
        deSerialize();
        return new ArrayList<>(userList);
    }

    /**
     * set list of users
     */
    public void setList(List<Object> userList) {
        this.userList.clear();
        for(Object a: userList){
            this.userList.add((ClientUser)a);
        }
    }

    /**
     * @param name name of user
     * find user by name
     */
    @Override
    public Object getObject(String name) {
        deSerialize();
        for (ClientUser u : userList) {
            if (u.getUsername().equals(name))
                return u;
        }
        return null;
    }

    /**
     * @param uuid  id of user
     * find user by id
     */
    @Override
    public Object getObject(UUID uuid) {
        deSerialize();
        for (ClientUser u : userList) {
            if (u.getId().equals(uuid))
                return u;
        }
        return null;
    }

    /**
     * update the database file
     */
    @Override @SuppressWarnings("ALL")
    public void updateSer() {
        File file = new File(serFilePath);
        file.delete();
        try {
            if(!file.exists()) {
                boolean result = file.createNewFile();
                if (!result){
                    System.out.println("the new file is not created");
                }
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serialize();

    }

    /**
     * @param o user
     * add user into list
     */
    @Override
    public void addObject(Object o) {
        deSerialize();
        userList.add((ClientUser) o);
        updateSer();
    }

    /**
     * Serializes the userList to the user.ser file
     */
    private void serialize() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(serFilePath);
            OutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(buffer);

            out.writeObject(userList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    /**
     * Deserializes the user.ser file to the userList
     */
    // source: https://stackoverflow.com/questions/31540556/casting-object-to-list-results-in-unchecked-cast-warning
    @SuppressWarnings("unchecked")
    public void deSerialize() {
        try {
            File file = new File(serFilePath);
            if (!(file.length() == 0)){
                FileInputStream fileIn = new FileInputStream(serFilePath);
                InputStream buffer = new BufferedInputStream(fileIn);
                ObjectInputStream in = new ObjectInputStream(buffer);

                userList = (List<ClientUser>) in.readObject();
                in.close();
                fileIn.close();
            }

        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * @param o user
     * return if user in list
     */
    @Override
    public boolean hasObject(Object o) {
        deSerialize();
        for (ClientUser i: userList){
            if (i.getId().equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeObject(String o) {

    }

    /**
     * @param o id of user
     * remove user from the list
     */
    @Override
    public void removeObject(UUID o) {
        deSerialize();
        userList.removeIf(u -> u.getId().equals(o));
        updateSer();
    }

}

