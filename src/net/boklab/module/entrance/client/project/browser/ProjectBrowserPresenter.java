package net.boklab.module.entrance.client.project.browser;

import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.ClientSession;
import net.boklab.module.entrance.client.site.SiteManager;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ProjectBrowserPresenter extends AbstractPresenter<ProjectBrowserDisplay> {

    @Inject
    public ProjectBrowserPresenter(final SiteManager sites, final ClientSession session,
	    final Provider<ProjectPresenter> provider, final Provider<ProjectBrowserDisplay> display) {
	super(display);

	sites.addRetrievedHandler(new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		openSite(provider, event.getBok());
	    }
	});

	Bok site = session.getSite();
	if (site != null) {
	    openSite(provider, site);
	}

    }

    private void openSite(final Provider<ProjectPresenter> provider, final Bok site) {
	getDisplay().clearList();
	for (final Bok project : site.getChildren()) {
	    final ProjectPresenter presenter = provider.get();
	    presenter.setProject(project);
	    getDisplay().add(presenter.getDisplay());
	}
    }

}
