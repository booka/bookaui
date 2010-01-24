package net.boklab.project.client;

import net.boklab.entrance.client.EntrancePresenter;
import net.boklab.project.client.action.Projects;
import net.boklab.project.client.action.ProjectsBridge;
import net.boklab.project.client.action.ProjectsWorker;
import net.boklab.project.client.ui.ProjectDisplay;
import net.boklab.project.client.ui.ProjectListDisplay;
import net.boklab.project.client.ui.ProjectListWidget;
import net.boklab.project.client.ui.ProjectWidget;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class ProjectModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ProjectListDisplay.class).to(ProjectListWidget.class);
	bind(ProjectDisplay.class).to(ProjectWidget.class);

	bind(Projects.class).to(ProjectsBridge.class).in(Singleton.class);
	bind(ProjectsWorker.class).asEagerSingleton();
	bind(ProjectRouter.class).asEagerSingleton();

	bind(EntrancePresenter.class).asEagerSingleton();
    }

}
