package net.boklab.archives.client;

import net.boklab.browser.client.ui.DocumentBrowserPresenter;
import net.boklab.document.client.manager.DocumentOpenedHandler;
import net.boklab.document.client.manager.Documents;
import net.boklab.document.client.model.DocumentClips;
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

    private Project currentProject;

    @Inject
    public ArchivesPresenter(final Router router, Projects projects, final Documents documents,
	    final DocumentBrowserPresenter browser,
	    final Provider<WorkspaceDisplay> displayProvider, DocumentPresenter document) {
	super(displayProvider);

	final WorkspaceDisplay display = getDisplay();
	display.setCenter(document.getDisplay());
	currentProject = null;

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
		currentProject = project;
		display.setRight(browser.getDisplay());
		display.setLeft(Display.NONE);
	    }
	});

	documents.onDocumentOpened(new DocumentOpenedHandler() {
	    @Override
	    public void onDocumentClips(DocumentClips documentClips) {
	    }
	});
    }

}
