package net.boklab.testing;

import java.util.ArrayList;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.DefaultRouter;

public class RouterTester extends DefaultRouter {

    private final ArrayList<Place> requests;
    private final ArrayList<Place> changed;

    public RouterTester(EventBus eventBus) {
	super(eventBus);
	this.requests = new ArrayList<Place>();
	this.changed = new ArrayList<Place>();
    }

    @Override
    public void fireChanged(Place place) {
	changed.add(place);
	super.fireChanged(place);
    }

    @Override
    public void fireRequest(Place place) {
	requests.add(place);
	super.fireRequest(place);
    }

    public Place getLastRequest() {
	int last = requests.size() - 1;
	Place place = last >= 0 ? requests.get(last) : null;
	return place;
    }

    @Override
    public void onRequest(String regex, PlaceRequestHandler handler) {
	super.onRequest(regex, handler);
    }

}
