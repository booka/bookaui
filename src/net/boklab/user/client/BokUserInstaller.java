package net.boklab.user.client;

import net.boklab.core.client.session.LoggedInEvent;
import net.boklab.core.client.session.LoggedInHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.user.client.ui.UserPresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class BokUserInstaller {
    private Place previous;

    @Inject
    public BokUserInstaller(final Router router, final PlaceManager places, final Sessions sessions,
	    final Provider<BookaAppPresenter> bookaProvider, final Provider<UserPresenter> userProvider) {

	router.onRequest("^/login$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		previous = places.getCurrentPlace();
		bookaProvider.get().setContent(userProvider.get());
		router.fireChanged(I18nUser.t.placeLogin(), event.getPlace());
	    }
	});

	sessions.onLoggedIn(new LoggedInHandler() {
	    @Override
	    public void onLoggedIn(final LoggedInEvent event) {
		if (previous != null) {
		    places.firePlaceRequest(new PlaceRequestEvent(previous));
		}
	    }
	});

    }
}
