package net.boklab.tools.client.eventbus;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultEventBus extends HandlerManager implements EventBus {

    @Inject
    public DefaultEventBus() {
	super(null);
    }

}