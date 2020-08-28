package Trade.Adaptor;

import javax.swing.*;

public interface iInitializeInput {
    void addInput(String key, JTextArea input);
    void initializeList(JTextArea ta);
    void initializeCurr(JTextArea ta);
    void initializeMsg(JTextArea ta);
}
