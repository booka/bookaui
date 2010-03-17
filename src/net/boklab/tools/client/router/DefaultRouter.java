package net.boklab.tools.client.router;

import java.util.ArrayList;

import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultRouter implements Router {

    private final ArrayList<PlaceMatcher> matchers;
    private final PlaceManager places;

    @Inject
    public DefaultRouter(final PlaceManager places) {
	this.places = places;
	matchers = new ArrayList<PlaceMatcher>();

	places.addPlaceRequestHandler(new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final Place place = event.getPlace();
		for (final PlaceMatcher matcher : matchers) {
		    if (matcher.matches(place)) {
			matcher.handler.onPlaceRequest(event);
		    }
		}
	    }
	});

    }

    @Override
    public boolean currentIs(final String resource) {
	return resource.equals(places.getCurrentPlace().resource);
    }

    @Override
    public void onRequest(final Path path, final PlaceRequestHandler handler) {
	matchers.add(new PlaceMatcher(path.regex, handler));
    }

    @Override
    public void request(final Place place) {
	places.request(place);
    }

    @Override
    public void setCurrent(final Place place) {
	places.setCurrent(place);
    }

}
