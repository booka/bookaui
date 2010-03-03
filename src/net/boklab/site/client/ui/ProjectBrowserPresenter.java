package net.boklab.site.client.ui;

import net.boklab.site.client.action.ProjectManager;
import net.boklab.site.client.action.SiteRetrievedEvent;
import net.boklab.site.client.action.SiteRetrievedHandler;
import net.boklab.site.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ProjectBrowserPresenter extends AbstractPresenter<ProjectBrowserDisplay> {

    @Inject
    public ProjectBrowserPresenter(final ProjectManager projects, final Provider<ProjectPresenter> provider,
	    final Provider<ProjectBrowserDisplay> display) {
	super(display);

	projects.onSiteRetrieved(new SiteRetrievedHandler() {
	    @Override
	    public void onProjectList(final SiteRetrievedEvent event) {
		getDisplay().clearList();
		for (final Project project : event.getList()) {
		    final ProjectPresenter presenter = provider.get();
		    presenter.setProject(project);
		    getDisplay().add(presenter.getDisplay());
		}

	    }
	});

    }

}
