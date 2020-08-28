package Trade.Adaptor.RequestTrade;

import Inventory.Entity.Item;
import Trade.Entity.Trade;
import User.Entity.ClientUser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface iRequestTradeController {


    void getTarUser(String item);

    void presentTradeInfo();

    void onewayBut(String duration);

    void twowayBut(String duration);

    void backBut();
}

