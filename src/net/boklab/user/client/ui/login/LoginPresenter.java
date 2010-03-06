package net.boklab.user.client.ui.login;

import net.boklab.core.client.session.Sessions;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class LoginPresenter extends AbstractPresenter<LoginDisplay> {

    @Inject
    public LoginPresenter(final Sessions sessions, final Provider<LoginDisplay> displayProvider) {
	super(displayProvider);

	final LoginDisplay display = getDisplay();
	display.getLogin().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		sessions.login("test@plataformabooka.net", "entrar");
	    }
	});

	display.getEmail().setText("test");
	display.getPassword().setText("test");
    }

}
