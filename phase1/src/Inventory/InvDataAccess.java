package Inventory;

import Main.GateWay;

import java.io.*;
/**
 * [gateway class]
 * the class that read and write all the item information from ItemList.txt into ItemList in gateway
 */
public class InvDataAccess {
    /**
     * the place we store information
     */
    private final GateWay gw;

    /**
     * [constructor]
     * @param gw the place we store information
     */
    public InvDataAccess(GateWay gw){
        this.gw = gw;
    }

    /**
     * read all the items from ItemList.txt
     */
    public void readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("phase1/src/ItemList.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] lst = line.split(",");
                Item newItem = new Item(lst[0], lst[2]);
                newItem.setDescription(lst[1]);
                newItem.setIsInTrade(Boolean.parseBoolean(lst[3]));
                gw.getInv().add(newItem);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * update the new lendingList to file
     * @throws IOException when the update is failed
     */
    public void updateFile() throws IOException {
        File file = new File("phase1/src/ItemList.txt");
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
        for (Item item: gw.getInv()){
            addItemToFile(item);
        }
    }


    /**
     *
     * @param item the item wanted to add to file
     * @throws IOException when the item cannot be added to file
     */
    private void addItemToFile(Item item) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("phase1/src/ItemList.txt", true);
            fos.write((item.getName()+","+item.getDescription()+","+item.getOwnerName()+","+item.getIsInTrade()+"\n").getBytes());
        }catch(IOException e){
            throw new IOException("cannot add item to file");

        }
    }
}
