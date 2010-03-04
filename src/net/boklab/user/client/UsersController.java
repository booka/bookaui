package net.boklab.user.client;

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
public class UsersController {
    private Place previous;

    @Inject
    public UsersController(final Router router, final PlaceManager places, final Sessions sessions,
	    final NavigationPresenter navigation, final Provider<BookaAppPresenter> bookaProvider,
	    final Provider<UserPresenter> userProvider) {

	final String loginResource = I18nUser.t.loginResource();
	navigation.setResource(NavigationDisplay.LOGIN, loginResource);

	router.onRequest(Paths.singletonResource(loginResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		previous = places.getCurrentPlace();
		bookaProvider.get().setContent(userProvider.get());
		router.fireChanged(I18nUser.t.placeLogin(), event.getPlace());
	    }
	});

	sessions.addSessionChangedHandler(new SessionChangedHandler() {
	    @Override
	    public void onSessionChanged(final SessionChangedEvent event) {
		if (previous != null) {
		    places.firePlaceRequest(new PlaceRequestEvent(previous));
		}
	    }
	}, false);

    }
}
