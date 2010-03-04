package net.boklab.core.client.bok.events;

import net.boklab.core.client.model.Bok;

public class BokUpdatedEvent extends AbstractBokEvent<BokUpdatedHandler> {

    private static final Type<BokUpdatedHandler> TYPE = new Type<BokUpdatedHandler>();

    public static Type<BokUpdatedHandler> getType() {
	return TYPE;
    }

    public BokUpdatedEvent(final Bok bok) {
	super(bok);
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
