package net.boklab.tools.client.place;

import com.google.inject.Inject;

public class RestPlaceTokenizer implements PlaceTokenizer {

    @Inject
    public RestPlaceTokenizer() {
    }

    @Override
    public Place fromString(String token) {
	if (token == null || token.length() == 0) {
	    return Place.ROOT;
	}
	String[] split = token.split("/");
	switch (split.length) {
	case 0:
	case 1:
	    return Place.ROOT;
	case 2:
	    return new Place(split[1]);
	case 3:
	    return new Place(split[1], split[2]);
	}
	return Place.UNKNOWN;
    }

    @Override
    public String toString(Place place) {
	String token = "/";
	String controller = place.resource;
	if (controller != null)
	    token += controller;
	String id = place.id;
	if (id != null)
	    token += "/" + id;
	return token;
    }

}
