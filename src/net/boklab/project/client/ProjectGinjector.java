package net.boklab.project.client;

import net.boklab.document.client.DocumentsGinjector;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.ui.ProjectListPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(ProjectModule.class)
public interface ProjectGinjector extends DocumentsGinjector {
    ProjectListPresenter getProjectListPresenter();

    ProjectManager getProjects();
}
