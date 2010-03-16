package net.boklab.core.client;

import net.boklab.core.client.user.UserSessionManager;
import net.boklab.core.client.user.UserSessionPersistence;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(BokCoreModule.class)
public interface BokCoreGinjector extends Ginjector {
    UserSessionManager getSessions();

    UserSessionPersistence getUserSessionPersistence();
}
