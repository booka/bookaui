package net.boklab.project.client;

import net.boklab.project.client.action.ProjectManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectRouter {
    @Inject
    public ProjectRouter(Router router, final ProjectManager manager) {
	Log.debug("Init project router");
	router.onRequest("^/entrance$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		manager.getProjectList();
	    }
	});

	router.onRequest("^/archives/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		Log.debug("ProjectRouter: get project documents");
		Place place = event.getPlace();
		manager.getProjectDocuments(place.resourceId);
	    }
	});
    }
}
