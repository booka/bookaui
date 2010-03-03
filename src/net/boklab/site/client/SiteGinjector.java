package net.boklab.site.client;

import net.boklab.document.client.DocumentsGinjector;
import net.boklab.site.client.action.ProjectManager;
import net.boklab.site.client.ui.ProjectBrowserPresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(SiteModule.class)
public interface SiteGinjector extends DocumentsGinjector {
    ProjectBrowserPresenter getProjectListPresenter();

    ProjectManager getProjects();
}
