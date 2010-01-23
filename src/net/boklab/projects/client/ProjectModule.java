package net.boklab.projects.client;

import net.boklab.projects.client.ui.ProjectDisplay;
import net.boklab.projects.client.ui.ProjectListDisplay;
import net.boklab.projects.client.ui.ProjectListWidget;
import net.boklab.projects.client.ui.ProjectWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class ProjectModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ProjectListDisplay.class).to(ProjectListWidget.class);
	bind(ProjectDisplay.class).to(ProjectWidget.class);
    }

}
