package net.boklab.document.client.manager;

import net.boklab.document.client.model.DocumentClips;

import com.google.gwt.event.shared.EventHandler;

public interface DocumentOpenedHandler extends EventHandler {

    void onDocumentClips(DocumentClips documentClips);

}
