package net.boklab.places.client.indice;

import net.boklab.core.client.ui.wip.WipPresenter;
import net.boklab.module.explore.client.browser.IndiceBrowserPresenter;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class IndicesWorkspace {

    private final Provider<WipPresenter> wip;
    private final Provider<WorkspacePresenter> workspace;
    private final Provider<BookaAppPresenter> booka;
    private final Provider<IndiceBrowserPresenter> indices;

    @Inject
    public IndicesWorkspace(final Provider<BookaAppPresenter> booka, final Provider<WorkspacePresenter> workspace,
	    final Provider<WipPresenter> wip, final Provider<IndiceBrowserPresenter> indices) {
	this.booka = booka;
	this.workspace = workspace;
	this.wip = wip;
	this.indices = indices;
    }

    public void show() {
	final WorkspacePresenter w = workspace.get();
	w.setLeft(indices.get());
	w.setRight(null);
	w.setCenter(wip.get());
	booka.get().setContent(w);
    }
}
