package net.boklab.places.client;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
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
import net.boklab.workspace.client.msg.CreateMessageEvent.Level;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntranceController {

    protected int loadingSiteUM;

    @Inject
    public EntranceController(final DocumentManager documents, final ProjectManager projects,
	    final MessageManager messages, final CallManager calls, final Router router,
	    final Provider<EntranceWorkspace> workspace, final NavigationPresenter navigation,
	    final SiteManager sites, final Sessions sessions) {

	loadingSiteUM = -1;

	final String ENTRANCE = "entrada";
	navigation.registerResource(NavigationDisplay.ENTRANCE, ENTRANCE);

	router.onRequest(Paths.root(), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		// redirect to entrance
		router.fireRequest(new Place(ENTRANCE));
	    }
	});

	router.onRequest(Paths.singletonResource(ENTRANCE), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		// project browser needs to receive events
		workspace.get().prepare();
		sites.open(true);
		workspace.get().show();
		router.setCurrent(I18nPlaces.t.placeEntrance(), event.getPlace());
		navigation.setActive(NavigationDisplay.ENTRANCE);
	    }
	});

	sites.addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		loadingSiteUM = messages.createMessage(I18nPlaces.t.loadingSite(), Level.working);
	    }
	});

	sites.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
	    }
	});

	sites.addRetrievedHandler(new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		loadingSiteUM = messages.removeMessage(loadingSiteUM);
		navigation.setPlace(I18nPlaces.t.placeSite());
	    }
	});

	projects.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok project = event.getBok();
		final Bok call = project.getFirstChild(Bok.CALL);
		calls.open(call.getId(), call.getTitle(), false);
	    }
	});

	calls.addRetrievedHandler(new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		workspace.get().setDocument(event.getBok());
	    }
	});

    }
}
