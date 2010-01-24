package net.boklab.document.client.manager;

import net.boklab.document.client.model.DocumentClips;

import com.google.gwt.event.shared.GwtEvent;

public class DocumentOpenedEvent extends GwtEvent<DocumentOpenedHandler> {

    public static final Type<DocumentOpenedHandler> TYPE = new Type<DocumentOpenedHandler>();
    private final DocumentClips documentClips;

    public DocumentOpenedEvent(DocumentClips documentClips) {
	this.documentClips = documentClips;
    }

    @Override
    public Type<DocumentOpenedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(DocumentOpenedHandler handler) {
	handler.onDocumentClips(documentClips);
    }

}
