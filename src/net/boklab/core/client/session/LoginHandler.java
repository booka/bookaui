package net.boklab.core.client.session;

import com.google.gwt.event.shared.EventHandler;

public interface LoginHandler extends EventHandler {

    void onLogin(LoginEvent event);

}
