package net.boklab.core.client.bok.events;

import net.boklab.core.client.model.Bok;

public class BokCreatedEvent extends AbstractBokEvent<BokCreatedHandler> {

    private static final Type<BokCreatedHandler> TYPE = new Type<BokCreatedHandler>();

    public static Type<BokCreatedHandler> getType() {
	return TYPE;
    }

    public BokCreatedEvent(final Bok bok) {
	super(bok);
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
