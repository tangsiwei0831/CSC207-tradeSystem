package User.PointSystem;

import Trade.Entity.Trade;
import Trade.UseCase.TradeManager;
import Trade.TradeStatus;
import User.Entity.ClientUser;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import Trade.Adaptor.BorderGUI;
import User.UseCase.UserManager;

/**
 * [Controller]
 * Responsible for awarding the users with bonus trades.
 */
public class AwardActivities implements iPointController {

    private final TradeManager tm = new TradeManager();
    private final PointManager pm = new PointManager();
    private Trade currTrade;
    private List<Trade> tradeList;
    private final JFrame fr;
    private final iPointPresenter pp;
    private final String currUser;
    private final UserManager um;

    /**
     * Constructs the AwardActivities for user
     */
    public AwardActivities(String currUser, JFrame fr, BorderGUI tg){
        this.currUser = currUser;
        this.fr = fr;
        pp = new PointPresenter(tg);
        um = new UserManager();

    }

    /**
     * Get current Trade
     */
    public Trade getCurrTrade(String num){
        int tradeNum = Integer.parseInt(num.trim());
        currTrade = tradeList.get(tradeNum);
        return currTrade;
    }

    /**
     * Check if the input satisfy certain condition
     * @param num the input
     */
    public boolean checkInput(String num){
        if (!isNum(num)){
            return true;
        }else return Integer.parseInt(num) < 0 | Integer.parseInt(num) >= tradeList.size();
    }

    private boolean isNum(String str){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * Provide a list of trades for user to select as bonus trades to avoid counting towards being frozen.
     * The trades in list are incomplete trades OR trades within the most recent 7 days.
     * Once the trade is selected as bonus, a fixed amount of bonus points will be deducted.
     * @param user the current user who makes actions
     */
    public List<Trade> getTradesForExchange(ClientUser user){
        List<Trade> result = new ArrayList<>();
        tradeList = tm.getAllTrade(user.getId());
        List<UUID> weekList = new ArrayList<>();
        for (Trade i: tm.getWeekTradeList(user.getUsername())) {
            weekList.add(i.getId());
        }
        for (Trade t: tradeList) {
            if (weekList.contains(t.getId())) {
                result.add(t);
            }else if (t.getStatus().equals(TradeStatus.incomplete)) {
                result.add(t);
            }if (user.getSelectedBonusTrades().contains(t.getId())) {
                result.remove(t);
            }
        }
        return result;
    }

    /**
     * Set the selected trade to bonus and update the points for user. (update pointList as well)
     * Notify the user when bonus points are not enough to exchange for a trade.
     * @param userId the ID of current user who is making actions
     * @param selected the trade user selected to be bonus
     */
    private void getBonus(UUID userId, Trade selected){
        ClientUser user = this.um.getUser(userId);
        if (selected == null){
            pp.notTradeSelected();
        }else if (pm.getUserPoints(user) < user.getExStandard()){
           pp.pointNotEnough();
        }
        else{
            ClientUser u1 = this.um.popUser(userId);
            u1.addBonusTrade(selected.getId());
            this.um.addUser(u1);
            this.pm.setUserPoints(user.getId());
            pp.changeSuccess();
        }

    }

    /**
     * Set up the submission button
     */
    public void submitBut(String tradeNum){
        pp.resetInputArea();
        if (checkInput(tradeNum)){
            pp.wrongInput();
        }else{
            currTrade = getCurrTrade(tradeNum);
            pp.presentTradeInfo(currTrade);
            pp.updateSuccess();
        }
    }

    /**
     * Set up the return/back button
     */
    public void backBut(){
        fr.setVisible(true);
        pp.closeFrame();
    }

    /**
     * Set up the update button
     */
    public void updateBut(){
        updateList();
        pp.updatePoint(um.getUser(currUser).getBonusPoints());
        noTradeSelected();
        //pp.resetCurr();
        updateStandard();
    }

    /**
     * Update the bonus points
     */
    public void updatePoint(){
        ClientUser user = um.getUser(currUser);
        pm.setUserPoints(user.getId());
        pp.updatePoint(pm.getUserPoints(user));
    }

    /**
     * Update the exchange standard for bonus trades
     */
    public void updateStandard(){
        pp.updateStandard(um.getExStandard());
    }

    /**
     * Indicate that there is no trade being selected
     */
    public void noTradeSelected(){
        pp.noTradeCurr();
    }

    /**
     * Set up the button for getting bonus trades
     */
    public void ebBut(){
        getBonus(um.getUser(currUser).getId(), currTrade);
        updateBut();
    }

    /**
     * Update the list of available trades
     */
    public void updateList(){
        pp.updateFrame(getTradesForExchange(um.getUser(currUser)));
    }

}
