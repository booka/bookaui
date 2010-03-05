package net.boklab.user.client;

import net.boklab.core.client.session.LoginRequestEvent;
import net.boklab.core.client.session.LoginRequestHandler;
import net.boklab.core.client.session.SessionChangedEvent;
import net.boklab.core.client.session.SessionChangedHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.user.client.ui.UserPresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class LoginController {
    private Place previous;
    private final NavigationPresenter navigation;
    private final Sessions sessions;

    @Inject
    public LoginController(final Router router, final PlaceManager places, final Sessions sessions,
	    final NavigationPresenter navigation, final Provider<BookaAppPresenter> bookaProvider,
	    final Provider<UserPresenter> userProvider) {

	this.sessions = sessions;
	this.navigation = navigation;
	final String loginResource = I18nUser.t.loginResource();
	navigation.setResource(NavigationDisplay.LOGIN, loginResource);
	navigation.setUserIconsVisible(sessions.isLoggedIn());

	router.onRequest(Paths.singletonResource(loginResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		previous = places.getCurrentPlace();
		bookaProvider.get().setContent(userProvider.get());
		router.setCurrent(I18nUser.t.placeLogin(), event.getPlace());
		navigation.setActive(NavigationDisplay.LOGIN);
	    }
	});

	sessions.addLoginRequestHandler(new LoginRequestHandler() {
	    @Override
	    public void onLogin(final LoginRequestEvent event) {
		navigation.setUser("...", false);
	    }
	});

	sessions.addSessionChangedHandler(new SessionChangedHandler() {
	    @Override
	    public void onSessionChanged(final SessionChangedEvent event) {
		setNavigation(sessions.isLoggedIn());
		if (previous != null) {
		    places.firePlaceRequest(new PlaceRequestEvent(previous));
		}

	    }
	}, false);

    }

    protected void setNavigation(final boolean loggedIn) {
	navigation.setUserIconsVisible(loggedIn);
	if (loggedIn) {
	    navigation.setUser(sessions.getUserName(), true);
	} else {
	    navigation.setUser(I18nUser.t.anonymous(), false);
	}

    }
}
