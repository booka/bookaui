package net.boklab.project.client.action;

import net.boklab.core.client.model.BokQuery;
import net.boklab.core.client.persistence.BokListRetrievedEvent;
import net.boklab.core.client.persistence.BokListRetrievedHandler;
import net.boklab.core.client.persistence.BokManager;
import net.boklab.core.client.persistence.BokRetrievedEvent;
import net.boklab.core.client.persistence.BokRetrievedHandler;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectManager {
    private Project activeProject;
    private final BokManager manager;
    private final EventBus eventBus;

    @Inject
    public ProjectManager(final EventBus eventBus, final BokManager manager) {
	this.eventBus = eventBus;
	this.manager = manager;

	activeProject = null;

	onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(final Project project) {
		activeProject = project;
	    }
	});
    }

    public Project getActiveProject() {
	return activeProject;
    }

    public void getProjectList() {
	final BokQuery query = new BokQuery().bokTypeEquals(Project.TYPE);
	manager.list(query, new BokListRetrievedHandler() {
	    @Override
	    public void onBokListRetrieved(final BokListRetrievedEvent event) {
		eventBus.fireEvent(new ProjectListRetrievedEvent(event.getChildren()));
	    }
	});
    }

    public boolean hasActiveProject() {
	return activeProject != null;
    }

    public void onProjectList(final ProjectListRetrievedHandler handler) {
	eventBus.addHandler(ProjectListRetrievedEvent.TYPE, handler);
    }

    public void onProjectOpened(final ProjectOpenedHandler handler) {
	eventBus.addHandler(ProjectOpenedEvent.TYPE, handler);
    }

    public void openProject(final String projectId) {
	if (activeProject == null || !activeProject.getId().equals(projectId)) {
	    manager.retrieve(projectId, new BokRetrievedHandler() {
		@Override
		public void onBokRetrieved(final BokRetrievedEvent event) {
		    final Project project = new Project(event.getBok());
		    eventBus.fireEvent(new ProjectOpenedEvent(project));
		}
	    });
	}
    }

}
