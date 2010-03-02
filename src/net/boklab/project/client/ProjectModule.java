package net.boklab.project.client;

import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.ui.ProjectBrowserDisplay;
import net.boklab.project.client.ui.ProjectBrowserWidget;
import net.boklab.project.client.ui.ProjectDisplay;
import net.boklab.project.client.ui.ProjectWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class ProjectModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ProjectManager.class).asEagerSingleton();
	bind(ProjectBrowserDisplay.class).to(ProjectBrowserWidget.class);
	bind(ProjectDisplay.class).to(ProjectWidget.class);
	bind(ProjectRouter.class).asEagerSingleton();
    }

}
