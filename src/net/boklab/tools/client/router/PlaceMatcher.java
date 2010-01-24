package net.boklab.tools.client.router;

import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestHandler;

public class PlaceMatcher {
    private final String regex;
    public final PlaceRequestHandler handler;

    public PlaceMatcher(String regex, PlaceRequestHandler handler) {
	assert regex != null : "PlaceMatcher without regex!";
	this.regex = regex;
	this.handler = handler;
    }

    public boolean matches(Place place) {
	return place.placeId.matches(regex);
    }
}
