package net.boklab.core.client.session;

import net.boklab.core.client.model.UserSession;

import com.google.gwt.event.shared.GwtEvent;

public class LoggedInEvent extends GwtEvent<LoggedInHandler> {

    public static final Type<LoggedInHandler> TYPE = new Type<LoggedInHandler>();
    private final UserSession userSession;

    public LoggedInEvent(UserSession userSession) {
	this.userSession = userSession;
    }

    @Override
    public GwtEvent.Type<LoggedInHandler> getAssociatedType() {
	return TYPE;
    }

    public UserSession getUserSession() {
	return userSession;
    }

    @Override
    protected void dispatch(LoggedInHandler handler) {
	handler.onLoggedIn(this);
    }

}
