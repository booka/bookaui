package net.boklab.places.client;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.OpenBokEvent;
import net.boklab.core.client.bok.events.OpenBokHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.core.client.session.Sessions;
import net.boklab.document.client.DocumentManager;
import net.boklab.site.client.CallManager;
import net.boklab.site.client.ProjectManager;
import net.boklab.site.client.SiteManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.msg.MessageManager;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntranceController {

    @Inject
    public EntranceController(final DocumentManager documents, final ProjectManager projects,
	    final MessageManager messages, final CallManager calls, final Router router,
	    final Provider<EntranceWorkspace> workspace, final NavigationPresenter navigation,
	    final SiteManager sites, final Sessions sessions) {

	final String ENTRANCE = I18nPlaces.t.resourceEntrance();
	final String PROJECT = I18nPlaces.t.resourceProjects();
	final String CALL = I18nPlaces.t.resourceCall();
	navigation.registerResource(NavigationDisplay.ENTRANCE, ENTRANCE);

	router.onRequest(Paths.root(), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.request(new Place(ENTRANCE));
	    }
	});

	router.onRequest(Paths.singletonResource(ENTRANCE), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.setCurrent(event.getPlace());
		navigation.setCurrentLocation(I18nPlaces.t.placeDescriptionSite());
		navigation.setActiveIcon(NavigationDisplay.ENTRANCE);
		workspace.get().show();
		sites.open(true);
	    }
	});

	router.onRequest(Paths.show(PROJECT), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.setCurrent(event.getPlace());
		navigation.setActiveIcon(NavigationDisplay.ENTRANCE);
		workspace.get().show();
		projects.open(event.getPlace().id, null, false);
	    }
	});

	router.onRequest(Paths.show(CALL), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.setCurrent(event.getPlace());
		navigation.setActiveIcon(NavigationDisplay.ENTRANCE);
		workspace.get().show();
	    }
	});

	projects.addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		if (router.currentIs(ENTRANCE)) {
		    router.request(new Place(PROJECT, event.getBokId()));
		}
	    }
	});

	projects.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		if (router.currentIs(ENTRANCE)) {
		    final Bok project = event.getBok();
		    router.request(new Place(PROJECT, project.getId()));
		    calls.openCallOfProject(project);
		    sites.open(false);
		}
	    }
	});

	calls.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		if (router.currentIs(PROJECT)) {
		    final Bok call = event.getBok();
		    workspace.get().setDocument(call);
		    projects.open(call.getProjectId(), null, false);
		}
	    }
	});

    }
}
