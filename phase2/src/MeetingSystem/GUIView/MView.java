package MeetingSystem.GUIView;

import MeetingSystem.Adapter.MPresenter;

/**
 * The blueprint for MainView
 */
public interface MView {

    void setPresenter(MPresenter presenter);

    void updateViewFromModel(boolean isFirst);

    void open();
}
