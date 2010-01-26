package net.boklab.document.client.manager;

import net.boklab.document.client.model.Document;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateDocumentEvent extends GwtEvent<UpdateDocumentHandler> {
    public static final Type<UpdateDocumentHandler> TYPE = new Type<UpdateDocumentHandler>();
    private final Document document;

    public UpdateDocumentEvent(Document document) {
	this.document = document;
    }

    @Override
    public Type<UpdateDocumentHandler> getAssociatedType() {
	return TYPE;
    }

    public Document getDocument() {
	return document;
    }

    @Override
    protected void dispatch(UpdateDocumentHandler handler) {
	handler.onUpdateDocument(document);
    }

}
