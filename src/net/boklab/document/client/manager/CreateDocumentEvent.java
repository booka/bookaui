package net.boklab.document.client.manager;

import net.boklab.document.client.model.Document;

import com.google.gwt.event.shared.GwtEvent;

public class CreateDocumentEvent extends GwtEvent<CreateDocumentHandler> {

    public static final Type<CreateDocumentHandler> TYPE = new Type<CreateDocumentHandler>();
    private final Document document;

    public CreateDocumentEvent(Document document) {
	this.document = document;
    }

    @Override
    public Type<CreateDocumentHandler> getAssociatedType() {
	return TYPE;
    }

    public Document getDocument() {
	return document;
    }

    @Override
    protected void dispatch(CreateDocumentHandler handler) {
	handler.onCreateDocument(this);
    }

}
