package net.boklab.tools.client.rest;

import com.google.gwt.event.shared.GwtEvent;

public class RestRequestErrorEvent extends GwtEvent<RestRequestErrorHandler> {

    public static final Type<RestRequestErrorHandler> TYPE = new Type<RestRequestErrorHandler>();
    public final String requestId;
    public final Throwable exception;

    public RestRequestErrorEvent(String requestId, Throwable exception) {
	this.requestId = requestId;
	this.exception = exception;
    }

    @Override
    public Type<RestRequestErrorHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(RestRequestErrorHandler handler) {
	handler.onError(this);
    }
}
