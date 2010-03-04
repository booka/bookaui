package net.boklab.site.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.PlainBokManager;
import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ProjectManager extends AbstractBokManager {

    @Inject
    public ProjectManager(final EventBus eventBus, final PlainBokManager manager) {
	super("Project", eventBus, manager);
    }

    public Bok getActiveProject() {
	return getActive();
    }

    public boolean hasActiveProject() {
	return hasActive();
    }

    public void openProject(final String projectId, final String knownTitle, final boolean forceOpen) {
	super.open(projectId, knownTitle, forceOpen);
    }
}
