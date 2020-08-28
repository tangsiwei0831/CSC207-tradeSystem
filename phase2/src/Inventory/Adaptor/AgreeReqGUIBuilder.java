package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIBuilder;

import Trade.Adaptor.BorderGUI;

import javax.swing.*;

/**
 * build agreeReqGUI frame
 */
public class AgreeReqGUIBuilder implements BorderGUIBuilder {
    private iItemController ic;
    private BorderGUI tg;

    public AgreeReqGUIBuilder(JFrame fr){
        tg = new BorderGUI();
        ic = new AgreeReqController(tg, fr);
    }

    /**
     * build empty frame
     */
    public void buildFrame() {
        tg.setFrame(600, 200, "Agree Requests Session");

    }

    /**
     * build north message panel
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
     * build west panel
     */
    public void buildPanelW(){
        JPanel panelW = new JPanel();
        JLabel requestList = new JLabel("Request List");
        JTextArea requestArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(requestArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(requestList);
        panelW.add(jsp);
        tg.setWest(panelW);
        tg.initializeList(requestArea);
        tg.setListText(ic.printList());
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
     * get "view"
     * @return BorderGUI, the frame
     */
    @Override
    public BorderGUI getTradeGUI() {
        return tg;
    }

    /**
     * build east panel
     */
    public void buildPanelE(){
        JPanel panelE = new JPanel();
        JButton agree = new JButton("agree");
        JButton disagree = new JButton("disagree");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(agree);
        panelE.add(disagree);
        tg.setEast(panelE);

        agree.addActionListener(e -> ic.performActionOne());
        disagree.addActionListener(e -> ic.performActionTwo());
    }


}