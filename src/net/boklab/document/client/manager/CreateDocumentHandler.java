package net.boklab.document.client.manager;

import com.google.gwt.event.shared.EventHandler;

public interface CreateDocumentHandler extends EventHandler {
    void onCreateDocument(CreateDocumentEvent event);
}
