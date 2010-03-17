package net.boklab.module.forum.client.config;

import net.boklab.core.client.module.ModuleConfig;
import net.boklab.module.forum.client.browser.ForumBrowserActions;
import net.boklab.module.forum.client.manager.ForumManager;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ForumConfig {

    @Inject
    public ForumConfig(final ModuleConfig config, final ForumManager forums,
	    final ForumBrowserActions browserActions,

	    final ForumNavigationHandler forumNavigation, final ForumLocation forumLocation) {

	config.addLocation(forumLocation);
	config.addNavigationHandler(NavigationDisplay.EDITION, forumNavigation);
    }
}
