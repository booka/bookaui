package net.boklab.user.client;

import net.boklab.core.client.navigation.NavigationEvent;
import net.boklab.core.client.navigation.NavigationHandler;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AccountController {
    @Inject
    public AccountController(final Router router, final AccountWorkspace workspace,
	    final NavigationPresenter navigation) {
	final String accountResource = I18nUser.t.accountResource();

	navigation.addNavigationHandler(new NavigationHandler() {
	    @Override
	    public void onNavigation(final NavigationEvent event) {
		if (event.isNavigation(NavigationDisplay.ACCOUNT)) {
		    router.request(new Place(accountResource));
		}
	    }
	});

	router.onRequest(Paths.singletonResource(accountResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		navigation.setActiveIcon(NavigationDisplay.ACCOUNT);
		workspace.show();
	    }
	});

    }
}
