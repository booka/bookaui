package net.boklab.places.client.entrance;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.places.client.I18nPlaces;
import net.boklab.places.client.Location;
import net.boklab.site.client.CallManager;
import net.boklab.site.client.ProjectManager;
import net.boklab.site.client.SiteManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Path;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class SiteLocation implements Location {
    private final NavigationPresenter navigation;
    private final SiteManager sites;
    private final Provider<EntranceWorkspace> workspace;
    private final String resourceEntrance;

    @Inject
    public SiteLocation(final Router router, final NavigationPresenter navigation,
	    final Provider<EntranceWorkspace> workspace, final SiteManager sites,
	    final ProjectManager projects, final CallManager calls) {
	this.navigation = navigation;
	this.workspace = workspace;
	this.sites = sites;

	resourceEntrance = I18nPlaces.t.resourceEntrance();

	projects.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok project = event.getBok();
		navigation.setProjectName(project.getTitle());
		if (router.currentIs(resourceEntrance)) {
		    calls.openCallOfProject(project);
		}
	    }
	});

    }

    public Path getPath() {
	return Paths.singletonResource(resourceEntrance);
    }

    public void open(final Place place) {
	navigation.setCurrentLocation(I18nPlaces.t.locationSite());
	navigation.setActiveIcon(NavigationDisplay.ENTRANCE);
	workspace.get().show();
	sites.open(true);
    }

}
