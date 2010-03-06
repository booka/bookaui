package net.boklab.site.client;

import net.boklab.site.client.project.browser.ProjectBrowserDisplay;
import net.boklab.site.client.project.browser.ProjectBrowserWidget;
import net.boklab.site.client.project.browser.ProjectDisplay;
import net.boklab.site.client.project.browser.ProjectWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class SiteModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ProjectManager.class).asEagerSingleton();
	bind(SiteManager.class).asEagerSingleton();
	bind(CallManager.class).asEagerSingleton();

	bind(ProjectBrowserDisplay.class).to(ProjectBrowserWidget.class);
	bind(ProjectDisplay.class).to(ProjectWidget.class);
    }

}
