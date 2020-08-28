package Trade.Adaptor;

/**
 * controls the algorithm that generates the final product object.
 */
public class BorderGUIEngineer {
    private final BorderGUIBuilder tgBuilder;

    /**
     * [constructor]
     * @param tgb construction methods to build the GUI
     */
    public BorderGUIEngineer(BorderGUIBuilder tgb){
        tgBuilder = tgb;
    }


    /**
     * get GUI from builder
     * @return interface GUIPlan
     */
    public GUIPlan getGUI()
    {
        return tgBuilder.getTradeGUI();
    }

    /**
     * build the gui
     */
    public void constructGUI()
    {
        tgBuilder.buildFrame();
        tgBuilder.buildPanelN();
        tgBuilder.buildPanelE();
        tgBuilder.buildPanelW();
        tgBuilder.buildPanelS();
        tgBuilder.buildPanelC();

    }
}
