package net.boklab.core.client.bok.events;

import com.google.gwt.event.shared.EventHandler;

public interface BokUpdatedHandler extends EventHandler {
    void onBokUpdated(BokUpdatedEvent event);
}
