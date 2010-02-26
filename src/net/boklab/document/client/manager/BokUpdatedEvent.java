package net.boklab.document.client.manager;

import net.boklab.core.client.model.Bok;

import com.google.gwt.event.shared.GwtEvent;

public class BokUpdatedEvent extends GwtEvent<BokUpdatedHandler> {

    private static final Type<BokUpdatedHandler> TYPE = new Type<BokUpdatedHandler>();

    public static Type<BokUpdatedHandler> getType() {
	return TYPE;
    }

    private final Bok bok;

    public BokUpdatedEvent(final Bok bok) {
	this.bok = bok;
    }

    @Override
    public Type<BokUpdatedHandler> getAssociatedType() {
	return TYPE;
    }

    public Bok getBok() {
	return bok;
    }

    @Override
    protected void dispatch(final BokUpdatedHandler handler) {
	handler.onBokUpdated(this);
    }

}
