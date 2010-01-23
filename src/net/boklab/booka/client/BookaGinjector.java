package net.boklab.booka.client;

import net.boklab.entrance.client.EntranceGinjector;
import net.boklab.tools.client.BokToolsGinjector;
import net.boklab.workspace.client.WorkspaceGinjector;

import com.google.gwt.inject.client.GinModules;

@GinModules(BookaModule.class)
public interface BookaGinjector extends EntranceGinjector, WorkspaceGinjector, BokToolsGinjector {
    App getApp();

}
