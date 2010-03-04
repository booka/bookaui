package net.boklab.site.client;

import net.boklab.site.client.ui.ProjectBrowserDisplay;
import net.boklab.site.client.ui.ProjectBrowserWidget;
import net.boklab.site.client.ui.ProjectDisplay;
import net.boklab.site.client.ui.ProjectWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class SiteModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(SitesController.class).asEagerSingleton();
	bind(ProjectManager.class).asEagerSingleton();
	bind(SiteManager.class).asEagerSingleton();

	bind(ProjectBrowserDisplay.class).to(ProjectBrowserWidget.class);
	bind(ProjectDisplay.class).to(ProjectWidget.class);
    }

}
