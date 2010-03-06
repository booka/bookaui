package net.boklab.tools.client.router;

import java.util.ArrayList;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceChangedEvent;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;

import com.google.gwt.core.client.GWT;
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

	eventBus.addHandler(PlaceRequestEvent.getType(), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final Place place = event.getPlace();
		for (final PlaceMatcher matcher : matchers) {
		    if (matcher.matches(place)) {
			GWT.log("Handler for : " + place.toDebugString());
			matcher.handler.onPlaceRequest(event);
		    }
		}
	    }
	});
    }

    @Override
    public void fireRequest(final Place place) {
	eventBus.fireEvent(new PlaceRequestEvent(place));
    }

    @Override
    public void onRequest(final Path path, final PlaceRequestHandler handler) {
	matchers.add(new PlaceMatcher(path.regex, handler));
    }

    @Override
    public void setCurrent(final String description, final Place place) {
	eventBus.fireEvent(new PlaceChangedEvent(description, place));
    }

}
