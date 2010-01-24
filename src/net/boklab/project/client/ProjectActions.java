package net.boklab.project.client;

import net.boklab.project.client.action.GetProjectListHandler;
import net.boklab.project.client.action.ProjectListAction;
import net.boklab.tools.client.dispatcher.ActionsRegistry;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProjectActions {
    @Inject
    public ProjectActions(ActionsRegistry registry, Provider<GetProjectListHandler> getProjectList) {
	registry.register(ProjectListAction.class, getProjectList);
    }
}
