package net.boklab.user.client.ui.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends Composite implements LoginDisplay {

    interface LoginWidgetUiBinder extends UiBinder<Widget, LoginWidget> {
    }

    private static LoginWidgetUiBinder uiBinder = GWT.create(LoginWidgetUiBinder.class);

    @UiField
    TextBox email, password;
    @UiField
    Anchor login;

    public LoginWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getEmail() {
	return email;
    }

    @Override
    public HasClickHandlers getLogin() {
	return login;
    }

    @Override
    public HasText getPassword() {
	return password;
    }

}
