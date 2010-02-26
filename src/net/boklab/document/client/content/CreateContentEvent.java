package net.boklab.document.client.content;

import net.boklab.document.client.clip.ClipPresenter;

import com.google.gwt.event.shared.GwtEvent;

public class CreateContentEvent extends GwtEvent<CreateContentHandler> {

    public static final Type<CreateContentHandler> TYPE = new Type<CreateContentHandler>();
    private final ClipContentType contentType;
    private final ClipPresenter clipPresenter;

    public CreateContentEvent(final ClipContentType contentType, final ClipPresenter clipPresenter) {
	this.contentType = contentType;
	this.clipPresenter = clipPresenter;
    }

    @Override
    public Type<CreateContentHandler> getAssociatedType() {
	return TYPE;
    }

    public ClipPresenter getClipPresenter() {
	return clipPresenter;
    }

    public ClipContentType getContentType() {
	return contentType;
    }

    @Override
    protected void dispatch(final CreateContentHandler handler) {
	handler.onCreateContent(this);
    }

}
