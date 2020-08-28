package Trade.Adaptor;

public interface BorderGUIBuilder {
    void buildFrame();
    void buildPanelN();
    void buildPanelE();
    void buildPanelW();
    void buildPanelS();
    void buildPanelC();
    BorderGUI getTradeGUI();
}
