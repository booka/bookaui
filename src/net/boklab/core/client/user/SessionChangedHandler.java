package net.boklab.core.client.user;

import com.google.gwt.event.shared.EventHandler;

public interface SessionChangedHandler extends EventHandler {
    void onSessionChanged(SessionChangedEvent event);
}
