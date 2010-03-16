package net.boklab.core.client.user;

import com.google.gwt.event.shared.EventHandler;

public interface LoginRequestHandler extends EventHandler {

    void onLoginRequest(LoginRequestEvent event);

}
