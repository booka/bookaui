package net.boklab.core.client.session;

import com.google.gwt.event.shared.EventHandler;

public interface LoginRequestHandler extends EventHandler {

    void onLogin(LoginRequestEvent event);

}
