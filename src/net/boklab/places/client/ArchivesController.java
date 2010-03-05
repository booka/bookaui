package net.boklab.places.client;

import net.boklab.core.client.bok.events.BokOpenedEvent;
import net.boklab.core.client.bok.events.BokOpenedHandler;
import net.boklab.core.client.bok.events.OpenBokEvent;
import net.boklab.core.client.bok.events.OpenBokHandler;
import net.boklab.core.client.model.Bok;
import net.boklab.document.client.DocumentManager;
import net.boklab.site.client.ProjectManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.event.UserMessageEvent.Level;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArchivesController {
    protected int projectLoadingUM;
    protected int documentLoadingUM;

    @Inject
    public ArchivesController(final Router router, final ProjectManager projects,
	    final DocumentManager documents, final ArchivesWorkspace workspace,
	    final NavigationPresenter navigation) {

	projectLoadingUM = documentLoadingUM = -1;

	final String ARCHIVES = "archivos";
	final String PROJECTS = I18nPlaces.t.projectsResourceName();
	final String DOCUMENTS = "documentos";

	navigation.setResource(NavigationDisplay.ARCHIVES, ARCHIVES);
	navigation.setProjectIconsVisible(projects.hasActive());

	router.onRequest(Paths.singletonResource(ARCHIVES), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		Log.debug("ArchivesPresenter: Redirect to project id");
		if (projects.hasActive()) {
		    router.fireRequest(new Place(PROJECTS, projects.getActive().getId()));
		}
		navigation.setActive(NavigationDisplay.ARCHIVES);
	    }
	});

	router.onRequest(Paths.show(PROJECTS), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final Place place = event.getPlace();
		final String projectId = place.id;
		projects.open(projectId, null, true);
		router.setCurrent(I18nPlaces.t.unknownProjectName(), place);
		navigation.setActive(NavigationDisplay.ARCHIVES);
	    }
	});

	router.onRequest(Paths.show(DOCUMENTS), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final String documentId = event.getPlace().id;
		documents.open(documentId, null, true);
		navigation.setActive(NavigationDisplay.ARCHIVES);
	    }
	});

	projects.addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		if (event.isChangePlace()) {
		    GWT.log("ARCHIVES Project force true!");
		    projectLoadingUM = navigation.fireUserMessage(I18nPlaces.t.loadingProject(),
			    Level.working);
		    router.setCurrent(event.getKnownTitle(), new Place(PROJECTS, event.getBokId()));
		    workspace.show(false);
		}
		workspace.prepare();
		navigation.setActive(NavigationDisplay.ARCHIVES);
	    }
	});

	projects.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		final Bok project = event.getBok();
		projectLoadingUM = navigation.removeMessage(projectLoadingUM);
		final String projectName = I18nPlaces.t.projectName(project.getTitle());
		navigation.setProject(projectName);
		navigation.setPlace(projectName);
		navigation.setProjectIconsVisible(projects.hasActive());
		workspace.prepare();
	    }
	});

	documents.addOpenHandler(new OpenBokHandler() {
	    @Override
	    public void onOpenBok(final OpenBokEvent event) {
		final String title = event.getKnownTitle();
		final String message = title != null ? I18nPlaces.t.loadingDocument(title)
			: I18nPlaces.t.loadingDocumentUnknown();
		documentLoadingUM = navigation.fireUserMessage(message, Level.working);
		workspace.prepare();
		// workspace.show(true);
		navigation.setActive(NavigationDisplay.ARCHIVES);
	    }
	});

	documents.addOpenedHandler(new BokOpenedHandler() {
	    @Override
	    public void onBokOpened(final BokOpenedEvent event) {
		documentLoadingUM = navigation.removeMessage(documentLoadingUM);
		final Bok document = event.getBok();
		navigation.setPlace(document.getTitle());
		projects.open(document.getParentId(), null, false);
		router.setCurrent(document.getTitle(), new Place(DOCUMENTS, document.getId()));
		// /workspace.show(true);
	    }
	});

    }

}
