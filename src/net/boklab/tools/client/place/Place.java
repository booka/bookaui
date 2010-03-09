package net.boklab.tools.client.place;

/**
 * A place in history
 * 
 * @author dani
 * 
 */
public class Place {
    private static final String BLANK = "";

    public static final Place ROOT = new Place("");
    public static final Place UNKNOWN = new Place(BLANK, BLANK);

    public static String urlFor(final String resource) {
	return "/" + resource;
    }

    public static String urlFor(final String controller, final String resourceId) {
	final String c = controller == null ? BLANK : controller;
	final String i = resourceId == null ? BLANK : "/" + resourceId;
	return "/" + c + i;
    }
    public final String resource;

    public final String id;

    public final String placeToken;

    public Place() {
	this(null, null);
    }

    public Place(final String controller) {
	this(controller, null);
    }

    public Place(final String controller, final String resourceId) {
	resource = controller;
	id = resourceId;
	final String t = urlFor(controller, resourceId);
	placeToken = t;
    }

    @Override
    public boolean equals(final Object o) {
	if (o instanceof Place) {
	    final Place place = (Place) o;
	    return placeToken.equals(place.placeToken);
	}
	return false;
    }

    @Override
    public int hashCode() {
	return 16 * placeToken.hashCode();
    }

    public String toDebugString() {
	return "Place: " + placeToken;
    }
}
