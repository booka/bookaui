package net.boklab.project.client.action;

import java.util.ArrayList;

import net.boklab.core.client.BokJSO;
import net.boklab.core.client.BokQuery;
import net.boklab.core.client.BokRequestResultsJSO;
import net.boklab.document.client.model.Document;
import net.boklab.project.client.model.Project;
import net.boklab.project.client.model.ProjectDocuments;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.rest.RestCallback;
import net.boklab.tools.client.rest.RestManager;
import net.boklab.tools.client.router.Router;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultProjectManager implements ProjectManager {
    private final EventBus eventBus;
    private final RestManager manager;

    @Inject
    public DefaultProjectManager(Router router, EventBus events, RestManager manager) {
	this.eventBus = events;
	this.manager = manager;

	GWT.log("New project manager", null);

	router.when("^/entrance$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		GWT.log("PROJECT LIST", null);
		getProjectList();
	    }
	});

	router.when("^/archives/\\d+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		Place place = event.getPlace();
		getProjectDocuments(place.resourceId);
	    }
	});
    }

    @Override
    public void getProjectDocuments(final String projectId) {
	BokQuery query = new BokQuery();
	query.bokTypeEquals(Document.TYPE);

	String host = "boks/" + projectId + "/children";

	manager.getList("project.documents", host, query.toParams(), new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		GWT.log("Project documents!", null);
		BokRequestResultsJSO results = JsonUtils.unsafeEval(text);
		BokJSO bok = results.getBok();
		Project project = new Project(bok);
		fireProjectDocuments(new ProjectDocuments(project, results));
	    }

	});
    }

    @Override
    public void getProjectList() {
	BokQuery query = new BokQuery();
	query.bokTypeEquals(Project.TYPE);

	manager.getList("projects.list", "boks", query.toParams(), new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		BokRequestResultsJSO results = JsonUtils.<BokRequestResultsJSO> unsafeEval(text);
		ArrayList<Project> list = getProjectList(results);
		fireProjectList(list);
	    }
	});
    }

    @Override
    public void onProjectDocuments(ProjectDocumentsHandler handler) {
	eventBus.addHandler(ProjectDocumentsEvent.TYPE, handler);
    }

    @Override
    public void onProjectList(ProjectListHandler handler) {
	eventBus.addHandler(ProjectListEvent.TYPE, handler);
    }

    private void fireProjectDocuments(ProjectDocuments projectDocuments) {
	eventBus.fireEvent(new ProjectDocumentsEvent(projectDocuments));
    }

    private void fireProjectList(ArrayList<Project> list) {
	eventBus.fireEvent(new ProjectListEvent(list));
    }

    protected ArrayList<Project> getProjectList(BokRequestResultsJSO results) {
	ArrayList<Project> list = new ArrayList<Project>();
	int total = results.getChildrenSize();
	for (int index = 0; index < total; index++) {
	    list.add(new Project(results.getChildren(index)));
	}
	return list;
    }

}
