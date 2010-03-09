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
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;
import net.boklab.workspace.client.ui.signals.SignalsDisplay;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class SessionController {
    private Place previous;
    private final NavigationPresenter navigation;
    private final Sessions sessions;

    @Inject
    public SessionController(final Router router, final PlaceManager places,
	    final Sessions sessions, final NavigationPresenter navigation,
	    final Provider<SessionWorkspace> workspace) {

	this.sessions = sessions;
	this.navigation = navigation;
	final String loginResource = I18nUser.t.loginResource();
	navigation.registerResource(NavigationDisplay.LOGIN, loginResource);
	final String userResource = SignalsDisplay.USER;
	navigation.registerResource(userResource, userResource);
	navigation.setUserIconsVisible(sessions.isLoggedIn());

	router.onRequest(Paths.singletonResource(loginResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		previous = places.getCurrentPlace();
		workspace.get().showLogin();
		router.setCurrent(event.getPlace());
		navigation.setActiveIcon(NavigationDisplay.LOGIN);
	    }
	});

	router.onRequest(Paths.singletonResource(userResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		workspace.get().showLogout();
	    }
	});

	sessions.addSessionChangedHandler(new SessionChangedHandler() {
	    @Override
	    public void onSessionChanged(final SessionChangedEvent event) {
		setNavigation(sessions.isLoggedIn());
		if (previous != null) {
		    places.request(previous);
		}

	    }
	}, false);

	sessions.addLoginRequestHandler(new LoginRequestHandler() {
	    @Override
	    public void onLoginRequest(final LoginRequestEvent event) {
		places.request(new Place(loginResource));
	    }
	});

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