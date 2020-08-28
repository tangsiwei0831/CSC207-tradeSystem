package Trade.Adaptor.RequestTrade;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.UUID;

public class RTradeGUIBuilder implements BorderGUIBuilder {


    private final iRequestTradeController trc;
    private final BorderGUI tg;

    /**
     * [constructor]
     * @param currUser current user
     * @param item item selected by the current user
     * @param fr  last frame
     */
    public RTradeGUIBuilder(UUID currUser, String item, JFrame fr) {


        tg = new BorderGUI();
        trc = new RTradeController(currUser, tg, item, fr);
        trc.getTarUser(item);
    }

    /**
     * build frame
     */
    @Override
    public void buildFrame() {
        tg.setFrame(800, 200, "Request Trade Session");
    }

    /**
     * build north panel
     */
    @Override
    public void buildPanelN() {
        JPanel msg = new JPanel();
        JLabel msgl = new JLabel("Message:", SwingConstants.LEFT);
        msg.add(msgl);
        JTextArea jtz = new JTextArea();
        msg.add(jtz);
        tg.setNorth(msg);
        tg.initializeMsg(jtz);
    }

    /**
     * build east panel
     */
    @Override
    public void buildPanelE() {
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Input second item name here " +
                "for a two way trade", SwingConstants.LEFT);
        JTextArea ta = new JTextArea("Type item name here");
        panelRight.add(label);
        panelRight.add(ta);
        tg.setEast(panelRight);
        tg.addInput("input", ta);
    }

    /**
     * build west panel
     */
    @Override
    public void buildPanelW() {
    }

    /**
     * build south panel
     */
    @Override
    public void buildPanelS() {
        JPanel panelS =  new JPanel();
        JButton onewayTemp = new JButton("One way-Temporary");
        JButton onewayPer = new JButton("One way-Permanent");
        JButton twowayTemp = new JButton("Two way-Temporary");
        JButton twowayPer = new JButton("Two way-Permanent");
        JButton back = new JButton("Back");
        panelS.add(onewayTemp);
        panelS.add(onewayPer);
        panelS.add(twowayTemp);
        panelS.add(twowayPer);
        panelS.add(back);
        tg.setSouth(panelS);
        onewayTemp.addActionListener(e -> {trc.onewayBut("temp");});
        onewayPer.addActionListener(e -> {trc.onewayBut("per");});
        twowayTemp.addActionListener(e -> {trc.twowayBut("temp"); });
        twowayPer.addActionListener(e -> { trc.twowayBut("per"); });
        back.addActionListener(e -> trc.backBut());

    }

    /**
     * build central panel
     */
    @Override
    public void buildPanelC() {
        JTextArea tradeInfo = new JTextArea();
        JPanel panel = new JPanel();
        panel.add(tradeInfo);
        tradeInfo.setPreferredSize(new Dimension(400, 170));
        tg.setCenter(panel);
        tg.initializeCurr(tradeInfo);
        trc.presentTradeInfo();
    }

    /**
     * get trade GUI
     * @return trade GUI
     */
    @Override
    public BorderGUI getTradeGUI() {
        return tg;
    }
}
