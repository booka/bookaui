package net.boklab.document.client.manager;

import net.boklab.document.client.model.DocumentClips;

import com.google.gwt.event.shared.GwtEvent;

public class DocumentClipsEvent extends GwtEvent<DocumentClipsHandler> {

    public static final Type<DocumentClipsHandler> TYPE = new Type<DocumentClipsHandler>();
    private final DocumentClips documentClips;

    public DocumentClipsEvent(DocumentClips documentClips) {
	this.documentClips = documentClips;
    }

    @Override
    public Type<DocumentClipsHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(DocumentClipsHandler handler) {
	handler.onDocumentClips(documentClips);
    }

}
