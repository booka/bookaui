package net.boklab.tools.client.place;

public class RestPlaceTokenizer implements PlaceTokenizer {

    @Override
    public Place fromToken(String token) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getToken(Place place) {
	String token = "/";
	String controller = place.getParameter(Place.CONTROLLER);
	if (controller != null)
	    token += controller;
	String id = place.getParameter(Place.ID);
	if (id != null)
	    token += "/" + id;
	return token;
    }

}
