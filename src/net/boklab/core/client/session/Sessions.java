package net.boklab.core.client.session;

import net.boklab.core.client.model.UserSession;

public interface Sessions {

    void addLoginHandler(LoginHandler handler);

    void addLoginRequestHandler(LoginRequestHandler loginRequestHandler);

    void addSessionChangedHandler(SessionChangedHandler handler, boolean fireIfLoggedIn);

    String getAuthToken();

    String getUserId();

    String getUserName();

    UserSession getUserSession();

    boolean isLoggedIn();

    void login(String name, String password);

    void logout();

    void requestLogin();

    void setLoggedIn(UserSession userSession);

}
