package net.boklab.core.client.bok.events;

import net.boklab.core.client.model.Bok;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateBokEvent extends GwtEvent<UpdateBokHandler> {
    private static final Type<UpdateBokHandler> TYPE = new Type<UpdateBokHandler>();

    public static Type<UpdateBokHandler> getType() {
	return TYPE;
    }
    private final Bok bok;

    private final BokUpdatedHandler handler;

    public UpdateBokEvent(final Bok bok, final BokUpdatedHandler handler) {
	this.bok = bok;
	this.handler = handler;
    }

    @Override
    public Type<UpdateBokHandler> getAssociatedType() {
	return TYPE;
    }

    public Bok getBok() {
	return bok;
    }

    public BokUpdatedHandler getHandler() {
	return handler;
    }

    public boolean isBokType(final String type) {
	final String bokType = bok.getBokType();
	return type == null ? null == bokType : type.equals(bokType);
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + bok.getBokType() + bok.getId();
    }

    @Override
    protected void dispatch(final UpdateBokHandler handler) {
	handler.onUpdateBok(this);
    }
}
