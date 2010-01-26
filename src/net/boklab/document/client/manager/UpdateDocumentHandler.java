package net.boklab.document.client.manager;

import net.boklab.document.client.model.Document;

import com.google.gwt.event.shared.EventHandler;

public interface UpdateDocumentHandler extends EventHandler {

    void onUpdateDocument(Document document);

}
