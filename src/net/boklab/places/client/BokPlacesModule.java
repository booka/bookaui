package net.boklab.places.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokPlacesModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(LocationRegistrar.class).asEagerSingleton();
	bind(NavigationPlaces.class).asEagerSingleton();
    }

}
