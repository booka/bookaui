package net.boklab.site.client.ui;

import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.site.client.SiteManager;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ProjectBrowserPresenter extends AbstractPresenter<ProjectBrowserDisplay> {

    @Inject
    public ProjectBrowserPresenter(final SiteManager sites, final Provider<ProjectPresenter> provider,
	    final Provider<ProjectBrowserDisplay> display) {
	super(display);

	sites.addRetrievedHandler(new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		final Bok site = event.getBok();
		getDisplay().clearList();
		for (final Bok project : site.getChildren()) {
		    final ProjectPresenter presenter = provider.get();
		    presenter.setProject(project);
		    getDisplay().add(presenter.getDisplay());
		}

	    }
	});

    }

}
