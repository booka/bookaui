package net.boklab.document.client.manager;

import com.google.gwt.event.shared.EventHandler;

public interface BokUpdatedHandler extends EventHandler {
    void onBokUpdated(BokUpdatedEvent event);
}
