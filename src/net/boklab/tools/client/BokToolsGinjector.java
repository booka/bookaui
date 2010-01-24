package net.boklab.tools.client;

import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.tools.client.place.HistoryManager;
import net.boklab.tools.client.place.PlaceManager;
import net.boklab.tools.client.rest.RestServiceAsync;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(BokToolsModule.class)
public interface BokToolsGinjector extends Ginjector {
    EventBus getEventBus();

    HistoryManager getHistoryManager();

    PlaceManager getPlaceManager();

    RestServiceAsync getRestServiceAsync();
}
