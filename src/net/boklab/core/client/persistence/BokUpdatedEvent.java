package net.boklab.core.client.persistence;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokResponse;

public class BokUpdatedEvent extends BokResponseEvent<BokUpdatedHandler> {

    private static final Type<BokUpdatedHandler> TYPE = new Type<BokUpdatedHandler>();

    public static Type<BokUpdatedHandler> getType() {
	return TYPE;
    }

    public BokUpdatedEvent(final Bok bok, final BokResponse response) {
	super(bok, response);
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
