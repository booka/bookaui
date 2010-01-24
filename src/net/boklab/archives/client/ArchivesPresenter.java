package net.boklab.archives.client;

import net.boklab.browser.client.ui.DocumentBrowserPresenter;
import net.boklab.tools.client.mvp.Presenter;
import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.google.inject.Inject;

public class ArchivesPresenter {

    private final DocumentBrowserPresenter browser;

    @Inject
    public ArchivesPresenter(DocumentBrowserPresenter browser) {
	this.browser = browser;
    }

    public void revealOn(WorkspacePresenter workspace) {
	workspace.setWest(browser);
	workspace.setEast(Presenter.NONE);
    }
}
