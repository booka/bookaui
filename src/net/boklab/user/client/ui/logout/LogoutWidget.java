package net.boklab.user.client.ui.logout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class LogoutWidget extends Composite implements LogoutDisplay {

    interface LogoutWidgetUiBinder extends UiBinder<Widget, LogoutWidget> {
    }

    private static LogoutWidgetUiBinder uiBinder = GWT.create(LogoutWidgetUiBinder.class);

    @UiField
    Anchor logout;
    @UiField
    Label user;

    public LogoutWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getLogoutAction() {
	return logout;
    }

    @Override
    public Label getUser() {
	return user;
    }

}
