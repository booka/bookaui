package net.boklab.tools.client.eventbus;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Singleton;

@Singleton
public class LoggerEventBus extends HandlerManager implements EventBus {

    public LoggerEventBus() {
	super(null);
	GWT.log("Event bus logger", null);
    }

    @Override
    public void fireEvent(final GwtEvent<?> event) {
	Log.debug("*" + event.toDebugString());
	super.fireEvent(event);
    }
}
