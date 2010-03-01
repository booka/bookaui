package net.boklab.user.client.ui.login;

import net.boklab.core.client.session.Sessions;
import net.boklab.tools.client.mvp.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginPresenter implements Presenter<LoginDisplay> {

    private final LoginDisplay display;

    @Inject
    public LoginPresenter(final Sessions sessions, final LoginDisplay display) {
	this.display = display;
	display.getLogin().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		sessions.login("Test", "secret");
	    }
	});

	display.getEmail().setText("test");
	display.getPassword().setText("test");
    }

    @Override
    public LoginDisplay getDisplay() {
	return display;
    }
}
