package net.boklab.core.client.navigation;

import net.boklab.tools.client.eventbus.EventBus;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class NavigationManager {

    private final EventBus eventBus;

    @Inject
    public NavigationManager(final EventBus eventBus) {
	this.eventBus = eventBus;
    }

    public void addNavigationHandler(final String navigationToken, final NavigationHandler handler) {

	eventBus.addHandler(NavigationEvent.getType(), new NavigationHandler() {
	    @Override
	    public void onNavigation(final NavigationEvent event) {
		if (event.getName().equals(navigationToken)) {
		    handler.onNavigation(event);
		}
	    }
	});

    }

}
