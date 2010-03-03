package net.boklab.entrance.client;

import net.boklab.core.client.session.Sessions;
import net.boklab.core.client.wip.WipPresenter;
import net.boklab.site.client.action.ProjectManager;
import net.boklab.site.client.action.SiteRetrievedEvent;
import net.boklab.site.client.action.SiteRetrievedHandler;
import net.boklab.site.client.ui.ProjectBrowserPresenter;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntranceController {

    @Inject
    public EntranceController(final EventBus eventBus, final Router router,
	    final Provider<BookaAppPresenter> bookaProvider, final Provider<WorkspacePresenter> workspace,
	    final NavigationPresenter navigation, final Provider<WipPresenter> wip,
	    final Provider<ProjectBrowserPresenter> projectBrowser, final ProjectManager projects,
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
		projectBrowser.get();
		projects.openSite();
		final WorkspacePresenter w = workspace.get();
		w.setCenter(null);
		w.setVisible(true);
		w.setLeft(wip.get());
		w.setRight(projectBrowser.get());
		bookaProvider.get().setContent(w);
		router.fireChanged(I18nEntrance.t.placeEntrance(), event.getPlace());
	    }
	});

	projects.onSiteRetrieved(new SiteRetrievedHandler() {
	    @Override
	    public void onProjectList(final SiteRetrievedEvent event) {
	    }
	});
    }
}
