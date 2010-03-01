package net.boklab.booka.client;

import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.ui.ProjectListPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BokEntranceInstaller {

    @Inject
    public BokEntranceInstaller(final Router router, final ProjectManager projects,
	    final ProjectListPresenter projectList) {

	router.onRequest("^/archives/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final Place place = event.getPlace();
		projects.openProject(place.resourceId);
		router.fireChanged("i18n", event.getPlace());
	    }
	});

    }

}
