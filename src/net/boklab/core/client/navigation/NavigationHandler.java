package net.boklab.core.client.navigation;

import com.google.gwt.event.shared.EventHandler;

public interface NavigationHandler extends EventHandler {
    void onNavigation(NavigationEvent event);
}
