package net.boklab.booka.client;

import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;

public class BookaRouter {

    @Inject
    public BookaRouter(final Router router, final BookaAppPresenter booka, final WorkspacePresenter workspace) {

	router.onRequest("/?", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.fireRequest(new Place("entrance"));
	    }
	});

	router.onRequest("^/archives(/\\w+)?$|^/documents/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		booka.setContent(workspace);
	    }
	});

    }

}
