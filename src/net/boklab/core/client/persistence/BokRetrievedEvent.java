package net.boklab.core.client.persistence;

import net.boklab.core.client.model.BokResponse;

public class BokRetrievedEvent extends BokResponseEvent<BokRetrievedHandler> {

    private static final Type<BokRetrievedHandler> TYPE = new Type<BokRetrievedHandler>();

    public static Type<BokRetrievedHandler> getType() {
	return TYPE;
    }

    public BokRetrievedEvent(final BokResponse response) {
	super(response);
    }

    @Override
    public Type<BokRetrievedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(final BokRetrievedHandler handler) {
	handler.onBokRetrieved(this);
    }

}
