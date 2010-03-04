package net.boklab.core.client.bok.events;

import net.boklab.core.client.model.Bok;

public class BokOpenedEvent extends AbstractBokEvent<BokOpenedHandler> {

    private static final Type<BokOpenedHandler> TYPE = new Type<BokOpenedHandler>();

    public static Type<BokOpenedHandler> getType() {
	return TYPE;
    }

    private final boolean forceOpen;

    public BokOpenedEvent(final Bok bok, final boolean forceOpen) {
	super(bok);
	this.forceOpen = forceOpen;
    }

    @Override
    public Type<BokOpenedHandler> getAssociatedType() {
	return getType();
    }

    public boolean isForceOpen() {
	return forceOpen;
    }

    @Override
    protected void dispatch(final BokOpenedHandler handler) {
	handler.onBokOpened(this);
    }

}
