package net.boklab.project.client.action;

import java.util.ArrayList;

import net.boklab.core.client.BokQuery;
import net.boklab.core.client.BokSearchResultsJSO;
import net.boklab.document.client.model.Document;
import net.boklab.project.client.model.Project;
import net.boklab.project.client.model.ProjectDocuments;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.rest.RestCallback;
import net.boklab.tools.client.rest.RestManager;
import net.boklab.tools.client.router.Router;

import com.google.gwt.core.client.JsonUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultProjectManager implements ProjectManager {
    private static final String RESOURCE = "boks";
    private final EventBus eventBus;
    private final RestManager manager;
    private ArrayList<Project> currentProjectList;

    @Inject
    public DefaultProjectManager(Router router, EventBus events, RestManager manager) {
	this.eventBus = events;
	this.manager = manager;

	router.when("^/entrace$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		getProjectList();
	    }
	});
	router.when("^/entrace/\\d+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
	    }
	});
    }

    @Override
    public void getProjectDocuments(final Project project) {
	BokQuery query = new BokQuery();
	query.bokTypeEquals(Document.TYPE);
	query.bokParentEquals(project.getId());

	manager.getList("documents.documents", RESOURCE, query.toParams(), new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		BokSearchResultsJSO results = JsonUtils.unsafeEval(text);
		fireProjectDocuments(new ProjectDocuments(project, results));
	    }

	});
    }

    @Override
    public void getProjectList() {
	BokQuery query = new BokQuery();
	query.bokTypeEquals(Project.TYPE);

	manager.getList("projects.list", RESOURCE, query.toParams(), new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		handleResults(JsonUtils.<BokSearchResultsJSO> unsafeEval(text));
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
	this.currentProjectList = list;
	eventBus.fireEvent(new ProjectListEvent(list));
    }

    protected void handleResults(BokSearchResultsJSO results) {
	ArrayList<Project> list = new ArrayList<Project>();
	int total = results.getSize();
	for (int index = 0; index < total; index++) {
	    list.add(new Project(results.get(index)));
	}
	fireProjectList(list);
    }

}
