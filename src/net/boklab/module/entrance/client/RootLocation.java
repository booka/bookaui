package net.boklab.module.entrance.client;

import net.boklab.core.client.module.Location;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Path;
import net.boklab.tools.client.router.Router.Paths;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RootLocation implements Location {

    private final Router router;

    @Inject
    public RootLocation(final Router router) {
	this.router = router;
    }

    @Override
    public Path getPath() {
	return Paths.root();
    }

    @Override
    public void open(final Place place) {
	final String entranceResource = I18nEntrance.t.resourceEntrance();
	router.request(new Place(entranceResource));
    }

}
