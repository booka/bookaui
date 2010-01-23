package net.boklab.booka.client;

import net.boklab.booka.client.ui.app.BookaAppPresenter;
import net.boklab.entrance.client.EntrancePresenter;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceChangedEvent;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.place.Router;
import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

public class App {

    private final BookaPresenters presenters;
    private final EventBus eventBus;

    @Inject
    public App(EventBus eventBus, final BookaPresenters presenters, PlaceManager placeManager) {
	this.eventBus = eventBus;
	this.presenters = presenters;

	final BookaAppPresenter booka = presenters.bookaApp.get();

	RootLayoutPanel.get().add(booka.getDisplay().asWidget());
	placeManager.fireCurrentPlace();

	eventBus.addHandler(PlaceRequestEvent.TYPE, new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		Place place = event.getPlace();
		if (Router.is(place, "entrance")) {
		    showEntrance(booka, place);
		}
	    }
	});
    }

    private void showEntrance(final BookaAppPresenter booka, Place place) {
	WorkspacePresenter workspace = presenters.workspace.get();
	EntrancePresenter entrancePresenter = presenters.entrance.get();
	entrancePresenter.revealOn(workspace);
	eventBus.fireEvent(new PlaceChangedEvent(place));
	booka.setContent(workspace);
    }

}
