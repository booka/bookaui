package net.boklab.document.client.manager;

import com.google.gwt.event.shared.EventHandler;

public interface DocumentOpenedHandler extends EventHandler {

    void onDocumentOpened(DocumentOpenedEvent event);

}
