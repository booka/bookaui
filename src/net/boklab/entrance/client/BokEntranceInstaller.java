package net.boklab.entrance.client;

import net.boklab.core.client.session.Sessions;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.action.ProjectOpenedEvent;
import net.boklab.project.client.action.ProjectOpenedHandler;
import net.boklab.project.client.action.SiteRetrievedEvent;
import net.boklab.project.client.action.SiteRetrievedHandler;
import net.boklab.project.client.ui.ProjectBrowserPresenter;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class BokEntranceInstaller {
    @Inject
    public BokEntranceInstaller(final EventBus eventBus, final Router router,
	    final Provider<BookaAppPresenter> bookaProvider, final Provider<WorkspacePresenter> workspaceProvider,
	    final Provider<NavigationPresenter> navigationProvider,
	    final Provider<ProjectBrowserPresenter> projectList, final ProjectManager projects, final Sessions sessions) {

	router.onRequest("^/entrance$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		projects.getSite();
		bookaProvider.get().setContent(workspaceProvider.get());
		router.fireChanged(I18nEntrance.t.placeEntrance(), event.getPlace());
	    }
	});

	projects.onSiteRetrieved(new SiteRetrievedHandler() {
	    @Override
	    public void onProjectList(final SiteRetrievedEvent event) {
		final WorkspacePresenter workspace = workspaceProvider.get();
		workspace.setLeft(null);
		workspace.setRight(projectList.get());
	    }
	});

	projects.onProjectOpened(new ProjectOpenedHandler() {
	    @Override
	    public void onProject(final ProjectOpenedEvent event) {
		final NavigationPresenter navigation = navigationProvider.get();
		// FIXME: i18n
		navigation.setProject("Investigación: " + event.getProject().getTitle());
	    }
	});
    }
}
