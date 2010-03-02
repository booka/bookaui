package net.boklab.booka.client;

import net.boklab.browser.client.ui.DocumentBrowserPresenter;
import net.boklab.document.client.doc.DocumentPresenter;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.persistence.DocumentRetrievedEvent;
import net.boklab.document.client.persistence.DocumentRetrievedHandler;
import net.boklab.document.client.persistence.Documents;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class BokArchivesInstaller {

    private final Provider<DocumentPresenter> documentProvider;

    @Inject
    public BokArchivesInstaller(final Router router, final ProjectManager projects, final Documents documents,
	    final Provider<DocumentBrowserPresenter> browserProvider,
	    final Provider<WorkspacePresenter> workspaceProvider, final Provider<DocumentPresenter> documentProvider) {

	this.documentProvider = documentProvider;
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
		projects.openProject(place.resourceId);
		show(browserProvider.get(), workspaceProvider.get());
	    }
	});

	router.onRequest("^/documents/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final String documentId = event.getPlace().resourceId;
		documents.openDocument(documentId);
		show(browserProvider.get(), workspaceProvider.get());
	    }
	});

	documents.onDocumentOpened(new DocumentRetrievedHandler() {
	    @Override
	    public void onDocumentRetrieved(final DocumentRetrievedEvent event) {
		final Document document = event.getDocument();
		projects.openProject(document.getParentId());
		router.fireChanged(document.getTitle(), new Place("documents", document.getId()));
	    }
	});
    }

    private void show(final DocumentBrowserPresenter browser, final WorkspacePresenter workspace) {
	workspace.setCenter(documentProvider.get());
	workspace.setLeft(browser);
	workspace.setRight(null);
    }
}
