package net.boklab.tools.client.place;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.inject.Singleton;

@Singleton
public class GWTHistoryManager implements HistoryManager {

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
	return History.addValueChangeHandler(handler);
    }

    @Override
    public void fireCurrentHistoryState() {
	History.fireCurrentHistoryState();
    }

}
