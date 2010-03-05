package net.boklab.places.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokPlacesModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(EntranceController.class).asEagerSingleton();
	bind(ArchivesController.class).asEagerSingleton();
	bind(IndicesController.class).asEagerSingleton();
    }

}
