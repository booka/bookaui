package net.boklab.document.client.manager;

import net.boklab.document.client.model.Document;

import com.google.gwt.event.shared.GwtEvent;

public class DocumentOpenedEvent extends GwtEvent<DocumentOpenedHandler> {

    public static final Type<DocumentOpenedHandler> TYPE = new Type<DocumentOpenedHandler>();
    private final Document document;

    public DocumentOpenedEvent(Document document) {
	this.document = document;
    }

    @Override
    public Type<DocumentOpenedHandler> getAssociatedType() {
	return TYPE;
    }

    public Document getDocument() {
	return document;
    }

    @Override
    protected void dispatch(DocumentOpenedHandler handler) {
	handler.onDocumentOpened(this);
    }

}
