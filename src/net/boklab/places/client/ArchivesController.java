package net.boklab.places.client;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.OpenBokEvent;
import net.boklab.core.client.bok.events.OpenBokHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.document.client.ArchiveManager;
import net.boklab.document.client.DocumentManager;
import net.boklab.site.client.ProjectManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.msg.MessageManager;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArchivesController {

    @Inject
    public ArchivesController(final Router router, final ArchiveManager archives,
	    final ProjectManager projects, final DocumentManager documents,
	    final ArchivesWorkspace workspace, final NavigationPresenter navigation,
	    final MessageManager messages) {

	final String ARCHIVES = I18nPlaces.t.resourceArchives();
	final String DOCUMENTS = "documentos";

	navigation.registerResource(NavigationDisplay.ARCHIVES, ARCHIVES);

	router.onRequest(Paths.singletonResource(ARCHIVES), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.setCurrent(event.getPlace());
		if (documents.hasActive()) {
		    router.request(new Place(DOCUMENTS, documents.getActive().getId()));
		} else if (archives.hasActive()) {
		    router.request(new Place(ARCHIVES, archives.getActiveId()));
		} else if (projects.hasActive()) {
		    workspace.show(false);
		    archives.openArchivesOfProject(projects.getActive());
		} else {
		    router.request(Place.ROOT);
		}
	    }
	});

	router.onRequest(Paths.show(ARCHIVES), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		router.setCurrent(event.getPlace());
		navigation.setActiveIcon(NavigationDisplay.ARCHIVES);
	    }
	});

	router.onRequest(Paths.show(DOCUMENTS), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final String documentId = event.getPlace().id;
		documents.open(documentId, null, true);
		navigation.setActiveIcon(NavigationDisplay.ARCHIVES);
	    }
	});

	archives.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		if (router.currentIs(ARCHIVES)) {
		    final Bok archives = event.getBok();
		    final String projectName = I18nPlaces.t.projectName(archives.getTitle());
		    navigation.setProjectName(projectName);
		    navigation.setCurrentLocation(projectName);
		    GWT.log("Redirect to archives/id");
		    router.request(new Place(ARCHIVES, archives.getId()));
		}
	    }
	});

	documents.addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		workspace.prepare();
		navigation.setActiveIcon(NavigationDisplay.ARCHIVES);
	    }
	});

	documents.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		if (router.currentIs(ARCHIVES)) {
		    final Bok document = event.getBok();
		    workspace.setDocument(document);
		    navigation.setCurrentLocation(document.getTitle());
		    archives.open(document.getParentId(), null, false);
		    router.setCurrent(new Place(DOCUMENTS, document.getId()));
		}
	    }
	});

    }
}
