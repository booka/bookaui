package net.boklab.archives.client;

import net.boklab.browser.client.ui.DocumentBrowserPresenter;
import net.boklab.document.client.manager.DocumentOpenedEvent;
import net.boklab.document.client.manager.DocumentOpenedHandler;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.ui.DocumentPresenter;
import net.boklab.project.client.action.ProjectOpenedHandler;
import net.boklab.project.client.action.Projects;
import net.boklab.project.client.model.Project;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspaceDisplay;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ArchivesPresenter extends AbstractPresenter<WorkspaceDisplay> {
    @Inject
    public ArchivesPresenter(final Router router, final Projects projects,
	    final Documents documents, final DocumentBrowserPresenter browser,
	    final Provider<WorkspaceDisplay> displayProvider, DocumentPresenter documentPresenter) {
	super(displayProvider);

	final WorkspaceDisplay display = getDisplay();
	display.setCenter(documentPresenter.getDisplay());

	router.onRequest("^/documents/\\w+$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(PlaceRequestEvent event) {
		String documentId = event.getPlace().resourceId;
		documents.openDocument(documentId);
		router.fireChanged(event.getPlace());
	    }
	});

	projects.onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(Project project) {
		display.setRight(browser.getDisplay());
		display.setLeft(Display.NONE);
	    }
	});

	documents.onDocumentOpened(new DocumentOpenedHandler() {
	    @Override
	    public void onDocumentOpened(DocumentOpenedEvent event) {
		Document document = event.getDocument();
		projects.openProject(document.getParentId());
	    }
	});
    }

}
