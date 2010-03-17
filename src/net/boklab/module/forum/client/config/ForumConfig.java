package net.boklab.module.forum.client.config;

import net.boklab.core.client.navigation.NavigationManager;
import net.boklab.tools.client.router.Router;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ForumConfig {

    @Inject
    public ForumConfig(final Router router, final NavigationManager navigations,
	    final ForumNavigationHandler forumNavigation, final ForumLocation forumLocation) {
	router.addLocation(forumLocation);

	navigations.addNavigationHandler(NavigationDisplay.EDITION, forumNavigation);
    }
}
