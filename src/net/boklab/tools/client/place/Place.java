package net.boklab.tools.client.place;

/**
 * A place in history
 * 
 * @author dani
 * 
 */
public class Place {
    private static final String BLANK = "";

    public static final Place ROOT = new Place();
    public static final Place UNKNOWN = new Place(BLANK, BLANK);

    public final String controller;
    public final String resourceId;
    public final String placeId;

    public Place() {
	this(null, null);
    }

    public Place(String controller) {
	this(controller, null);
    }

    public Place(String controller, String resourceId) {
	this.controller = controller;
	this.resourceId = resourceId;
	String c = controller == null ? BLANK : controller;
	String i = resourceId == null ? BLANK : "/" + resourceId;
	this.placeId = "/" + c + i;
    }

}
