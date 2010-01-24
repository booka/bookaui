package net.boklab.core.client.session;

import com.google.gwt.event.shared.EventHandler;

public interface LoggedOutHandler extends EventHandler {
    void onLogout(LoggedOutEvent event);

}
