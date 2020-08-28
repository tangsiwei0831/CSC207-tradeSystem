package Trade.Adaptor.TradeHistory;

import MeetingSystem.UseCase.MeetingActionManager;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iPresent;
import Trade.Entity.Trade;
import User.UseCase.UserManager;

import java.util.ArrayList;
import java.util.List;

public class TradeHistoryPresenter implements iTradeHistoryPresenter {

    private final iPresent tgp;

    public TradeHistoryPresenter(BorderGUI tgp) {
        this.tgp = tgp;
    }

    public void closeFrame() {
        tgp.getFrame().setVisible(false);
    }

    public void updateTrade(List<Trade> tradeList) {
        String result = "";
        for (int i = 0; i < tradeList.size(); i++) {
            ArrayList<String> users = new ArrayList<>();
            users.add(new UserManager().UUIDToName(tradeList.get(i).getUsers().get(0)));
            users.add(new UserManager().UUIDToName(tradeList.get(i).getUsers().get(1)));
            result = result + i + ". " + "\n" + "Traders: "+ users + "\n" + tradeList.get(i).toString();
            if (tradeList.get(i).getMeeting() != null){
                result = result + "First meeting: "
                        + new MeetingActionManager().getMeetingWithId(tradeList.get(i).getMeeting()).toString() + "\n";
            }
            if (tradeList.get(i).getSecondMeeting() != null){
                result = result + "Second meeting: "
                        + new MeetingActionManager().getMeetingWithId(tradeList.get(i).getSecondMeeting()).toString() + "\n";
            }
        }
        if (result.equals("")){
            tgp.setListText("no available trade");
        }else{
            tgp.setListText(result);
        }
    }

    public void updateFreUser(List<String> userList) {
        String result = "";
        for (String a : userList) {
            result = result + a + "\n";
        }
        if (result.equals("")) {
            tgp.setCurrText("No User");
        } else {
            tgp.setCurrText(result);
        }
    }
}
