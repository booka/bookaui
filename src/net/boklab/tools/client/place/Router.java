package net.boklab.tools.client.place;

public class Router {

    public static boolean is(Place place, String controller) {
	return controller.equals(place.getParameter(Place.CONTROLLER));
    }

}
