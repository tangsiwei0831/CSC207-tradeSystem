package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUIEngineer;
import Trade.Adaptor.GUIPlan;
import User.UseCase.UserManager;

import javax.swing.*;

/**
 * [Presenter]
 * inventoryGUI main. Connect to other Inventory GUIs.
 */
public class InventoryGUI {
    private final String currUser;
    private final JFrame ivf;

    /**
     * [Constructor]
     * @param currUser Current User name
     * @param frame Last frame
     */
    public InventoryGUI(String currUser, JFrame frame){
        this.currUser = currUser;
        this.ivf = frame;

    };

    /**
     * present the frame
     */
    public void run(){
        JFrame frame = new JFrame("Inventory Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200, 200);

        JPanel menu = new JPanel();
        JLabel label = new JLabel("    Hello, "+ currUser);
        JButton mk = new JButton("Market");
        JButton wb = new JButton("WishList - Borrow");
        JButton wl = new JButton("WishList - Lend");
        JButton ar = new JButton("Agree Request (Admin Only)");
        JButton back = new JButton("Return");

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(label);
        menu.add(mk);
        menu.add(wb);
        menu.add(wl);
        if (new UserManager().getUser(currUser).getIsAdmin()){
            menu.add(ar);
        }
        menu.add(back);

        frame.getContentPane().add(menu);
        frame.setVisible(true);

        mk.addActionListener(e -> {
            frame.setVisible(false);
            BorderGUIBuilder builder = new MarketBuilder(frame);
            BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
            engineer.constructGUI();
            GUIPlan tg = engineer.getGUI();
            tg.run();
        });

        wb.addActionListener(e -> {
            frame.setVisible(false);
            BorderGUIBuilder builder = new WishBorrowBuilder(currUser, frame);
            BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
            engineer.constructGUI();
            GUIPlan tg = engineer.getGUI();
            tg.run();
        });

        wl.addActionListener(e -> {
            frame.setVisible(false);
            BorderGUIBuilder builder = new WishLendBuilder(currUser, frame);
            BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
            engineer.constructGUI();
            GUIPlan tg = engineer.getGUI();
            tg.run();

        });

        ar.addActionListener(e -> {
            frame.setVisible(false);
            BorderGUIBuilder builder = new AgreeReqGUIBuilder(frame);
            BorderGUIEngineer engineer = new BorderGUIEngineer(builder);
            engineer.constructGUI();
            GUIPlan tg = engineer.getGUI();
            tg.run();
        });

        back.addActionListener(e -> {
            frame.setVisible(false);
            ivf.setVisible(true);
        });


    }
}
