package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUI;

import javax.swing.*;

/**
 * build market frame
 */
public class MarketBuilder implements BorderGUIBuilder {

    iItemController ic;
    BorderGUI tg;

    /**
     * [Constructor]
     * initialize iItemController
     * @param fr: last frame
     */
    public MarketBuilder(JFrame fr) {
        tg = new BorderGUI();
        ic = new MarketController(tg, fr);
    }

    /**
     * build frame
     */
    @Override
    public void buildFrame() {
        tg.setFrame(600, 200, "Market Session");
    }

    /**
     * build north panel
     */
    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
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
        JTextArea ta = new JTextArea("Type item name here", 1, 10);
        JButton submit = new JButton("Submit");
        JButton back  = new JButton("Return");
        JButton update = new JButton("update");
        panelS.add(ta);
        panelS.add(submit);
        panelS.add(back);
        tg.setSouth(panelS);
        tg.addInput("input", ta);
        submit.addActionListener(e -> ic.submitBut());
        back.addActionListener(e -> ic.backBut());
        update.addActionListener(e -> ic.updateBut());


    }

    /**
     * build central panel
     */
    @Override
    public void buildPanelC() {
        JPanel panelC = new JPanel();
        JLabel currTradeL = new JLabel("Item Selected");
        JTextArea currArea = new JTextArea();
        currArea.setEditable(false);

        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        currArea.setText("no item selected");
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        ic.updateCurr();


    }

    /**
     * get frame
     * @return Market BorderGUI
     */
    @Override
    public BorderGUI getTradeGUI() {
        return tg;
    }
}
