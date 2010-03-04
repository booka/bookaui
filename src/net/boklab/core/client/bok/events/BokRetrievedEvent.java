package net.boklab.core.client.bok.events;

import net.boklab.core.client.model.Bok;

public class BokRetrievedEvent extends AbstractBokEvent<BokRetrievedHandler> {

    private static final Type<BokRetrievedHandler> TYPE = new Type<BokRetrievedHandler>();

    public static Type<BokRetrievedHandler> getType() {
	return TYPE;
    }

    public BokRetrievedEvent(final Bok bok) {
	super(bok);
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
