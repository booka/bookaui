package net.boklab.user.client;

import net.boklab.user.client.ui.SessionPresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class SessionWorkspace {
    private final Provider<BookaAppPresenter> booka;
    private final Provider<SessionPresenter> user;

    @Inject
    public SessionWorkspace(final Provider<BookaAppPresenter> booka,
	    final Provider<SessionPresenter> user) {
	this.booka = booka;
	this.user = user;
    }

    public void showLogin() {
	user.get().setLogin();
	booka.get().setContent(user.get());
    }

    public void showLogout() {
	user.get().setLogout();
	booka.get().setContent(user.get());
    }

}
