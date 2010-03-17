package net.boklab.module.forum.client.config;

import net.boklab.core.client.ui.wip.WipPresenter;
import net.boklab.module.forum.client.browser.ForumBrowserPresenter;
import net.boklab.workspace.client.ui.WorkspacePresenter;
import net.boklab.workspace.client.ui.app.BookaAppPresenter;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ForumWorkspace {
    private final Provider<BookaAppPresenter> booka;
    private final Provider<WorkspacePresenter> workspace;
    private final Provider<ForumBrowserPresenter> forum;
    private final Provider<WipPresenter> wip;
    private final NavigationPresenter navigation;

    @Inject
    public ForumWorkspace(final NavigationPresenter navigation,
	    final Provider<BookaAppPresenter> booka, final Provider<WorkspacePresenter> workspace,
	    final Provider<WipPresenter> wip, final Provider<ForumBrowserPresenter> forum) {
	this.navigation = navigation;
	this.booka = booka;
	this.workspace = workspace;
	this.wip = wip;
	this.forum = forum;
    }

    public void show(final String locationDescription) {
	navigation.setCurrentLocation(locationDescription);
	navigation.setActiveIcon(NavigationDisplay.EDITION);
	show();
    }

    private void show() {
	final WorkspacePresenter w = workspace.get();
	w.setLeft(forum.get());
	w.setRight(null);
	w.setCenter(wip.get());
	booka.get().setContent(w);
    }

}
