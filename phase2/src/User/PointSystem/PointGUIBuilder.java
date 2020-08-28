package User.PointSystem;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUI;
import javax.swing.*;
import java.awt.*;

public class PointGUIBuilder implements BorderGUIBuilder {

    iPointController aa;
    BorderGUI tg;

    /**
     * Constructs the PointGUIBuilder
     * @param currUser the current acting user
     * @param tf frame
     */
    public PointGUIBuilder(String currUser, JFrame tf){
        tg = new BorderGUI();
        aa = new AwardActivities(currUser, tf, tg);
    }

    /**
     * Set up the panel for redeeming bonus points
     */
    @Override
    public void buildFrame() {
        tg.setFrame(800, 400, "Redeem Point Session");
    }

    /**
     * Set up the panel
     */
    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("Message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        msgArea.setEditable(false);
        panelN.add(msg);
        panelN.add(msgArea);
        tg.initializeMsg(msgArea);
        tg.setNorth(panelN);
    }

    /**
     * Set up the panel for presenting points on the right hand side
     */
    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JLabel p = new JLabel("Points");
        JTextArea points = new JTextArea();
        points.setEditable(false);
        JLabel ex = new JLabel("Exchange Standard");
        JTextArea exStandard = new JTextArea();
        exStandard.setEditable(false);
        points.setPreferredSize(new Dimension(50, 50));
        JButton eb = new JButton("Get Bonus");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(p);
        panelE.add(points);
        panelE.add(ex);
        panelE.add(exStandard);
        panelE.add(eb);
        eb.addActionListener(e -> aa.ebBut());
        tg.addInput("Points", points);
        tg.addInput("Exchange Standard", exStandard);
        aa.updatePoint();
        aa.updateStandard();
        tg.setEast(panelE);

    }

    /**
     * Set up the panel for presenting available trades
     */
    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        tradeArea.setEditable(false);
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);
        panelW.setPreferredSize(new Dimension(380,370));
        tg.setWest(panelW);
        tg.initializeList(tradeArea);
        aa.updateList();
    }

    /**
     * Set up the panel for inputs at the bottom
     */
    @Override
    public void buildPanelS() {
        JPanel panelS =  new JPanel();
        JLabel input = new JLabel("Input Trade Number");
        JTextArea inputArea = new JTextArea("Trade Number", 1, 10);
        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");
        JButton update = new JButton("Update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);
        tg.setSouth(panelS);
        tg.addInput("input", inputArea);
        tg.setInput("input", "Trade Number");

        submit.addActionListener(e -> {
            String tradeNum = tg.getInput("input");
            System.out.println(tradeNum);
            aa.submitBut(tradeNum);
        });
        back.addActionListener(e -> aa.backBut());
        update.addActionListener(e -> aa.updateBut());
    }


    /**
     * Set up the panel for presenting selected trades
     */
    @Override
    public void buildPanelC() {
        JPanel panelC =  new JPanel();
        JLabel currTradeL = new JLabel("Trade Selected");
        JTextArea currArea = new JTextArea();
        currArea.setEditable(false);
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        aa.noTradeSelected();

    }

    /**
     * Get the TradeGUI
     */
    @Override
    public BorderGUI getTradeGUI() {
        return tg;
    }
}
