package net.boklab.core.client.user;

import net.boklab.core.client.model.UserSession;

import com.google.gwt.event.shared.GwtEvent;

public class SessionChangedEvent extends GwtEvent<SessionChangedHandler> {

    public static final Type<SessionChangedHandler> TYPE = new Type<SessionChangedHandler>();
    private final UserSession userSession;

    public SessionChangedEvent(UserSession userSession) {
	this.userSession = userSession;
    }

    @Override
    public GwtEvent.Type<SessionChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public UserSession getUserSession() {
	return userSession;
    }

    @Override
    protected void dispatch(SessionChangedHandler handler) {
	handler.onSessionChanged(this);
    }

}
