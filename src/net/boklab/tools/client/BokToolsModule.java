package net.boklab.tools.client;

import net.boklab.tools.client.eventbus.DefaultEventBus;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.GWTHistoryManager;
import net.boklab.tools.client.place.HistoryManager;

import com.google.gwt.inject.client.AbstractGinModule;

public class BokToolsModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(EventBus.class).to(DefaultEventBus.class);
	bind(HistoryManager.class).to(GWTHistoryManager.class);
    }

}
