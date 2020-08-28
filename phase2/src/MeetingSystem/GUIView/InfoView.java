package MeetingSystem.GUIView;

import MeetingSystem.Adapter.IPresenter;

/**
 * The blueprint for InputTimePlaceView (SetupView, EditView), OKCancelView (AgreeView, ConfirmView), HelpView
 */
public interface InfoView {

    void setPresenter(IPresenter presenter);

    void open();
}
