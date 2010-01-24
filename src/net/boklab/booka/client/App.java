package net.boklab.booka.client;

import net.boklab.booka.client.ui.app.BookaAppPresenter;
import net.boklab.entrance.client.EntrancePresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

public class App {

    private final BookaPresenters presenters;
    private final Router router;

    @Inject
    public App(Router router, final BookaPresenters presenters, PlaceManager placeManager) {
	this.router = router;
	this.presenters = presenters;

	final BookaAppPresenter booka = presenters.bookaApp.get();

	RootLayoutPanel.get().add(booka.getDisplay().asWidget());

	router.when("^/entrance$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		showEntrance(booka, event.getPlace());
	    }
	});

	placeManager.fireCurrentPlace();
    }

    private void showEntrance(final BookaAppPresenter booka, Place place) {
	WorkspacePresenter workspace = presenters.workspace.get();
	EntrancePresenter entrancePresenter = presenters.entrance.get();
	entrancePresenter.revealOn(workspace);
	router.fire(place);
	booka.setContent(workspace);
    }

}
