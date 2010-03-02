package net.boklab.core.client.session;

import net.boklab.core.client.model.UserSession;

public interface Sessions {

    void addLoginRequestHandler(LoginRequestHandler handler);

    String getAuthToken();

    String getUserId();

    String getUserName();

    UserSession getUserSession();

    boolean isLoggedIn();

    void login(String name, String password);

    void logout();

    void onLoggedIn(LoggedInHandler handler);

    void setLoggedIn(UserSession userSession);

}
