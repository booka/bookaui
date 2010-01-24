package net.boklab.core.client;

import net.boklab.core.client.session.Sessions;
import net.boklab.core.client.session.SessionsBridge;
import net.boklab.core.client.session.SessionsWorker;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class BokCoreModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(Sessions.class).to(SessionsBridge.class).in(Singleton.class);
	bind(SessionsWorker.class).asEagerSingleton();
    }

}
