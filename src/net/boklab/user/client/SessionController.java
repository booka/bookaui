package net.boklab.user.client;

import net.boklab.core.client.navigation.NavigationEvent;
import net.boklab.core.client.navigation.NavigationHandler;
import net.boklab.core.client.user.LoginRequestEvent;
import net.boklab.core.client.user.LoginRequestHandler;
import net.boklab.core.client.user.SessionChangedEvent;
import net.boklab.core.client.user.SessionChangedHandler;
import net.boklab.core.client.user.UserSessionManager;
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
    private final UserSessionManager sessions;

    @Inject
    public SessionController(final Router router, final PlaceManager places,
	    final UserSessionManager sessions, final NavigationPresenter navigation,
	    final Provider<SessionWorkspace> workspace) {

	this.sessions = sessions;
	this.navigation = navigation;
	final String loginResource = I18nUser.t.loginResource();
	final String userResource = SignalsDisplay.USER;
	navigation.setUserIconsVisible(sessions.isLoggedIn());

	navigation.addNavigationHandler(new NavigationHandler() {
	    @Override
	    public void onNavigation(final NavigationEvent event) {
		if (event.isNavigation(NavigationDisplay.LOGIN)) {
		    router.request(new Place(loginResource));
		} else if (event.isNavigation(SignalsDisplay.USER)) {
		    router.request(new Place(userResource));
		}
	    }
	});

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
	    navigation.setUser(sessions.getUserSession().getUserName(), true);
	} else {
	    navigation.setUser(I18nUser.t.anonymous(), false);
	}

    }
}
