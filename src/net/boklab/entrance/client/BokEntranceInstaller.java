package net.boklab.entrance.client;

import net.boklab.project.client.action.ProjectListRetrievedEvent;
import net.boklab.project.client.action.ProjectListRetrievedHandler;
import net.boklab.project.client.action.ProjectManager;
import net.boklab.project.client.ui.ProjectListPresenter;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.PlaceRequestEvent;
import net.boklab.tools.client.place.PlaceRequestHandler;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class BokEntranceInstaller {
    @Inject
    public BokEntranceInstaller(final EventBus eventBus, final Router router,
	    final Provider<BookaAppPresenter> bookaProvider, final Provider<WorkspacePresenter> workspaceProvider,
	    final Provider<ProjectListPresenter> projectList, final ProjectManager projects) {

	router.onRequest("^/entrance$", new PlaceRequestHandler() {
	    @Override
	    public void onPlaceRequest(final PlaceRequestEvent event) {
		projects.getProjectList();
		bookaProvider.get().setContent(workspaceProvider.get());
		router.fireChanged(I18nEntrance.t.placeEntrance(), event.getPlace());
	    }
	});

	projects.onProjectList(new ProjectListRetrievedHandler() {
	    @Override
	    public void onProjectList(final ProjectListRetrievedEvent event) {
		final WorkspacePresenter workspace = workspaceProvider.get();
		workspace.setLeft(null);
		workspace.setRight(projectList.get());
	    }
	});
    }
}
