package net.boklab.places.client;

import net.boklab.core.client.bok.events.BokRetrievedEvent;
import net.boklab.core.client.bok.events.BokRetrievedHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.indices.client.IndiceManager;
import net.boklab.site.client.ProjectManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.msg.MessageManager;
import net.boklab.workspace.client.msg.CreateMessageEvent.Level;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IndicesController {
    @Inject
    public IndicesController(final Router router, final IndicesWorkspace workspace,
	    final NavigationPresenter navigation, final MessageManager messages,
	    final PlaceManager places, final ProjectManager projects, final IndiceManager indices) {

	final String accountResource = I18nPlaces.t.resourceBrowse();
	navigation.registerResource(NavigationDisplay.BOOKA, accountResource);

	router.onRequest(Paths.singletonResource(accountResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		if (indices.hasActive()) {
		    router.request(new Place(accountResource, indices.getActive().getId()));
		} else if (projects.hasActive()) {
		    final Bok project = projects.getActive();
		    final Bok indice = project.getFirstChild(Bok.INDICE);
		    GWT.log("Indice: " + indice);
		    if (indice != null) {
			router.request(new Place(accountResource, indice.getId()));
		    } else {
			messages.createMessage("IndicesController algo raro", Level.error);
		    }
		} else {

		}
	    }
	});

	router.onRequest(Paths.show(accountResource), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		indices.open(event.getPlace().id, null, false);
		navigation.setActiveIcon(NavigationDisplay.BOOKA);
		workspace.show();
		// FIXME: i18n
		router.setCurrent(event.getPlace());
	    }
	});

	indices.addRetrievedHandler(new BokRetrievedHandler() {
	    @Override
	    public void onBokRetrieved(final BokRetrievedEvent event) {
		final Bok indice = event.getBok();
		if (!projects.isActive(indice.getProjectId())) {
		    projects.open(indice.getProjectId(), null, false);
		}
	    }
	});
    }
}
