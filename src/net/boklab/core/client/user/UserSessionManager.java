package net.boklab.core.client.user;

import net.boklab.core.client.model.UserSession;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.RestManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserSessionManager {

    private final EventBus eventBus;
    private String authToken;
    private UserSession userSession;
    private final RestManager manager;

    @Inject
    public UserSessionManager(final EventBus eventBus, final RestManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;
    }

    public void addLoginHandler(final LoginHandler handler) {
	eventBus.addHandler(LoginEvent.TYPE, handler);
    }

    public void addLoginRequestHandler(final LoginRequestHandler handler) {
	eventBus.addHandler(LoginRequestEvent.getType(), handler);
    }

    public void addSessionChangedHandler(final SessionChangedHandler handler,
	    final boolean forceEvent) {
	eventBus.addHandler(SessionChangedEvent.TYPE, handler);
	if (forceEvent) {
	    handler.onSessionChanged(new SessionChangedEvent(userSession));
	}
    }

    public String getAuthToken() {
	return authToken;
    }

    public String getUserId() {
	assert userSession != null : "Asked for user id wihout login";
	return userSession.getUserId();
    }

    public String getUserName() {
	return userSession != null ? userSession.getUserName() : "";
    }

    public UserSession getUserSession() {
	return userSession;
    }

    public boolean isLoggedIn() {
	return userSession != null;
    }

    public void login(final String name, final String password) {
	final LoginEvent event = new LoginEvent(name, password);
	eventBus.fireEvent(event);
    }

    public void logout() {
	authToken = null;
	userSession = null;
	manager.setAuthToken(authToken);
	eventBus.fireEvent(new SessionChangedEvent(null));
    }

    public void requestLogin() {
	eventBus.fireEvent(new LoginRequestEvent());
    }

    public void setLoggedIn(final UserSession session) {
	authToken = session.getToken();
	userSession = session;
	manager.setAuthToken(authToken);
	eventBus.fireEvent(new SessionChangedEvent(session));
    }

}
