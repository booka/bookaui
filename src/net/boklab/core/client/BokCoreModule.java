package net.boklab.core.client;

import net.boklab.core.client.persistence.BokPersistence;
import net.boklab.core.client.session.Sessions;
import net.boklab.core.client.session.UserSessionManager;
import net.boklab.core.client.session.UserSessionPersistence;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class BokCoreModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(UserSessionPersistence.class).asEagerSingleton();
	bind(Sessions.class).to(UserSessionManager.class).in(Singleton.class);
	bind(BokPersistence.class).asEagerSingleton();
    }

}
