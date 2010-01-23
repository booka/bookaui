package net.boklab.workspace.client;

import net.boklab.workspace.client.ui.WorkspaceDisplay;
import net.boklab.workspace.client.ui.WorkspaceWidget;

import com.google.gwt.inject.client.AbstractGinModule;

public class WorkspaceModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(WorkspaceDisplay.class).to(WorkspaceWidget.class);
    }

}
