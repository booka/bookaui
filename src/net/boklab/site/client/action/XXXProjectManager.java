package net.boklab.site.client.action;

import net.boklab.core.client.bok.events.BokCreatedEvent;
import net.boklab.core.client.bok.events.BokCreatedHandler;
import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.bok.events.BokUpdatedEvent;
import net.boklab.core.client.bok.events.BokUpdatedHandler;
import net.boklab.core.client.bok.events.OpenBokEvent;
import net.boklab.core.client.bok.events.OpenBokHandler;
import net.boklab.core.client.persistence.PlainBokManager;
import net.boklab.site.client.I18nSite;
import net.boklab.site.client.model.Project;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.event.UserMessageEvent;

import com.google.inject.Inject;

public class XXXProjectManager {
    private Project activeProject;
    private final PlainBokManager manager;
    private final EventBus eventBus;

    @Inject
    public XXXProjectManager(final EventBus eventBus, final PlainBokManager manager) {
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

    public void addProjectRequestHandler(final OpenBokHandler handler) {
	eventBus.addHandler(OpenBokEvent.getType(), handler);
    }

    public Project getActiveProject() {
	return activeProject;
    }

    public boolean hasActiveProject() {
	return activeProject != null;
    }

    public void onProjectChanged(final BokUpdatedHandler bokUpdatedHandler) {
	eventBus.addHandler(BokUpdatedEvent.getType(), new BokUpdatedHandler() {
	    @Override
	    public void onBokUpdated(final BokUpdatedEvent event) {
		if (event.isBokType(Project.TYPE)) {
		    bokUpdatedHandler.onBokUpdated(event);
		}
	    }
	});
    }

    public void onProjectCreated(final BokCreatedHandler bokCreatedHandler) {
	eventBus.addHandler(BokCreatedEvent.getType(), new BokCreatedHandler() {
	    @Override
	    public void onBokCreated(final BokCreatedEvent event) {
		if (event.isBokType(Project.TYPE)) {
		    bokCreatedHandler.onBokCreated(event);
		}
	    }
	});
    }

    public void onProjectOpened(final ProjectOpenedHandler handler) {
	eventBus.addHandler(ProjectOpenedEvent.getType(), handler);
    }

    public void onSiteRetrieved(final BokRetrievedHandler handler) {
	eventBus.addHandler(BokRetrievedEvent.getType(), new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		if (event.isBokType("Site")) {
		    handler.onBokRetrieved(event);
		}
	    }
	});
    }

    public void openProject(final String projectId, final String knownTitle, final boolean changePlace) {
	eventBus.fireEvent(new OpenBokEvent("Project", projectId, changePlace, knownTitle));
	if (activeProject == null || !activeProject.getId().equals(projectId)) {
	    manager.retrieve(projectId, new BokRetrievedHandler() {
		@Override
		public void onBokRetrieved(final BokRetrievedEvent event) {
		    final Project project = new Project(event.getBok());
		    eventBus.fireEvent(new ProjectOpenedEvent(project, changePlace));
		    eventBus.fireEvent(new UserMessageEvent(I18nSite.t.projectOpened(project.getTitle())));
		}
	    });
	} else {
	    eventBus.fireEvent(new ProjectOpenedEvent(activeProject, changePlace));
	}
    }

}
