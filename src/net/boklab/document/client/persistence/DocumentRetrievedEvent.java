package net.boklab.document.client.persistence;

import net.boklab.document.client.model.Document;

import com.google.gwt.event.shared.GwtEvent;

public class DocumentRetrievedEvent extends GwtEvent<DocumentRetrievedHandler> {

    private static final Type<DocumentRetrievedHandler> TYPE = new Type<DocumentRetrievedHandler>();
    private final Document document;

    public DocumentRetrievedEvent(Document document) {
	this.document = document;
    }

    @Override
    public Type<DocumentRetrievedHandler> getAssociatedType() {
	return getType();
    }

    public Document getDocument() {
	return document;
    }

    @Override
    protected void dispatch(DocumentRetrievedHandler handler) {
	handler.onDocumentRetrieved(this);
    }

    public static Type<DocumentRetrievedHandler> getType() {
	return TYPE;
    }

}
