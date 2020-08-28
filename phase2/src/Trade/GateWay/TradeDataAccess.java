package Trade.GateWay;

import Trade.Entity.Trade;
import User.Gateway.DataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * [Gateway]
 * Connect TradeManager and Trade.ser
 */
public class TradeDataAccess implements DataAccess {

    private final String serFilePath = "phase2/src/trade.ser";
    private List<Trade> tradeList;


    // https://stackoverflow.com/questions/1205995/what-is-the-list-of-valid-suppresswarnings-warning-names-in-java
    @SuppressWarnings("all")
    /**
     * [constructer]
     * Check whether the file is existed. If existed, update file contents to a temporary list. If not, create the file.
     */
    public TradeDataAccess() {
        tradeList = new ArrayList<>();

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
     * get temporary trade list
     * @return trade list
     */
    @Override
    public List<Object> getList() {
        deSerialize();
        return new ArrayList<>(tradeList);
    }

    /**
     * no use
     * @param name na
     * @return na
     */
    @Override
    public Object getObject(String name) {
        // not used!
        return null;
    }

    /**
     * get trade with trade id
     * @param uuid trade id
     * @return trade
     */
    @Override
    public Object getObject(UUID uuid) {
        deSerialize();
        for (Trade trade : tradeList) {
            if (trade.getId().equals(uuid))
                return trade;
        }
        return null;
    }


    /**
     * add trade to file
     * @param o
     */
    @Override
    public void addObject(Object o) {
        deSerialize();
        tradeList.add((Trade) o);
        updateSer();
    }

    /**
     * update info from temporary ist to file
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
     * write to file
     */
    private void serialize() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(serFilePath);
            OutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(buffer);

            out.writeObject(tradeList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // source: https://stackoverflow.com/questions/31540556/casting-object-to-list-results-in-unchecked-cast-warning
    @SuppressWarnings("unchecked")
    /**
     * get temporary trade list from file
     */
    @Override
    public void deSerialize() {
        try {
            File file = new File(serFilePath);
            if (!(file.length() == 0)){
                FileInputStream fileIn = new FileInputStream(serFilePath);
                InputStream buffer = new BufferedInputStream(fileIn);
                ObjectInputStream in = new ObjectInputStream(buffer);

                tradeList = (List<Trade>) in.readObject();
                in.close();
                fileIn.close();
                //System.out.println("deSerialize: "+lendingList);
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
     * check whether the object is in file
     * @param o the object
     * @return boolean
     */
    @Override
    public boolean hasObject(Object o) {
        deSerialize();
        for (Trade i: tradeList){
            if (i.getId().equals(o)){
                return true;
            }
        }
        return false;
    }

    /**
     * no use
     */
    @Override
    public void removeObject(String o) {

    }

    /**
     * remove trade given trade UUID
     * @param o the UUID of trade that wants to be deleted
     */
    @Override
    public void removeObject(UUID o) {
        deSerialize();
        tradeList.removeIf(i -> i.getId().equals(o));
        updateSer();

    }

}
