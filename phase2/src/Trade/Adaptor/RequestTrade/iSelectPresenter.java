package Trade.Adaptor.RequestTrade;

import Trade.Adaptor.BorderGUI;

import java.util.List;

interface iSelectPresenter {

    void closeFrame();

    void updateFrame(List<String> il);

    void resetInputArea();

    void wrongInput();

    void selectItemInfo(String itemInfo);

    void updateSuccess();

    void notItemSelected();
}
