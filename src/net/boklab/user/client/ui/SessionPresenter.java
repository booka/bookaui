package net.boklab.user.client.ui;

import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Presenter;
import net.boklab.user.client.ui.login.LoginPresenter;
import net.boklab.user.client.ui.logout.LogoutPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class SessionPresenter extends AbstractPresenter<SessionDisplay> {

    private final LoginPresenter login;
    private Presenter<?> current;
    private final LogoutPresenter logout;

    @Inject
    public SessionPresenter(final LoginPresenter login, final LogoutPresenter logout,
	    final Provider<SessionDisplay> provider) {
	super(provider);
	this.login = login;
	this.logout = logout;
	setLogin();
    }

    public void setLogin() {
	setPresenter(login);
    }

    public void setLogout() {
	setPresenter(logout);
    }

    private void setPresenter(final Presenter<?> presenter) {
	final SessionDisplay display = getDisplay();
	if (current != null) {
	    display.removeDisplay(current.getDisplay());
	}
	current = presenter;
	display.addDisplay(current.getDisplay());
    }

}
