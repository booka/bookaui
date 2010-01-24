package net.boklab.booka.client;

import net.boklab.archives.client.ArchivesPresenter;
import net.boklab.booka.client.ui.app.BookaAppPresenter;
import net.boklab.entrance.client.EntrancePresenter;
import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BookaPresenters {
    public final Provider<BookaAppPresenter> bookaApp;
    public final Provider<EntrancePresenter> entrance;
    public final Provider<WorkspacePresenter> workspace;
    public final Provider<ArchivesPresenter> archives;

    @Inject
    public BookaPresenters(Provider<BookaAppPresenter> bookaApp,
	    Provider<EntrancePresenter> entrance, Provider<WorkspacePresenter> workspace,
	    Provider<ArchivesPresenter> archives) {
	this.bookaApp = bookaApp;
	this.entrance = entrance;
	this.workspace = workspace;
	this.archives = archives;
    }
}
