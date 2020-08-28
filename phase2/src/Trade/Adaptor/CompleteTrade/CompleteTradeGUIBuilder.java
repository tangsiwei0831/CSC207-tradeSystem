package Trade.Adaptor.CompleteTrade;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUI;
import Trade.Adaptor.iTradeController;

import javax.swing.*;
import java.awt.*;

/**
 * build complete trade GUI
 */
public class CompleteTradeGUIBuilder implements BorderGUIBuilder {
    iTradeController ctc;
    BorderGUI tg;

    /**
     * [constructor]
     * @param currUser current user name
     * @param tf last frame
     */
    public CompleteTradeGUIBuilder(String currUser, JFrame tf) {
        tg = new BorderGUI();
        ctc = new CTradeController(currUser, tg, tf);
    }

    /**
     * build frame
     */
    @Override
    public void buildFrame(){
        tg.setFrame(800, 400, "Complete Trade Session");
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
    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JButton action = new JButton("Go to Meeting");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(action);
        action.addActionListener(e -> {
            ctc.performAction1();
        });
        tg.setEast(panelE);

    }

    /**
     * build west panel
     */
    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);
        panelW.setPreferredSize(new Dimension(380,370));
        tg.setWest(panelW);
        tg.initializeList(tradeArea);
        ctc.updateList();
    }

    /**
     * build south panel
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
            ctc.submitBut(tradeNum);
        });
        back.addActionListener(e -> ctc.backBut());
        update.addActionListener(e -> ctc.updateBut());
    }

    /**
     * build central panel
     */
    @Override
    public void buildPanelC() {
        JPanel panelC =  new JPanel();
        JLabel currTradeL = new JLabel("Trade Selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        ctc.noTradeSelected();

    }

    /**
     * get complete trade GUI
     * @return complete trade GUI
     */
    public BorderGUI getTradeGUI(){
        return tg;
    }
}


