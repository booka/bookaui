package net.boklab.entrance.client;

import net.boklab.core.client.wip.WipPresenter;
import net.boklab.site.client.ui.ProjectBrowserPresenter;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntranceWorkspace {

    private final Provider<BookaAppPresenter> bookaProvider;
    private final Provider<WorkspacePresenter> workspace;
    private final Provider<WipPresenter> wip;
    private final Provider<ProjectBrowserPresenter> projectBrowser;

    @Inject
    public EntranceWorkspace(final Provider<BookaAppPresenter> bookaProvider,
	    final Provider<WorkspacePresenter> workspace,final NavigationPresenter navigation, final Provider<WipPresenter> wip,
	    final Provider<ProjectBrowserPresenter> projectBrowser) {
	this.bookaProvider = bookaProvider;
	this.workspace = workspace;
	this.wip = wip;
	this.projectBrowser = projectBrowser;
    }

    public void prepare() {
	projectBrowser.get();
    }

    public void show() {
	final WorkspacePresenter w = workspace.get();
	w.setCenter(null);
	w.setVisible(true);
	w.setLeft(wip.get());
	w.setRight(projectBrowser.get());
	bookaProvider.get().setContent(w);
    }
}
