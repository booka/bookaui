package net.boklab.core.client.user;

import com.google.gwt.event.shared.EventHandler;

public interface LoginHandler extends EventHandler {

    void onLogin(LoginEvent event);

}
