package net.boklab.module.explore.client;

import net.boklab.core.client.module.ModuleConfig;
import net.boklab.module.explore.client.indice.IndicesLocation;
import net.boklab.module.explore.client.indice.browser.IndiceBrowserActions;
import net.boklab.module.explore.client.manager.IndiceManager;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ExploreConfiguration {
    @Inject
    public ExploreConfiguration(final ModuleConfig config, final IndiceManager indices,
	    final IndiceBrowserActions browserActions, final IndicesLocation indicesLocation,
	    final ExplorerNavigator exploreNavigator) {

	config.addNavigationHandler(NavigationDisplay.BOOKA, exploreNavigator);
	config.addLocation(indicesLocation);
    }
}
