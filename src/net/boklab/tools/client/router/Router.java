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

	public static Path root() {
	    return new Path("/?");
	}

	public static Path show(final String resourceSingular) {
	    return new Path("^/" + resourceSingular + "/\\w+$");
	}

	public static Path singletonResource(final String resource) {
	    return new Path("^/" + resource + "$");
	}
    }

    void setCurrent(String description, Place place);

    void fireRequest(Place place);

    void onRequest(Path regex, PlaceRequestHandler handler);

}
