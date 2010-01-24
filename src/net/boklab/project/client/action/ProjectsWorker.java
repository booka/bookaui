package net.boklab.project.client.action;

import java.util.ArrayList;

import net.boklab.core.client.model.BokJSO;
import net.boklab.core.client.model.BokQuery;
import net.boklab.core.client.model.BokRequestResultsJSO;
import net.boklab.document.client.model.Document;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.rest.RestCallback;
import net.boklab.tools.client.rest.RestManager;

import com.google.gwt.core.client.JsonUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectsWorker {

    private final RestManager manager;
    private final EventBus eventBus;

    @Inject
    public ProjectsWorker(EventBus eventBus, RestManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;

	eventBus.addHandler(OpenProjectEvent.TYPE, new OpenProjectHandler() {
	    @Override
	    public void onOpenProject(OpenProjectEvent event) {
		openProject(event.getProjectId());
	    }
	});

	eventBus.addHandler(GetProjectsEvent.TYPE, new GetProjectsHandler() {
	    @Override
	    public void onGetProjects(GetProjectsEvent event) {
		getProjects();
	    }
	});
    }

    protected ArrayList<Project> getProjectList(BokRequestResultsJSO results) {
	ArrayList<Project> list = new ArrayList<Project>();
	int total = results.getChildrenSize();
	for (int index = 0; index < total; index++) {
	    list.add(new Project(results.getChildren(index)));
	}
	return list;
    }

    protected void getProjects() {
	BokQuery query = new BokQuery();
	query.bokTypeEquals(Project.TYPE);

	manager.getList("projects.list", "boks", query.toParams(), new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		BokRequestResultsJSO results = JsonUtils.<BokRequestResultsJSO> unsafeEval(text);
		ArrayList<Project> list = getProjectList(results);
		eventBus.fireEvent(new GotProjectsEvent(list));
	    }
	});
    }

    protected void openProject(String projectId) {
	BokQuery query = new BokQuery();
	query.bokTypeEquals(Document.TYPE);

	String host = "boks/" + projectId + "/children";

	manager.getList("project.documents", host, query.toParams(), new RestCallback() {
	    @Override
	    public void onSuccess(String text) {
		BokRequestResultsJSO results = JsonUtils.unsafeEval(text);
		BokJSO bok = results.getBok();
		Project project = new Project(bok);
		Project Project = new Project(project, results);
		eventBus.fireEvent(new ProjectOpenedEvent(Project));
	    }
	});
    }
}
