package net.boklab.project.client.action;

import net.boklab.project.client.model.Project;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectsBridge implements Projects {

    private final EventBus eventBus;
    private Project activeProject;

    @Inject
    public ProjectsBridge(EventBus eventBus) {
	this.eventBus = eventBus;
	activeProject = null;
	onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(Project project) {
		activeProject = project;
	    }
	});
    }

    @Override
    public Project getActiveProject() {
	return activeProject;
    }

    @Override
    public void getProjectList() {
	eventBus.fireEvent(new GetProjectsEvent());
    }

    @Override
    public boolean hasActiveProject() {
	return activeProject != null;
    }

    @Override
    public void onProjectList(GotProjectsHandler handler) {
	eventBus.addHandler(GotProjectsEvent.TYPE, handler);
    }

    @Override
    public void onProjectOpened(ProjectOpenedHandler handler) {
	eventBus.addHandler(ProjectOpenedEvent.TYPE, handler);
    }

    @Override
    public void openProject(String projectId) {
	if (activeProject == null || !activeProject.getId().equals(projectId))
	    eventBus.fireEvent(new OpenProjectEvent(projectId));
    }

}
