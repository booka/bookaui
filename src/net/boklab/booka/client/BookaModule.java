package net.boklab.booka.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class BookaModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(BokEntranceInstaller.class).asEagerSingleton();
	bind(BokArchivesInstaller.class).asEagerSingleton();

    }

}
