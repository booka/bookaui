package net.boklab.core.client.session;

import com.google.gwt.event.shared.GwtEvent;

public class LoginRequestEvent extends GwtEvent<LoginRequestHandler> {
    public static final Type<LoginRequestHandler> TYPE = new Type<LoginRequestHandler>();
    private final String email;
    private final String password;

    public LoginRequestEvent(final String email, final String password) {
	this.email = email;
	this.password = password;
    }

    @Override
    public Type<LoginRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public String getName() {
	return email;
    }

    public String getPassword() {
	return password;
    }

    @Override
    protected void dispatch(final LoginRequestHandler handler) {
	handler.onLogin(this);
    }

}
