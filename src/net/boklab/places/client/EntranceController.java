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
import net.boklab.workspace.client.ui.navigation.NavigationEvent;
import net.boklab.workspace.client.ui.navigation.NavigationHandler;
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

	final String entranceResource = I18nPlaces.t.resourceEntrance();
	final String projectResource = I18nPlaces.t.resourceProjects();
	final String callResource = I18nPlaces.t.resourceCall();

	navigation.addNavigationHandler(new NavigationHandler() {
	    @Override
	    public void onNavigation(final NavigationEvent event) {
		if (event.isNavigation(NavigationDisplay.ENTRANCE)) {
		    if (calls.hasActive()) {
			router.request(new Place(callResource, calls.getActiveId()));
		    } else if (projects.hasActive()) {
			router.request(new Place(projectResource, projects.getActiveId()));
		    } else {
			router.request(new Place(entranceResource));
		    }
		}
	    }
	});

	router.onRequest(Paths.root(), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.request(new Place(entranceResource));
	    }
	});

	router.onRequest(Paths.singletonResource(entranceResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.setCurrent(event.getPlace());
		navigation.setCurrentLocation(I18nPlaces.t.placeDescriptionSite());
		navigation.setActiveIcon(NavigationDisplay.ENTRANCE);
		workspace.get().show();
		sites.open(true);
	    }
	});

	router.onRequest(Paths.show(projectResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.setCurrent(event.getPlace());
		navigation.setActiveIcon(NavigationDisplay.ENTRANCE);
		workspace.get().show();
		projects.open(event.getPlace().id, null, false);
	    }
	});

	router.onRequest(Paths.show(callResource), new PlaceRequestHandler() {
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
		if (router.currentIs(entranceResource)) {
		    router.setCurrent(new Place(projectResource, event.getBokId()));
		}
	    }
	});

	projects.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok project = event.getBok();
		navigation.setProjectName(project.getTitle());
		if (router.currentIs(entranceResource) || router.currentIs(projectResource)) {
		    calls.openCallOfProject(project);
		    if (!sites.hasActive()) {
			sites.open(false);
		    }
		}
	    }
	});

	calls.addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		if (router.currentIs(projectResource)) {
		    router.setCurrent(new Place(callResource, event.getBokId()));
		}
	    }
	});

	calls.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok call = event.getBok();
		navigation.setCurrentLocation(I18nPlaces.t.placeCall(call.getTitle()));
		if (router.currentIs(projectResource)) {
		    workspace.get().setDocument(call);
		    if (!projects.isActive(call.getProjectId())) {
			projects.open(call.getProjectId(), null, false);
		    }
		}
	    }
	});

    }
}
