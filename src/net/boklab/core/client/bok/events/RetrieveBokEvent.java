package net.boklab.core.client.bok.events;

import com.google.gwt.event.shared.GwtEvent;

public class RetrieveBokEvent extends GwtEvent<RetrieveBokHandler> {

    public static final Type<RetrieveBokHandler> TYPE = new Type<RetrieveBokHandler>();
    private final String bokId;
    private final BokRetrievedHandler handler;

    public RetrieveBokEvent(final String bokId, final BokRetrievedHandler handler) {
	this.bokId = bokId;
	this.handler = handler;
    }

    @Override
    public Type<RetrieveBokHandler> getAssociatedType() {
	return TYPE;
    }

    public String getBokId() {
	return bokId;
    }

    public BokRetrievedHandler getHandler() {
	return handler;
    }

    @Override
    protected void dispatch(final RetrieveBokHandler handler) {
	handler.onGetBok(this);
    }

}
