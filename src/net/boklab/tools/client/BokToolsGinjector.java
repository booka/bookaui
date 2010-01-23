package net.boklab.tools.client;

import net.boklab.tools.client.eventbus.EventBus;

import com.google.gwt.inject.client.Ginjector;

public interface BokToolsGinjector extends Ginjector {
    EventBus getEventBus();
}
