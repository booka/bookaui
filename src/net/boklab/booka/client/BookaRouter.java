package net.boklab.booka.client;

import net.boklab.booka.client.ui.app.BookaAppPresenter;
import net.boklab.calendar.client.ui.CalendarPresenter;
import net.boklab.core.client.session.Sessions;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.google.inject.Inject;

public class BookaRouter {

    @Inject
    public BookaRouter(final Router router, final BookaAppPresenter booka,
	    final WorkspacePresenter workspace, final CalendarPresenter calendar,
	    final Sessions sessions) {

	router.onRequest("/?", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		router.fireRequest(new Place("entrance"));
	    }
	});

	router.onRequest("^/calendar$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		booka.setContent(calendar);
	    }
	});

	router.onRequest("^/entrance$|^/archives(/\\w+)?$|^/documents/\\w+$",
		new PlaceRequestHandler() {
		    @Override
		    public void onPlaceRequest(PlaceRequestEvent event) {
			booka.setContent(workspace);
		    }
		});

	router.onRequest("^/login$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		sessions.login("Test", "secret");
	    }
	});
    }

}
