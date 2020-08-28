package Trade.Adaptor;

import javax.swing.*;

public interface iPresent {
    void setInput(String key, String contents);
    void setListText(String str);
    void setCurrText(String str);
    void setMsgText(String str);
    JFrame getFrame();
}
