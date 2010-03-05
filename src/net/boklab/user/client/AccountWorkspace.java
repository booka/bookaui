package net.boklab.user.client;

import net.boklab.core.client.ui.wip.WipPresenter;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class AccountWorkspace {

    private final Provider<WipPresenter> wip;
    private final Provider<WorkspacePresenter> workspace;
    private final Provider<BookaAppPresenter> booka;

    @Inject
    public AccountWorkspace(final Provider<BookaAppPresenter> booka, final Provider<WorkspacePresenter> workspace,
	    final Provider<WipPresenter> wip) {
	this.booka = booka;
	this.workspace = workspace;
	this.wip = wip;
    }

    public void show() {
	final WorkspacePresenter w = workspace.get();
	w.setLeft(null);
	w.setRight(null);
	w.setCenter(wip.get());
	booka.get().setContent(w);
    }
}
