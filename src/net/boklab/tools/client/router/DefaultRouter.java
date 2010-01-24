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
    public DefaultRouter(EventBus eventBus) {
	this.eventBus = eventBus;
	this.matchers = new ArrayList<PlaceMatcher>();

	GWT.log("ROUTER!", null);

	eventBus.addHandler(PlaceRequestEvent.TYPE, new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		Place place = event.getPlace();
		GWT.log("PLACE REQUEST: " + place.placeId, null);
		for (PlaceMatcher matcher : matchers) {
		    if (matcher.matches(place)) {
			GWT.log("Matcher for place: " + place.placeId, null);
			matcher.handler.onPlaceRequest(event);
		    }
		}
	    }
	});
    }

    @Override
    public void fireChanged(Place place) {
	eventBus.fireEvent(new PlaceChangedEvent(place));
    }

    @Override
    public void fireRequest(Place place) {
	eventBus.fireEvent(new PlaceRequestEvent(place));
    }

    @Override
    public void onRequest(final String name, final PlaceRequestHandler handler) {
	GWT.log("New matcher for place: " + name, null);
	matchers.add(new PlaceMatcher(name, handler));
    }

}
