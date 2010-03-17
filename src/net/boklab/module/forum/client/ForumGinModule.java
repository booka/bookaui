package net.boklab.module.forum.client;

import net.boklab.module.forum.client.config.ForumConfig;

import com.google.gwt.inject.client.AbstractGinModule;

public class ForumGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(ForumConfig.class).asEagerSingleton();
    }

}
