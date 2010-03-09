package net.boklab.core.client.session;

import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginHandler> {
    public static final Type<LoginHandler> TYPE = new Type<LoginHandler>();
    private final String email;
    private final String password;

    public LoginEvent(final String email, final String password) {
	this.email = email;
	this.password = password;
    }

    @Override
    public Type<LoginHandler> getAssociatedType() {
	return TYPE;
    }

    public String getName() {
	return email;
    }

    public String getPassword() {
	return password;
    }

    @Override
    protected void dispatch(final LoginHandler handler) {
	handler.onLogin(this);
    }

}
