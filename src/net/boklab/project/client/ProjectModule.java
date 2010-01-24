package net.boklab.project.client;

import net.boklab.project.client.ui.ProjectDisplay;
import net.boklab.project.client.ui.ProjectListDisplay;
import net.boklab.project.client.ui.ProjectListWidget;
import net.boklab.project.client.ui.ProjectWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class ProjectModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ProjectListDisplay.class).to(ProjectListWidget.class);
	bind(ProjectDisplay.class).to(ProjectWidget.class);
	bind(ProjectActions.class).asEagerSingleton();
    }

}
