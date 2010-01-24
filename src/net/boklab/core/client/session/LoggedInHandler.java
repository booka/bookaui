package net.boklab.core.client.session;

import com.google.gwt.event.shared.EventHandler;

public interface LoggedInHandler extends EventHandler {
    void onLoggedIn(LoggedInEvent event);
}
