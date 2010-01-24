package net.boklab.booka.client;

import net.boklab.booka.client.ui.app.BookaAppPresenter;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;

public class BookaRouter {

    @Inject
    public BookaRouter(final Router router, final BookaAppPresenter booka,
	    final WorkspacePresenter workspace) {

	router.onRequest("/?", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		router.fireRequest(new Place("entrance"));
	    }
	});

	router.onRequest("^/entrance$|^/archives.*", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		Log.debug("BookaRouter: show workspace");
		booka.setContent(workspace);
		router.fireChanged(event.getPlace());
	    }
	});
    }

}
