package net.boklab.site.client;

import net.boklab.browser.client.ui.DocumentBrowserPresenter;
import net.boklab.core.client.wip.WipPresenter;
import net.boklab.document.client.doc.DocumentPresenter;
import net.boklab.document.client.model.Document;
import net.boklab.document.client.persistence.DocumentRequestEvent;
import net.boklab.document.client.persistence.DocumentRequestHandler;
import net.boklab.document.client.persistence.DocumentRetrievedEvent;
import net.boklab.document.client.persistence.DocumentRetrievedHandler;
import net.boklab.document.client.persistence.Documents;
import net.boklab.site.client.action.ProjectManager;
import net.boklab.site.client.action.ProjectOpenedEvent;
import net.boklab.site.client.action.ProjectOpenedHandler;
import net.boklab.site.client.action.ProjectRequestEvent;
import net.boklab.site.client.action.ProjectRequestHandler;
import net.boklab.site.client.model.Project;
import net.boklab.tools.client.place.Place;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.tools.client.router.Router.Paths;
import net.boklab.workspace.client.event.UserMessageEvent.Level;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class SitesController {

    private final Provider<DocumentPresenter> document;
    private final Provider<WorkspacePresenter> workspace;
    private final Provider<DocumentBrowserPresenter> browser;

    private final Provider<WipPresenter> wip;
    private final BookaAppPresenter booka;
    protected Integer projectLoadMid;
    protected Integer documentLoadMid;
    private final NavigationPresenter navigation;

    @Inject
    public SitesController(final Router router, final ProjectManager projects, final Documents documents,
	    final BookaAppPresenter booka, final Provider<DocumentBrowserPresenter> browser,
	    final Provider<WipPresenter> wip, final Provider<WorkspacePresenter> workspace,
	    final Provider<DocumentPresenter> document, final NavigationPresenter navigation) {

	this.booka = booka;
	this.navigation = navigation;
	final String ARCHIVES = "archivos";
	final String PROJECTS = I18nSite.t.projectsResourceName();
	final String DOCUMENTS = "documentos";

	navigation.setResource(NavigationDisplay.ARCHIVES, ARCHIVES);

	this.browser = browser;
	this.wip = wip;
	this.workspace = workspace;

	this.document = document;
	router.onRequest(Paths.singletonResource(ARCHIVES), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		Log.debug("ArchivesPresenter: Redirect to project id");
		if (projects.hasActiveProject()) {
		    router.fireRequest(new Place(PROJECTS, projects.getActiveProject().getId()));
		}
	    }
	});

	router.onRequest(Paths.show(PROJECTS), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final Place place = event.getPlace();
		final String projectId = place.id;
		projects.openProject(projectId, true, null);
		router.fireChanged(I18nSite.t.unknownProjectName(), place);
	    }
	});

	router.onRequest(Paths.show(DOCUMENTS), new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		final String documentId = event.getPlace().id;
		documents.openDocument(documentId);
	    }
	});

	projects.addProjectRequestHandler(new ProjectRequestHandler() {
	    @Override
	    public void onProjectRequest(final ProjectRequestEvent event) {
		if (event.isChangePlace()) {
		    projectLoadMid = navigation.fireUserMessage(I18nSite.t.loadingProject(), Level.working);
		}
		browser.get();
		showArchivesWorkspace(false);
	    }
	});

	projects.onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(final ProjectOpenedEvent event) {
		final Project project = event.getProject();
		projectLoadMid = removeMessageId(projectLoadMid);
		navigation.setProject(I18nSite.t.projectName(project.getTitle()));
		if (event.isChangePlace()) {
		    router.fireChanged(project.getTitle(), new Place(PROJECTS, project.getId()));
		}
		document.get();
	    }
	});

	documents.addDocumentRequestHandler(new DocumentRequestHandler() {
	    @Override
	    public void onDocumentRequest(final DocumentRequestEvent event) {
		documentLoadMid = navigation.fireUserMessage(I18nSite.t.loadingDocument(), Level.working);
		document.get();
		showArchivesWorkspace(false);
	    }
	});

	documents.addDocumentRetrievedHandler(new DocumentRetrievedHandler() {
	    @Override
	    public void onDocumentRetrieved(final DocumentRetrievedEvent event) {
		documentLoadMid = removeMessageId(documentLoadMid);
		final Document document = event.getDocument();
		projects.openProject(document.getParentId(), false, null);
		router.fireChanged(document.getTitle(), new Place(DOCUMENTS, document.getId()));
		showArchivesWorkspace(true);
	    }
	});
    }

    private void showArchivesWorkspace(final boolean hasDocument) {
	booka.setContent(workspace.get());
	final WorkspacePresenter w = workspace.get();
	w.setCenter(hasDocument ? document.get() : null);
	w.setLeft(browser.get());
	w.setRight(wip.get());
    }

    protected Integer removeMessageId(final Integer messageId) {
	if (messageId != null) {
	    navigation.removeMessage(messageId);
	}
	// always return null to clear the recipient
	return null;
    }
}
