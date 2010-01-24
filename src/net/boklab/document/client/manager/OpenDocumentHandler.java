package net.boklab.document.client.manager;

import com.google.gwt.event.shared.EventHandler;

public interface OpenDocumentHandler extends EventHandler {

    void onOpenDocument(OpenDocumentEvent event);

}
