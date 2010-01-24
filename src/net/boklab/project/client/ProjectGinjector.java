package net.boklab.project.client;

import net.boklab.document.client.DocumentGinjector;
import net.boklab.project.client.action.Projects;
import net.boklab.project.client.ui.ProjectListPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(ProjectModule.class)
public interface ProjectGinjector extends DocumentGinjector {
    ProjectListPresenter getProjectListPresenter();

    Projects getProjects();
}
