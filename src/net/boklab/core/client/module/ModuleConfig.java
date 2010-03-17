package net.boklab.core.client.module;

import net.boklab.core.client.navigation.NavigationHandler;
import net.boklab.core.client.navigation.NavigationManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ModuleConfig {

    private final Router router;
    private final NavigationManager navigations;

    @Inject
    public ModuleConfig(final Router router, final NavigationManager navigations) {
	this.router = router;
	this.navigations = navigations;
    }

    public void addLocation(final Location location) {
	router.onRequest(location.getPath(), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final Place place = event.getPlace();
		router.setCurrent(place);
		location.open(place);
	    }
	});
    }

    public void addNavigationHandler(final String navigationToken, final NavigationHandler navigator) {
	navigations.addNavigationHandler(navigationToken, navigator);
    }

}
