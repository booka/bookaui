package net.boklab.archives.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class ArchivesModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ArchivesPresenter.class).asEagerSingleton();
    }

}
