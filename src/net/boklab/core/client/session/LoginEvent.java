package net.boklab.core.client.session;

import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginHandler> {
    public static final Type<LoginHandler> TYPE = new Type<LoginHandler>();
    private final String name;
    private final String password;

    public LoginEvent(String name, String password) {
	this.name = name;
	this.password = password;
    }

    @Override
    public Type<LoginHandler> getAssociatedType() {
	return TYPE;
    }

    public String getName() {
	return name;
    }

    public String getPassword() {
	return password;
    }

    @Override
    protected void dispatch(LoginHandler handler) {
	handler.onLogin(this);
    }

}
