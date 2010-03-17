package net.boklab.module.archives.client.places;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.ui.wip.WipPresenter;
import net.boklab.document.client.doc.DocumentPresenter;
import net.boklab.module.archives.client.archive.browser.ArchiveBrowserPresenter;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ArchivesWorkspace {
    private final Provider<BookaAppPresenter> booka;
    private final Provider<WorkspacePresenter> workspace;
    private final Provider<ArchiveBrowserPresenter> browser;
    private final Provider<WipPresenter> wip;
    private final Provider<DocumentPresenter> document;

    @Inject
    public ArchivesWorkspace(final Provider<BookaAppPresenter> booka,
	    final Provider<WorkspacePresenter> workspace,
	    final Provider<ArchiveBrowserPresenter> browser,
	    final Provider<DocumentPresenter> document, final Provider<WipPresenter> wip) {
	this.booka = booka;
	this.workspace = workspace;
	this.browser = browser;
	this.document = document;
	this.wip = wip;
    }

    public void prepare() {
	browser.get();
	document.get();
    }

    public void setDocument(final Bok bok) {
	document.get().setDocument(bok);
    }

    public void show(final boolean hasDocument) {
	booka.get().setContent(workspace.get());
	final WorkspacePresenter w = workspace.get();
	w.setCenter(hasDocument ? document.get() : null);
	w.setLeft(browser.get());
	w.setRight(wip.get());
    }
}
