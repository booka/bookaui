package net.boklab.document.client.persistence;

import com.google.gwt.event.shared.EventHandler;

public interface ClipCreatedHandler extends EventHandler {
    void onClipCreated(ClipCreatedEvent event);
}
