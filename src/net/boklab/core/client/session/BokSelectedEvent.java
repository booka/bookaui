package net.boklab.core.client.session;

import net.boklab.core.client.model.Bok;

import com.google.gwt.event.shared.GwtEvent;

public class BokSelectedEvent extends GwtEvent<BokSelectedHandler> {
    private static final Type<BokSelectedHandler> TYPE = new Type<BokSelectedHandler>();

    public static Type<BokSelectedHandler> getType() {
	return TYPE;
    }

    private final Bok bok;
    private final String bokType;

    public BokSelectedEvent(final Bok bok) {
	this.bok = bok;
	bokType = bok.getBokType();
    }

    @Override
    public Type<BokSelectedHandler> getAssociatedType() {
	return getType();
    }

    public Bok getBok() {
	return bok;
    }

    public boolean isBokType(final String bokType) {
	return this.bokType.equals(bokType);
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + bok.toString();
    }

    @Override
    protected void dispatch(final BokSelectedHandler handler) {
	handler.onBokSelected(this);
    }

}
