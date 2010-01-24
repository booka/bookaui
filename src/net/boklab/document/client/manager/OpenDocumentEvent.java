package net.boklab.document.client.manager;

import com.google.gwt.event.shared.GwtEvent;

public class OpenDocumentEvent extends GwtEvent<OpenDocumentHandler> {

    public static final Type<OpenDocumentHandler> TYPE = new Type<OpenDocumentHandler>();
    private final String documentId;

    public OpenDocumentEvent(String documentId) {
	this.documentId = documentId;
    }

    @Override
    public Type<OpenDocumentHandler> getAssociatedType() {
	return TYPE;
    }

    public String getDocumentId() {
	return documentId;
    }

    @Override
    protected void dispatch(OpenDocumentHandler handler) {
	handler.onOpenDocument(this);
    }

}
