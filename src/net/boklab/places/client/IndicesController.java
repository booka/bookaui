package net.boklab.places.client;

import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.indices.client.IndiceManager;
import net.boklab.site.client.ProjectManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.msg.MessageManager;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationEvent;
import net.boklab.workspace.client.ui.navigation.NavigationHandler;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IndicesController {
    @Inject
    public IndicesController(final Router router, final IndicesWorkspace workspace,
	    final NavigationPresenter navigation, final MessageManager messages,
	    final PlaceManager places, final ProjectManager projects, final IndiceManager indices) {

	final String browseResource = I18nPlaces.t.resourceBrowse();

	navigation.addNavigationHandler(new NavigationHandler() {
	    @Override
	    public void onNavigation(final NavigationEvent event) {
		if (event.isNavigation(NavigationDisplay.BOOKA)) {
		    if (indices.hasActive()) {
			router.request(new Place(browseResource, indices.getActive().getId()));
		    } else if (projects.hasActive()) {
			router.request(new Place(browseResource, projects.getActiveId()));
		    }
		}
	    }
	});

	router.onRequest(Paths.singletonResource(browseResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
	    }
	});

	router.onRequest(Paths.show(browseResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		indices.open(event.getPlace().id, null, false);
		navigation.setActiveIcon(NavigationDisplay.BOOKA);
		workspace.show();
		// FIXME: i18n
		router.setCurrent(event.getPlace());
	    }
	});

	indices.addRetrievedHandler(new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		final Bok indice = event.getBok();
		if (!projects.isActive(indice.getProjectId())) {
		    projects.open(indice.getProjectId(), null, false);
		}
	    }
	});
    }
}
