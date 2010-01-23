package net.boklab.tools.client.place;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface HistoryManager {
    HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler);

    void fireCurrentHistoryState();

}
