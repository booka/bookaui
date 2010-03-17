package net.boklab.tools.client.router;

import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestHandler;

public interface Router {

    public class Path {
	public final String regex;

	Path(final String regex) {
	    this.regex = regex;
	}
    }

    public static class Paths {

	public static Path resource(final String resourceSingular) {
	    return new Path("^/" + resourceSingular + "/\\w+$");
	}

	public static Path root() {
	    return new Path("/?");
	}

	public static Path singletonResource(final String resource) {
	    return new Path("^/" + resource + "$");
	}
    }

    boolean currentIs(String resource);

    void onRequest(Path regex, PlaceRequestHandler handler);

    void request(Place place);

    void setCurrent(Place place);

}
