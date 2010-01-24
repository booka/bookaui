package net.boklab.core.client.session;

import net.boklab.core.client.model.UserSession;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.RestManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class SessionsBridge implements Sessions {

    private final EventBus eventBus;
    private String authToken;
    private UserSession userSession;
    private final RestManager manager;

    @Inject
    public SessionsBridge(EventBus eventBus, RestManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;
    }

    @Override
    public String getAuthToken() {
	return authToken;
    }

    @Override
    public int getUserId() {
	assert userSession != null : "Asked for user id wihout login";
	return userSession.getUserId();
    }

    @Override
    public UserSession getUserSession() {
	return userSession;
    }

    @Override
    public boolean isLoggedIn() {
	return userSession != null;
    }

    @Override
    public void login(String name, String password) {
	LoginEvent event = new LoginEvent(name, password);
	eventBus.fireEvent(event);
    }

    @Override
    public void logout() {
	this.authToken = null;
	this.userSession = null;
	manager.setAuthToken(authToken);
	eventBus.fireEvent(new LoggedOutEvent());
    }

    @Override
    public void onLoggedIn(LoggedInHandler handler) {
	eventBus.addHandler(LoggedInEvent.TYPE, handler);
    }

    @Override
    public void onLogin(LoginHandler handler) {
	eventBus.addHandler(LoginEvent.TYPE, handler);
    }

    @Override
    public void setLoggedIn(UserSession session) {
	authToken = session.getToken();
	userSession = session;
	manager.setAuthToken(authToken);
	eventBus.fireEvent(new LoggedInEvent(session));
    }

}
