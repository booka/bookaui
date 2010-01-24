package net.boklab.core.client.session;

import com.google.gwt.event.shared.GwtEvent;

public class LoggedOutEvent extends GwtEvent<LoggedOutHandler> {

    public static final Type<LoggedOutHandler> TYPE = new Type<LoggedOutHandler>();

    @Override
    public Type<LoggedOutHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(LoggedOutHandler handler) {
	handler.onLogout(this);
    }

}
