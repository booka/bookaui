package net.boklab.core.client.persistence;

import com.google.gwt.event.shared.EventHandler;

public interface BokCreatedHandler extends EventHandler {
    void onBokCreated(BokCreatedEvent event);
}
