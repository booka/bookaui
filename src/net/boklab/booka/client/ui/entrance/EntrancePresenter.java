package net.boklab.booka.client.ui.entrance;

import net.boklab.project.client.action.ProjectListRetrievedEvent;
import net.boklab.project.client.action.ProjectListRetrievedHandler;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.ui.ProjectListPresenter;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntrancePresenter extends AbstractPresenter<WorkspaceDisplay> {

    @Inject
    public EntrancePresenter(final Router router, final ProjectManager projects,
	    final ProjectListPresenter projectList, final Provider<WorkspaceDisplay> display) {
	super(display);

	router.onRequest("^/entrance$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		projects.getProjectList();
		router.fireChanged(event.getPlace());
	    }
	});

	router.onRequest("^/archives/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		Log.debug("ProjectRouter: get project documents");
		final Place place = event.getPlace();
		projects.openProject(place.resourceId);
		router.fireChanged(event.getPlace());
	    }
	});

	projects.onProjectList(new ProjectListRetrievedHandler() {
	    @Override
	    public void onProjectList(final ProjectListRetrievedEvent event) {
		getDisplay().setLeft(projectList.getDisplay());
		getDisplay().setRight(Display.NONE);
	    }
	});
    }

}
