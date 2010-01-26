package net.boklab.document.client.info.edit;

import net.boklab.document.client.model.Document;

import com.google.gwt.event.shared.GwtEvent;

public class DocInfoEditCancelledEvent extends GwtEvent<DocInfoEditCancelledHandler> {

    private static final Type<DocInfoEditCancelledHandler> TYPE = new Type<DocInfoEditCancelledHandler>();
    private final Document document;

    public DocInfoEditCancelledEvent(Document document) {
	this.document = document;
    }

    @Override
    public Type<DocInfoEditCancelledHandler> getAssociatedType() {
	return TYPE;
    }

    public Document getDocument() {
	return document;
    }

    @Override
    protected void dispatch(DocInfoEditCancelledHandler handler) {
	handler.onCancel(this);
    }

}
