package net.boklab.tools.client.router;

import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestHandler;

public interface Router {

    void fireChanged(String description, Place place);

    void fireRequest(Place place);

    void onRequest(String regex, PlaceRequestHandler handler);

}
