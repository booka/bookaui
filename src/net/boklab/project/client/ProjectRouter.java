package net.boklab.project.client;

import net.boklab.project.client.action.ProjectManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;

import com.google.inject.Inject;

public class ProjectRouter {
    @Inject
    public ProjectRouter(Router router, final ProjectManager manager) {
	router.onRequest("^/entrance$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		manager.getProjectList();
	    }
	});

	router.onRequest("^/archives/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		Place place = event.getPlace();
		manager.getProjectDocuments(place.resourceId);
	    }
	});
    }
}
