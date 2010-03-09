package net.boklab.core.client.session;

import com.google.gwt.event.shared.GwtEvent;

public class LoginRequestEvent extends GwtEvent<LoginRequestHandler> {

    private static final Type<LoginRequestHandler> TYPE = new Type<LoginRequestHandler>();

    public static Type<LoginRequestHandler> getType() {
	return TYPE;
    }

    @Override
    public Type<LoginRequestHandler> getAssociatedType() {
	return getType();
    }

    @Override
    protected void dispatch(final LoginRequestHandler handler) {
	handler.onLoginRequest(this);
    }

}
