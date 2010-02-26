package net.boklab.document.client.persistence;

import com.google.gwt.event.shared.EventHandler;

public interface DocumentRetrievedHandler extends EventHandler {

    void onDocumentRetrieved(DocumentRetrievedEvent event);

}
