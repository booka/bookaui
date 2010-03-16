package net.boklab.core.client.bok.events;

import net.boklab.core.client.model.Bok;

public class BokOpenedEvent extends AbstractBokEvent<BokOpenedHandler> {

    private static final Type<BokOpenedHandler> TYPE = new Type<BokOpenedHandler>();

    public static Type<BokOpenedHandler> getType() {
	return TYPE;
    }

    public BokOpenedEvent(final Bok bok) {
	super(bok);
    }

    @Override
    public Type<BokOpenedHandler> getAssociatedType() {
	return getType();
    }

    @Override
    protected void dispatch(final BokOpenedHandler handler) {
	handler.onBokOpened(this);
    }

}
