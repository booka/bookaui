package net.boklab.site.client.action;

import net.boklab.core.client.persistence.BokManager;
import net.boklab.core.client.persistence.BokRetrievedEvent;
import net.boklab.core.client.persistence.BokRetrievedHandler;
import net.boklab.site.client.I18nSite;
import net.boklab.site.client.model.Project;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.event.UserMessageEvent;
import net.boklab.workspace.client.event.UserMessageEvent.Level;

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
	    public void onProject(final ProjectOpenedEvent event) {
		activeProject = event.getProject();
	    }
	});
    }

    public void addProjectRequestHandler(final ProjectRequestHandler handler) {
	eventBus.addHandler(ProjectRequestEvent.getType(), handler);
    }

    public Project getActiveProject() {
	return activeProject;
    }

    public void openSite() {
	eventBus.fireEvent(new UserMessageEvent(I18nSite.t.loadingSite(), Level.working));
	manager.retrieve("1", new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		eventBus.fireEvent(new SiteRetrievedEvent(event.getBok(), event.getChildren()));
	    }
	});
    }

    public boolean hasActiveProject() {
	return activeProject != null;
    }

    public void onProjectOpened(final ProjectOpenedHandler handler) {
	eventBus.addHandler(ProjectOpenedEvent.getType(), handler);
    }

    public void onSiteRetrieved(final SiteRetrievedHandler handler) {
	eventBus.addHandler(SiteRetrievedEvent.TYPE, handler);
    }

    public void openProject(final String projectId, final boolean changePlace, final String knownTitle) {
	eventBus.fireEvent(new ProjectRequestEvent(projectId, changePlace, knownTitle));
	if (activeProject == null || !activeProject.getId().equals(projectId)) {
	    manager.retrieve(projectId, new BokRetrievedHandler() {
		@Override
		public void onBokRetrieved(final BokRetrievedEvent event) {
		    final Project project = new Project(event.getBok(), event.getChildren());
		    eventBus.fireEvent(new ProjectOpenedEvent(project, changePlace));
		    eventBus.fireEvent(new UserMessageEvent(I18nSite.t.projectOpened(project.getTitle())));
		}
	    });
	} else {
	    eventBus.fireEvent(new ProjectOpenedEvent(activeProject, changePlace));
	}
    }

}
