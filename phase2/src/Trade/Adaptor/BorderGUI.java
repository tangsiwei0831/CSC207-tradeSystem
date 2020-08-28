package Trade.Adaptor;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * [View]
 * GUI view
 * Use Border Layout
 * Has three text area -- message, list, curr. Can change text area's contents.
 * Has input areas. Available for getting inputs.
 */
public class BorderGUI implements GUIPlan, BorderLayoutGUI, iPresent, iInitializeInput, iInput {
    private JTextArea curr;
    private JTextArea list;
    private JTextArea msg;
    private HashMap<String, JTextArea> inputs = new HashMap<>();
    private JFrame fr;

    /**
     * set east panel
     * @param e east panel
     */
    public void setEast(JPanel e)
    {
        fr.getContentPane().add(BorderLayout.EAST, e);
    }

    /**
     * set north panel
     * @param n north panel
     */
    public void setNorth(JPanel n)
    {
        fr.getContentPane().add(BorderLayout.NORTH, n);
    }

    /**
     * set south panel
     * @param s south panel
     */
    public void setSouth(JPanel s)
    {
        fr.getContentPane().add(BorderLayout.SOUTH, s);
    }

    /**
     * set west panel
     * @param w west panel
     */
    public void setWest(JPanel w) { fr.getContentPane().add(BorderLayout.WEST, w); }

    /**
     * set center panel
     * @param c center panel
     */
    public void setCenter(JPanel c)
    {
        fr.getContentPane().add(BorderLayout.CENTER, c);
    }

    /**
     * set frame
     * @param width width of the frame
     * @param height height of the frame
     * @param name name of the frame
     */
    @Override
    public void setFrame(int width, int height, String name) {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        fr = frame;
    }

    /**
     * set the frame's visible to true
     */
    @Override
    public void run () {
        fr.setVisible(true);

    }

    /**
     * get the frame
     * @return frame
     */
    @Override
    public JFrame getFrame() {
        return fr;
    }

    /**
     * change list text area's text
     * @param str
     */
    @Override
    public void setListText(String str) {
        list.setText(str);
        list.setEditable(false);
    }

    /**
     * change curr text area's text
     * @param str: text that wants to change to
     */
    @Override
    public void setCurrText(String str) {
        curr.setText(str);
        curr.setEditable(false);
    }

    /**
     * change curr msg area's text
     * @param str: text that wants to change to
     */
    @Override
    public void setMsgText(String str) {
        msg.setText(str);
        msg.setEditable(false);
    }


    /**
     * initialize list text area
     * @param ta JTextArea that for lists
     */
    @Override
    public void initializeList(JTextArea ta) {
        list = ta;
        list.setEditable(false);
    }

    /**
     * initialize curr text area
     * @param ta JTextArea that for presenting current objects
     */
    @Override
    public void initializeCurr(JTextArea ta) {
        curr = ta;
        curr.setEditable(false);
    }

    /**
     * initialize msg text area
     * @param ta JTextArea that for presenting messages
     */
    @Override
    public void initializeMsg(JTextArea ta) {
        msg = ta;
        msg.setEditable(false);
    }

    /**
     * get input with keys
     * @param key the place to get input area
     * @return input strings
     */
    @Override
    public String getInput(String key) {
        return inputs.get(key).getText();
    }

    /**
     * reset input area
     * @param key the place to reset input area
     * @param content initial contents
     */
    @Override
    public void setInput(String key, String content) {
        inputs.get(key).setText(content);
    }

    /**
     * add an input area
     * @param key the searching string for the input area
     * @param input JTextArea that for inputs
     */
    @Override
    public void addInput(String key, JTextArea input) {
        inputs.put(key, input);
    }
}
