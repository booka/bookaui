package net.boklab.tools.client.router;

import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestHandler;

public class PlaceMatcher {
    private final String regex;
    public final PlaceRequestHandler handler;

    public PlaceMatcher(final String regex, final PlaceRequestHandler handler) {
	assert regex != null : "PlaceMatcher without regex!";
	this.regex = regex;
	this.handler = handler;
    }

    public boolean matches(final Place place) {
	return place.placeToken.matches(regex);
    }
}
