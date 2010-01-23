package net.boklab.projects.client;

import net.boklab.projects.client.ui.ProjectListPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(ProjectModule.class)
public interface ProjectGinjector {
    ProjectListPresenter getProjectListPresenter();
}
