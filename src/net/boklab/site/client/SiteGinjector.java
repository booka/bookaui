package net.boklab.site.client;

import net.boklab.document.client.DocumentsGinjector;
import net.boklab.site.client.project.browser.ProjectBrowserPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(SiteModule.class)
public interface SiteGinjector extends DocumentsGinjector {
    ProjectBrowserPresenter getProjectListPresenter();

    ProjectManager getProjects();
}
