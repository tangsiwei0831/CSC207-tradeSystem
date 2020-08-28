package User.Gateway;


import User.Entity.ItemApprovals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * [GateWay]
 */
public class ApprovalItemDataAccess implements DataAccess {
    private final String serFilePath = "phase2/src/ItemApprovals.ser";
    private List<ItemApprovals> ItemApprovalsList;

    /**
     * [constructor]
     */
    // https://stackoverflow.com/questions/1205995/what-is-the-list-of-valid-suppresswarnings-warning-names-in-java
    @SuppressWarnings("all")
    public ApprovalItemDataAccess(){
        ItemApprovalsList=new ArrayList<>();
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
     * return list of items
     */
    @Override
    public ArrayList<Object> getList() {
        deSerialize();
        return new ArrayList<>(ItemApprovalsList);
    }

    /**
     * @param name name of item
     * find user by name
     */
    @Override
    public ItemApprovals getObject(String name) {
        deSerialize();
        for (ItemApprovals itemApprovals : ItemApprovalsList) {
            if (itemApprovals.getFstString().equals(name)) {
                return itemApprovals;
            }
        }
        return null;
    }

    /**
     * @param uuid  id of user
     * find item by id
     */
    @Override
    public Object getObject(UUID uuid) {
        return null;
    }

    /**
     * @param o item
     * add item into list
     */
    @Override
    public void addObject(Object o) {
        deSerialize();
        ItemApprovalsList.add((ItemApprovals)o);
        updateSer();
    }

    /**
     * @param o item
     * return if item in list
     */
    @Override
    public boolean hasObject(Object o) {
        for (ItemApprovals itemApprovals : ItemApprovalsList) {
            if (o.equals(itemApprovals.getFstString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param o name of item
     * remove item from the list
     */
    @Override
    public void removeObject(String o) {
        deSerialize();
        ItemApprovalsList.removeIf(i -> i.getFstString().equals(o));
        updateSer();
    }

    @Override
    public void removeObject(UUID o) {

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

                ItemApprovalsList = (List<ItemApprovals>) in.readObject();
                in.close();
                fileIn.close();
                //System.out.println("deSerialize: "+lendingList);
            }

        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * set list of items
     */
    @Override
    public void setList(List<Object> ItemApprovalsList) {
        this.ItemApprovalsList.clear();
        for(Object a: ItemApprovalsList){
            this.ItemApprovalsList.add((ItemApprovals) a);
        }
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

            out.writeObject(ItemApprovalsList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}


