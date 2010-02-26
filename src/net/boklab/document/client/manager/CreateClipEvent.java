package net.boklab.document.client.manager;

import net.boklab.document.client.model.Clip;
import net.boklab.document.client.model.Document;

import com.google.gwt.event.shared.GwtEvent;

public class CreateClipEvent extends GwtEvent<CreateClipHandler> {

    public static final Type<CreateClipHandler> TYPE = new Type<CreateClipHandler>();
    private final Document document;
    private final Clip clip;

    public CreateClipEvent(final Document document, final Clip clip) {
	this.document = document;
	this.clip = clip;
    }

    @Override
    public Type<CreateClipHandler> getAssociatedType() {
	return TYPE;
    }

    public Clip getClip() {
	return clip;
    }

    public Document getDocument() {
	return document;
    }

    @Override
    protected void dispatch(final CreateClipHandler handler) {
	handler.onCreateClip(this);
    }

}
