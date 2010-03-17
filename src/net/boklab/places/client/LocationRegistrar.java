package net.boklab.places.client;

import net.boklab.places.client.archive.ArchivesLocation;
import net.boklab.places.client.archive.DocumentLocation;
import net.boklab.places.client.entrance.CallsLocation;
import net.boklab.places.client.entrance.SiteLocation;
import net.boklab.places.client.indice.IndicesLocation;
import net.boklab.tools.client.router.Router;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LocationRegistrar {

    @Inject
    public LocationRegistrar(final Router router, final RootLocation root, final SiteLocation site,
	    final CallsLocation calls, final ArchivesLocation archives,
	    final DocumentLocation documents, final IndicesLocation indices) {

	router.addLocation(root);

	// entrance
	router.addLocation(site);
	router.addLocation(calls);

	// archives
	router.addLocation(archives);
	router.addLocation(documents);

	// forum

	// booka
	router.addLocation(indices);
    }
}
