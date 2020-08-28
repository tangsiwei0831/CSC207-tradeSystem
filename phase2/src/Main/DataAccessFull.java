package Main;

import Inventory.GateWay.InvDataAccess;
import Trade.GateWay.TradeDataAccess;
import User.Gateway.ApprovalItemDataAccess;
import User.Gateway.ApprovalUserDataAccess;
import User.Gateway.UserDataAccess;

public class DataAccessFull {
    private final InvDataAccess ida;
    private final TradeDataAccess tda;
    private final UserDataAccess fe;
    private final ApprovalItemDataAccess aa;
    private final ApprovalUserDataAccess au;

    /**
     * [constructor]
     */
    public DataAccessFull(){
        ida = new InvDataAccess();
        tda = new TradeDataAccess();
        fe = new UserDataAccess();
        aa = new ApprovalItemDataAccess();
        au = new ApprovalUserDataAccess();
    }

    /**
     * update all the files
     */
    public void updateFile()  {
        ida.updateSer();
        tda.updateSer();
        fe.updateSer();
        aa.updateSer();
        au.updateSer();
    }

    /**
     * read all the files
     */
    public void readFile() {
        ida.deSerialize();
        tda.deSerialize();
        fe.deSerialize();
        aa.deSerialize();
        au.deSerialize();
    }


}
