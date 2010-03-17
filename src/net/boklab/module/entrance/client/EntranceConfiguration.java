package net.boklab.module.entrance.client;

import net.boklab.core.client.module.ModuleConfig;
import net.boklab.module.entrance.client.call.CallsLocation;
import net.boklab.module.entrance.client.site.SiteLocation;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EntranceConfiguration {

    @Inject
    public EntranceConfiguration(final ModuleConfig config, final RootLocation root,
	    final SiteLocation site, final CallsLocation calls,
	    final EntranceNavigator entranceNavigator) {

	config.addNavigationHandler(NavigationDisplay.ENTRANCE, entranceNavigator);
	config.addLocation(root);
	config.addLocation(site);
	config.addLocation(calls);

    }
}
