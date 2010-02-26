package net.boklab.booka.client.ui.archives;

import net.boklab.browser.client.ui.DocumentBrowserPresenter;
import net.boklab.document.client.doc.DocumentPresenter;
import net.boklab.document.client.manager.DocumentOpenedEvent;
import net.boklab.document.client.manager.DocumentOpenedHandler;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.model.Document;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ArchivesPresenter extends AbstractPresenter<WorkspaceDisplay> {

    @Inject
    public ArchivesPresenter(final Router router, final ProjectManager projects, final Documents documents,
	    final DocumentBrowserPresenter browser, final Provider<WorkspaceDisplay> displayProvider,
	    final DocumentPresenter documentPresenter) {
	super(displayProvider);

	final WorkspaceDisplay display = getDisplay();
	display.setCenter(documentPresenter.getDisplay());

	router.onRequest("^/archives$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		Log.debug("ArchivesPresenter: Redirect to project id");
		if (projects.hasActiveProject()) {
		    router.fireRequest(new Place("archives", projects.getActiveProject().getId()));
		}
	    }
	});

	router.onRequest("^/archives/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final Place place = event.getPlace();
		Log.debug("ArchivesPresenter opens project");
		projects.openProject(place.resourceId);
		show(browser, display);
		router.fireChanged(place);
	    }
	});

	router.onRequest("^/documents/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final String documentId = event.getPlace().resourceId;
		documents.openDocument(documentId);
		show(browser, display);
		router.fireChanged(event.getPlace());
	    }
	});

	documents.onDocumentOpened(new DocumentOpenedHandler() {
	    @Override
	    public void onDocumentOpened(final DocumentOpenedEvent event) {
		final Document document = event.getDocument();
		projects.openProject(document.getParentId());
	    }
	});
    }

    private void show(final DocumentBrowserPresenter browser, final WorkspaceDisplay display) {
	display.setRight(browser.getDisplay());
	display.setLeft(Display.NONE);
    }
}
