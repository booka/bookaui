package net.boklab.core.client.bok.events;

import com.google.gwt.event.shared.EventHandler;

public interface BokCreatedHandler extends EventHandler {
    void onBokCreated(BokCreatedEvent event);
}
