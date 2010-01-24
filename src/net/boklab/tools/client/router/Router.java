package net.boklab.tools.client.router;

import java.util.ArrayList;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceChangedEvent;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;

public class Router {

    private final EventBus eventBus;
    private final ArrayList<PlaceMatcher> matchers;

    @Inject
    public Router(EventBus eventBus) {
	this.eventBus = eventBus;
	this.matchers = new ArrayList<PlaceMatcher>();

	eventBus.addHandler(PlaceRequestEvent.TYPE, new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		Place place = event.getPlace();
		GWT.log("Place: " + place.placeId, null);
		for (PlaceMatcher matcher : matchers) {
		    if (matcher.matches(place)) {
			matcher.handler.onPlaceRequest(event);
		    }
		}
	    }
	});
    }

    public void fire(Place place) {
	eventBus.fireEvent(new PlaceChangedEvent(place));

    }

    public void when(final String name, final PlaceRequestHandler handler) {
	matchers.add(new PlaceMatcher(name, handler));
    }

}
