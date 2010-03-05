package net.boklab.places.client;

import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IndicesController {
    @Inject
    public IndicesController(final Router router, final IndicesWorkspace workspace,
	    final NavigationPresenter navigation, final PlaceManager places) {

	final String accountResource = I18nPlaces.t.resourceBrowse();
	navigation.setResource(NavigationDisplay.BOOKA, accountResource);

	router.onRequest(Paths.singletonResource(accountResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		navigation.setActive(NavigationDisplay.BOOKA);
		workspace.show();
		router.setCurrent("Explorar", event.getPlace());
	    }
	});
    }
}
