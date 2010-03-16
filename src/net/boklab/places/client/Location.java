package net.boklab.places.client;

import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router.Path;

public interface Location {

    Path getPath();

    void open(Place place);

}
