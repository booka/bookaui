package net.boklab.entrance.client;

import net.boklab.project.client.ui.ProjectListPresenter;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EntrancePresenter {
    private final ProjectListPresenter projectList;

    @Inject
    public EntrancePresenter(EventBus eventBus, ProjectListPresenter projectList) {
	this.projectList = projectList;

    }

    public void revealOn(WorkspacePresenter workspace) {
	workspace.setEast(projectList);
    }

}
