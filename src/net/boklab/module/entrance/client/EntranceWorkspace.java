package net.boklab.module.entrance.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.ui.wip.WipPresenter;
import net.boklab.document.client.doc.DocumentPresenter;
import net.boklab.module.entrance.client.project.browser.ProjectBrowserPresenter;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EntranceWorkspace {

    private final Provider<BookaAppPresenter> bookaProvider;
    private final Provider<WorkspacePresenter> workspace;
    private final Provider<WipPresenter> wip;
    private final Provider<ProjectBrowserPresenter> projectBrowser;
    private final Provider<DocumentPresenter> document;

    @Inject
    public EntranceWorkspace(final Provider<BookaAppPresenter> bookaProvider,
	    final Provider<WorkspacePresenter> workspace, final Provider<WipPresenter> wip,
	    final Provider<ProjectBrowserPresenter> projectBrowser,
	    final Provider<DocumentPresenter> document) {
	this.bookaProvider = bookaProvider;
	this.workspace = workspace;
	this.wip = wip;
	this.projectBrowser = projectBrowser;
	this.document = document;
    }

    public void prepare() {
	projectBrowser.get();
    }

    public void setDocument(final Bok bok) {
	document.get().setDocument(bok);
    }

    public void show() {
	final WorkspacePresenter w = workspace.get();
	w.setCenter(document.get());
	w.setVisible(true);
	w.setLeft(wip.get());
	w.setRight(projectBrowser.get());
	bookaProvider.get().setContent(w);
    }
}
