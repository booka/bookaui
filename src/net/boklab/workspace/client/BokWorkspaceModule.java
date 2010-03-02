package net.boklab.workspace.client;

import net.boklab.workspace.client.ui.WorkspaceDisplay;
import net.boklab.workspace.client.ui.WorkspaceWidget;
import net.boklab.workspace.client.ui.app.BookaAppDisplay;
import net.boklab.workspace.client.ui.app.BookaAppWidget;
import net.boklab.workspace.client.ui.navigation.NavigationDisplay;
import net.boklab.workspace.client.ui.navigation.NavigationWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokWorkspaceModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(BokWorkspaceInstaller.class).asEagerSingleton();

	bind(WorkspaceDisplay.class).to(WorkspaceWidget.class);
	bind(NavigationDisplay.class).to(NavigationWidget.class);
	bind(BookaAppDisplay.class).to(BookaAppWidget.class);
    }

}
