package net.boklab.document.client.persistence;

import com.google.gwt.event.shared.EventHandler;

public interface DocumentRequestHandler extends EventHandler {

    void onDocumentRequest(DocumentRequestEvent event);

}
