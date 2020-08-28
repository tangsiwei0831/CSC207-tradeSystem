package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUI;

import javax.swing.*;

/**
 * construct WishBorrowAdd GUI
 */
public class WishBorrowAddBuilder implements BorderGUIBuilder {
    iItemController ic;
    BorderGUI tg;

    /**
     * [constructor]
     * @param currUser current user's name
     * @param fr last frame
     */
    public WishBorrowAddBuilder(String currUser, JFrame fr) {
        tg = new BorderGUI();
        ic = new WishBorrowAddController(currUser, tg, fr);
    }

    /**
     * build frame
     */
    @Override
    public void buildFrame() {
        tg.setFrame(600, 200, "Add to Borrow Session");
    }

    /**
     * build north panel
     */
    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("message: ", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);
        tg.setNorth(panelN);
        tg.initializeMsg(msgArea);
    }

    /**
     * build east panel
     */
    @Override
    public void buildPanelE() {
    }

    /**
     * build west panel
     */
    @Override
    public void buildPanelW() {
        JPanel panelW = new JPanel();
        JLabel il = new JLabel("available item list");
        JTextArea itemList = new JTextArea();
        itemList.setText(ic.printList());
        JScrollPane sp = new JScrollPane(itemList);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(il);
        panelW.add(sp);
        tg.setWest(panelW);
        tg.initializeList(itemList);
        ic.updateList();
    }

    /**
     * build south panel
     */
    @Override
    public void buildPanelS() {
        JPanel panelS = new JPanel();
        JTextArea ta = new JTextArea("type item name here");
        JButton submit = new JButton("submit");
        JButton awl = new JButton("add to wish list");
        JButton back  = new JButton("return");
        panelS.add(ta);
        panelS.add(submit);
        panelS.add(awl);
        panelS.add(back);
        tg.setSouth(panelS);
        tg.addInput("input", ta);

        submit.addActionListener(e -> ic.submitBut());
        back.addActionListener(e -> ic.backBut());
        awl.addActionListener(e -> ic.performActionOne());

    }

    /**
     * build central panel
     */
    @Override
    public void buildPanelC() {
        JPanel panelC = new JPanel();
        JLabel currTradeL = new JLabel("item selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        currArea.setText("no item selected");
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        ic.updateCurr();
    }

    /**
     * get BorderGUI
     * @return BorderGUI
     */
    @Override
    public BorderGUI getTradeGUI() {
        return tg;
    }
}

