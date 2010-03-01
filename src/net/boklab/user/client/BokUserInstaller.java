package net.boklab.user.client;

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

    @Inject
    public BokUserInstaller(final Router router, final Provider<BookaAppPresenter> bookaProvider,
	    final Provider<UserPresenter> userProvider) {

	router.onRequest("^/login$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		bookaProvider.get().setContent(userProvider.get());
		router.fireChanged(I18nUser.t.placeLogin(), event.getPlace());
	    }
	});
    }
}
