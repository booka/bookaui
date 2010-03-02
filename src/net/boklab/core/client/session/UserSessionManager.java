package net.boklab.core.client.session;

import net.boklab.core.client.I18nCore;
import net.boklab.core.client.model.UserSession;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.RestManager;
import net.boklab.workspace.client.event.UserMessageEvent;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserSessionManager implements Sessions {

    private final EventBus eventBus;
    private String authToken;
    private UserSession userSession;
    private final RestManager manager;

    @Inject
    public UserSessionManager(final EventBus eventBus, final RestManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;
    }

    @Override
    public void addLoginRequestHandler(final LoginRequestHandler handler) {
	eventBus.addHandler(LoginRequestEvent.TYPE, handler);
    }

    @Override
    public String getAuthToken() {
	return authToken;
    }

    @Override
    public String getUserId() {
	assert userSession != null : "Asked for user id wihout login";
	return userSession.getUserId();
    }

    @Override
    public String getUserName() {
	return userSession != null ? userSession.getUserName() : "";
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
    public void login(final String name, final String password) {
	final LoginRequestEvent event = new LoginRequestEvent(name, password);
	eventBus.fireEvent(event);
    }

    @Override
    public void logout() {
	authToken = null;
	userSession = null;
	manager.setAuthToken(authToken);
	eventBus.fireEvent(new LoggedOutEvent());
    }

    @Override
    public void onLoggedIn(final LoggedInHandler handler) {
	eventBus.addHandler(LoggedInEvent.TYPE, handler);
    }

    @Override
    public void setLoggedIn(final UserSession session) {
	authToken = session.getToken();
	userSession = session;
	manager.setAuthToken(authToken);
	eventBus.fireEvent(new LoggedInEvent(session));
	eventBus.fireEvent(new UserMessageEvent(I18nCore.t.loggedInMessage(session.getUserName())));
    }

}
