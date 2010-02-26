package net.boklab.core.client.persistence;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.model.BokResponse;

public class BokCreatedEvent extends BokResponseEvent<BokCreatedHandler> {

    private static final Type<BokCreatedHandler> TYPE = new Type<BokCreatedHandler>();

    public static Type<BokCreatedHandler> getType() {
	return TYPE;
    }

    public BokCreatedEvent(final Bok bok, final BokResponse response) {
	super(bok, response);
    }

    @Override
    public Type<BokCreatedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(final BokCreatedHandler handler) {
	handler.onBokCreated(this);
    }

}
