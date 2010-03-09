package net.boklab.user.client.ui.logout;

import net.boklab.core.client.session.Sessions;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class LogoutPresenter extends AbstractPresenter<LogoutDisplay> {

    private final Sessions sessions;

    @Inject
    public LogoutPresenter(final Sessions sessions, final Provider<LogoutDisplay> provider) {
	super(provider);
	this.sessions = sessions;
    }

    @Override
    protected void attach(final LogoutDisplay display) {
	display.getLogoutAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		sessions.logout();
	    }
	});
    }

}
