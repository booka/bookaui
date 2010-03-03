package net.boklab.entrance.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokEntranceModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(EntranceController.class).asEagerSingleton();
    }

}
