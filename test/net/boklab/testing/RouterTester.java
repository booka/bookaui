package net.boklab.testing;

import java.util.ArrayList;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.DefaultRouter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RouterTester extends DefaultRouter {

    private final ArrayList<Place> requests;
    private final ArrayList<Place> changed;
    private Place lastRequest;
    private Place lastChanged;

    @Inject
    public RouterTester(final EventBus eventBus) {
	super(eventBus);
	requests = new ArrayList<Place>();
	changed = new ArrayList<Place>();
    }

    @Override
    public void setCurrent(final String description, final Place place) {
	lastChanged = place;
	changed.add(place);
	super.setCurrent(description, place);
    }

    @Override
    public void fireRequest(final Place place) {
	lastRequest = place;
	requests.add(place);
	super.fireRequest(place);
    }

    public Place getLastChanged() {
	return lastChanged;
    }

    public Place getLastRequest() {
	return lastRequest;
    }

    @Override
    public void onRequest(final Path path, final PlaceRequestHandler handler) {
	super.onRequest(path, handler);
    }

}
