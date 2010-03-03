package net.boklab.document.client.persistence;

import com.google.gwt.event.shared.GwtEvent;

public class DocumentRequestEvent extends GwtEvent<DocumentRequestHandler> {

    private static final Type<DocumentRequestHandler> TYPE = new Type<DocumentRequestHandler>();

    public static Type<DocumentRequestHandler> getType() {
	return TYPE;
    }

    private final String documentId;

    public DocumentRequestEvent(final String documentId) {
	this.documentId = documentId;
    }

    @Override
    public Type<DocumentRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public String getDocumentId() {
	return documentId;
    }

    @Override
    protected void dispatch(final DocumentRequestHandler handler) {
	handler.onDocumentRequest(this);
    }

}
