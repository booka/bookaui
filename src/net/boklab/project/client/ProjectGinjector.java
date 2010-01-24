package net.boklab.project.client;

import net.boklab.project.client.ui.ProjectListPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(ProjectModule.class)
public interface ProjectGinjector {
    ProjectListPresenter getProjectListPresenter();
}
