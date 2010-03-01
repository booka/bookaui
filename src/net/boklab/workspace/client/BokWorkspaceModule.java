package net.boklab.workspace.client;

import net.boklab.workspace.client.ui.WorkspaceDisplay;
import net.boklab.workspace.client.ui.WorkspaceWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokWorkspaceModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(BokWorkspaceInstaller.class).asEagerSingleton();
	
	bind(WorkspaceDisplay.class).to(WorkspaceWidget.class);
    }

}
