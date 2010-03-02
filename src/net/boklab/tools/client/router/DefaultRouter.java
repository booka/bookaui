package net.boklab.tools.client.router;

import java.util.ArrayList;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceChangedEvent;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultRouter implements Router {

    private final EventBus eventBus;
    private final ArrayList<PlaceMatcher> matchers;

    @Inject
    public DefaultRouter(final EventBus eventBus) {
	this.eventBus = eventBus;
	matchers = new ArrayList<PlaceMatcher>();

	eventBus.addHandler(PlaceRequestEvent.TYPE, new PlaceRequestHandler() {
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
    public void fireChanged(final String description, final Place place) {
	eventBus.fireEvent(new PlaceChangedEvent(description, place));
    }

    @Override
    public void fireRequest(final Place place) {
	eventBus.fireEvent(new PlaceRequestEvent(place));
    }

    @Override
    public void onRequest(final String name, final PlaceRequestHandler handler) {
	matchers.add(new PlaceMatcher(name, handler));
    }

}
