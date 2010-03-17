package net.boklab.module.entrance.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class EntranceGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(EntranceConfiguration.class).asEagerSingleton();
    }

}
