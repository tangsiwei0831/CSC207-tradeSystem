package Inventory.GateWay;

import Inventory.Entity.Item;
import User.Gateway.DataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * [gateway class]
 * This class reads and writes all the item information from ItemList.ser into lendingList
 */
public class InvDataAccess implements DataAccess {

    private final String serFilePath = "phase2/src/itemList.ser";
    private List<Item> lendingList;

    /**
     * [constructor]
     * Create temporary item list. Check whether required file existed. If existed, deserialze the file, if not, create new file.
     */
    // https://stackoverflow.com/questions/1205995/what-is-the-list-of-valid-suppresswarnings-warning-names-in-java
    @SuppressWarnings("all")
    public InvDataAccess() {
        lendingList = new ArrayList<>();

        try {
            File serFile = new File(serFilePath);
            if (serFile.exists() && !(serFile.length() == 0)) {
                deSerialize();
            } else {
                serFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * serialize the file
     */
    public void serialize(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(serFilePath);
            OutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(buffer);

            out.writeObject(lendingList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * get the list from file
     * @return item list
     */
    @Override
    public List<Object> getList() {
        deSerialize();
        return new ArrayList<>(lendingList);
    }

    /**
     *
     * @param name the name of the object
     * @return Item
     */
    @Override
    public Object getObject(String name) {
        deSerialize();
        for (Item item : lendingList) {
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }

    /**
     * no use
     */
    @Override
    public Object getObject(UUID uuid) {
        return null;
    }

    /**
     * add item to file
     * @param o: item
     */
    @Override
    public void addObject(Object o) {
        deSerialize();
        lendingList.add((Item) o);
        updateSer();
    }

    /**
     * update list info to file
     */
    @Override @SuppressWarnings("ALL")
    public void updateSer(){
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

    // source: https://stackoverflow.com/questions/31540556/casting-object-to-list-results-in-unchecked-cast-warning
    @SuppressWarnings("unchecked")
    /**
     * update file info to list
     */
    public void deSerialize() {
        try {
            File file = new File(serFilePath);
            if (!(file.length() == 0)){
                FileInputStream fileIn = new FileInputStream(serFilePath);
                InputStream buffer = new BufferedInputStream(fileIn);
                ObjectInputStream in = new ObjectInputStream(buffer);

                lendingList = (List<Item>) in.readObject();
                in.close();
                fileIn.close();
            }

        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * no use
     * @param userList na
     */
    @Override
    public void setList(List<Object> userList) {

    }


    /**
     * whether the item is in the item list
     * @param o: item
     * @return boolean
     */
    @Override
    public boolean hasObject(Object o) {
        deSerialize();
        for (Item i: lendingList){
            if (i.getName().equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeObject(String o) {
        deSerialize();
        lendingList.removeIf(i -> i.getName().equals(o));
        updateSer();

    }

    @Override
    public void removeObject(UUID o) {

    }


//    /**
//     * read all the items from ItemList.txt
//     */
//    public void readFile(){
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("phase2/src/ItemList.txt"));
//            String line = reader.readLine();
//            while (line != null) {
//                String[] lst = line.split(",");
//                Item newItem = new Item(lst[0], lst[2]);
//                newItem.setDescription(lst[1]);
//                newItem.setIsInTrade(Boolean.parseBoolean(lst[3]));
//                iv.addItem(newItem);
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//    /**
//     *
//     * @param item the item wanted to add to file
//     * @throws IOException when the item cannot be added to file
//     */
//    private void addItemToFile(Item item) throws IOException {
//        try {
//            FileOutputStream fos = new FileOutputStream("phase2/src/ItemList.txt", true);
//            fos.write((item.getName()+","+item.getDescription()+","+item.getOwnerName()+","+item.getIsInTrade()+"\n").getBytes());
//        }catch(IOException e){
//            throw new IOException("cannot add item to file");
//
//        }
//    }
//
//
//    /**
//     * update trade.txt with information in trade list of gateway.
//     */
//    public void updateFile(){
//        File file = new File("phase2/src/itemList.txt");
//        try {
//            if(!file.exists()) {
//                boolean result = file.createNewFile();
//                if (!result){
//                    System.out.println("the trade file is not updated successfully");
//                }
//            }
//            FileWriter fileWriter =new FileWriter(file);
//            fileWriter.write("");
//            fileWriter.flush();
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        serialize();
//    }


}
