package net.boklab.core.client.persistence;

import net.boklab.core.client.model.BokResponse;

public class BokUpdatedEvent extends BokResponseEvent<BokUpdatedHandler> {

    private static final Type<BokUpdatedHandler> TYPE = new Type<BokUpdatedHandler>();

    public static Type<BokUpdatedHandler> getType() {
	return TYPE;
    }

    public BokUpdatedEvent(final BokResponse response) {
	super(response);
    }

    @Override
    public Type<BokUpdatedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(final BokUpdatedHandler handler) {
	handler.onBokUpdated(this);
    }

}
