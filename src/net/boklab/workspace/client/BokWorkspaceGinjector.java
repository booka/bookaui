package net.boklab.workspace.client;

import net.boklab.workspace.client.ui.WorkspacePresenter;

import com.google.gwt.inject.client.GinModules;

@GinModules(BokWorkspaceModule.class)
public interface BokWorkspaceGinjector {
    WorkspacePresenter getWorkspacePresenter();
}
