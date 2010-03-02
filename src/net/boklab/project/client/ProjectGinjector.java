package net.boklab.project.client;

import net.boklab.document.client.DocumentsGinjector;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.ui.ProjectBrowserPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(ProjectModule.class)
public interface ProjectGinjector extends DocumentsGinjector {
    ProjectBrowserPresenter getProjectListPresenter();

    ProjectManager getProjects();
}
