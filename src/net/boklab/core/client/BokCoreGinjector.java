package net.boklab.core.client;

import net.boklab.core.client.session.Sessions;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(BokCoreModule.class)
public interface BokCoreGinjector extends Ginjector {
    Sessions getSessions();
}
