package net.boklab.tools.client.place;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * A wrapper for {@link History} GWT static class.
 * 
 * @see History
 * @author dani
 * 
 */
public interface HistoryManager {
    HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler);

    void fireCurrentHistoryState();

    void newItem(String token, boolean issueEvent);

}
