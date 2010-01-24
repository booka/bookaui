package net.boklab.project.client.action;

import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectsBridge implements Projects {

    private final EventBus eventBus;

    @Inject
    public ProjectsBridge(EventBus eventBus) {
	this.eventBus = eventBus;
    }

    @Override
    public void getProjectList() {
	eventBus.fireEvent(new GetProjectsEvent());
    }

    @Override
    public void onProjectOpened(ProjectOpenedHandler handler) {
	eventBus.addHandler(ProjectOpenedEvent.TYPE, handler);
    }

    @Override
    public void onProjectList(GotProjectsHandler handler) {
	eventBus.addHandler(GotProjectsEvent.TYPE, handler);
    }

    @Override
    public void openProject(String projectId) {
	eventBus.fireEvent(new OpenProjectEvent(projectId));
    }

}
