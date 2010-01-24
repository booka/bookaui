package net.boklab.core.client.session;

import net.boklab.core.client.model.UserSession;

public interface Sessions {

    void setLoggedIn(UserSession userSession);

    String getAuthToken();

    int getUserId();

    UserSession getUserSession();

    boolean isLoggedIn();

    void login(String name, String password);

    void logout();

    void onLoggedIn(LoggedInHandler handler);

    void onLogin(LoginHandler handler);

}
