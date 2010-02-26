package net.boklab.core.client.persistence;

import net.boklab.core.client.model.BokResponse;

public class BokListRetrievedEvent extends BokResponseEvent<BokListRetrievedHandler> {

    private static final Type<BokListRetrievedHandler> TYPE = new Type<BokListRetrievedHandler>();

    public static Type<BokListRetrievedHandler> getType() {
	return TYPE;
    }

    public BokListRetrievedEvent(final BokResponse response) {
	super(null, response);
    }

    @Override
    public Type<BokListRetrievedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(final BokListRetrievedHandler handler) {
	handler.onBokListRetrieved(this);
    }

}
