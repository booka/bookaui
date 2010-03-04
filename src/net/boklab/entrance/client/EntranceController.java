package net.boklab.entrance.client;

import net.boklab.core.client.bok.events.OpenBokEvent;
import net.boklab.core.client.bok.events.OpenBokHandler;
import net.boklab.core.client.session.Sessions;
import net.boklab.site.client.I18nSite;
import net.boklab.site.client.SiteManager;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.event.UserMessageEvent;
import net.boklab.workspace.client.event.UserMessageEvent.Level;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntranceController {

    @Inject
    public EntranceController(final EventBus eventBus, final Router router,
	    final Provider<EntranceWorkspace> workspace, final NavigationPresenter navigation, final SiteManager sites,
	    final Sessions sessions) {

	final String ENTRANCE = "entrada";
	navigation.setResource(NavigationDisplay.ENTRANCE, ENTRANCE);

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
		router.fireChanged(I18nEntrance.t.placeEntrance(), event.getPlace());
	    }
	});

	sites.addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		eventBus.fireEvent(new UserMessageEvent(I18nSite.t.loadingSite(), Level.working));
	    }
	});
    }
}
