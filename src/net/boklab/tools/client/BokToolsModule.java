package net.boklab.tools.client;

import net.boklab.tools.client.dispatcher.DefaultDispatcher;
import net.boklab.tools.client.dispatcher.Dispatcher;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.eventbus.LoggerEventBus;
import net.boklab.tools.client.place.GWTHistoryManager;
import net.boklab.tools.client.place.HistoryManager;
import net.boklab.tools.client.place.PlaceTokenizer;
import net.boklab.tools.client.place.RestPlaceTokenizer;
import net.boklab.tools.client.rest.DefaultRestManager;
import net.boklab.tools.client.rest.RailsRestServiceAsync;
import net.boklab.tools.client.rest.RestManager;
import net.boklab.tools.client.rest.RestServiceAsync;
import net.boklab.tools.client.router.DefaultRouter;
import net.boklab.tools.client.router.Router;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class BokToolsModule extends AbstractGinModule {

    @Override
    protected void configure() {
	bind(EventBus.class).to(LoggerEventBus.class).in(Singleton.class);
	bind(HistoryManager.class).to(GWTHistoryManager.class).in(Singleton.class);
	bind(RestServiceAsync.class).to(RailsRestServiceAsync.class).in(Singleton.class);
	bind(RestManager.class).to(DefaultRestManager.class).in(Singleton.class);
	bind(PlaceTokenizer.class).to(RestPlaceTokenizer.class).in(Singleton.class);
	bind(Dispatcher.class).to(DefaultDispatcher.class).in(Singleton.class);
	bind(Router.class).to(DefaultRouter.class).in(Singleton.class);
    }

}
