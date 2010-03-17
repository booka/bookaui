package net.boklab.module.archives.client;

import net.boklab.module.entrance.client.project.browser.ProjectBrowserDisplay;
import net.boklab.module.entrance.client.project.browser.ProjectBrowserWidget;
import net.boklab.module.entrance.client.project.browser.ProjectDisplay;
import net.boklab.module.entrance.client.project.browser.ProjectWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class ArchivesModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ArchivesConfiguration.class).asEagerSingleton();
	bind(ProjectBrowserDisplay.class).to(ProjectBrowserWidget.class);
	bind(ProjectDisplay.class).to(ProjectWidget.class);
    }

}
