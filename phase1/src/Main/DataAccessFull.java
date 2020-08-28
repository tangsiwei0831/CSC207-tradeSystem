package Main;

import Inventory.InvDataAccess;
import Main.UI.ApprovalDataAccess;
import Trade.TradeDataAccess;
import User.FileEditor;

import java.io.IOException;
/**
 * [gateway]
 * read and update all the files that store the information
 */
public class DataAccessFull {
    private final InvDataAccess ida;
    private final TradeDataAccess tda;
    private final FileEditor fe;
    private  final ApprovalDataAccess aa;

    /**
     * [constructor]
     * @param gw the place we store information
     */
    public DataAccessFull(GateWay gw){
        ida = new InvDataAccess(gw);
        tda = new TradeDataAccess(gw);
        fe = new FileEditor(gw);
        aa= new ApprovalDataAccess(gw);
    }

    /**
     * update all the files
     */
    public void updateFile() throws IOException {
        ida.updateFile();
        tda.updateFile();
        fe.updateFile();
        aa.updateFile();
    }

    /**
     * read all the files
     */
    public void readFile() {
        ida.readFile();
        tda.readFile();
        fe.splitUser(fe.readFile());
        aa.readItem();
        aa.readUser();
    }


}

