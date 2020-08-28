package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUI;

import javax.swing.*;

/**
 * build wish borrow GUI
 */
public class WishBorrowBuilder implements BorderGUIBuilder {
    iItemController ic;
    BorderGUI tg;

    /**
     * [constructor]
     * @param currUser current user name
     * @param fr last frame
     */
    public WishBorrowBuilder(String currUser, JFrame fr){
        tg = new BorderGUI();
        ic = new WishBorrowController(currUser, tg, fr);
    }

    /**
     * build frame
     */
    @Override
    public void buildFrame() {
        tg.setFrame(600, 200, "Edit WishBorrow Session");

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
        tg.initializeMsg(msgArea);
        tg.setNorth(panelN);
    }

    /**
     * build east panel
     */
    public void buildPanelE(){
        JPanel panelE = new JPanel();
        JButton add = new JButton("Add");
        JButton delete = new JButton("delete");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(add);
        panelE.add(delete);
        tg.setEast(panelE);

        delete.addActionListener(e -> ic.performActionTwo());
        add.addActionListener(e -> ic.performActionOne());
    }

    /**
     * build west panel
     */
    @Override
    public void buildPanelW() {
        JPanel panelW = new JPanel();
        JLabel wishList = new JLabel("Wish Borrow List");
        JTextArea wishArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(wishArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(wishList);
        panelW.add(jsp);
        tg.setWest(panelW);
        tg.initializeList(wishArea);
        ic.updateList();
    }

    /**
     * build south panel
     */
    @Override
    public void buildPanelS() {
        JPanel panelS = new JPanel();
        JLabel input = new JLabel("input item name");
        JTextArea inputArea = new JTextArea("item name");
        JButton submit = new JButton("submit");
        JButton back = new JButton("return");
        JButton update = new JButton("update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);
        tg.setSouth(panelS);
        tg.addInput("input", inputArea);
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
