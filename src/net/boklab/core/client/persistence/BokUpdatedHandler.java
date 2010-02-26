package net.boklab.core.client.persistence;

import com.google.gwt.event.shared.EventHandler;

public interface BokUpdatedHandler extends EventHandler {
    void onBokUpdated(BokUpdatedEvent event);
}
