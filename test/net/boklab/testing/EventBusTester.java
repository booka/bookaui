package net.boklab.testing;

import net.boklab.tools.client.eventbus.DefaultEventBus;

import com.google.gwt.event.shared.GwtEvent;

public class EventBusTester extends DefaultEventBus {
    private GwtEvent<?> lastEvent;

    @Override
    public void fireEvent(GwtEvent<?> event) {
	this.lastEvent = event;
	super.fireEvent(event);
    }

    public GwtEvent<?> getLastEvent() {
	return lastEvent;
    }

    @SuppressWarnings("unchecked")
    public Class<? extends GwtEvent> getLastEventType() {
	return lastEvent.getClass();
    }

}
