package net.boklab.workspace.client.ui.navigation;

import com.google.gwt.event.shared.EventHandler;

public interface NavigationHandler extends EventHandler {
    void onNavigation(NavigationEvent event);
}
