package net.boklab.document.client.manager;

import net.boklab.core.client.model.Bok;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateBokEvent extends GwtEvent<UpdateBokHandler> {
    public static final Type<UpdateBokHandler> TYPE = new Type<UpdateBokHandler>();
    private final Bok bok;

    public UpdateBokEvent(final Bok bok) {
	this.bok = bok;
    }

    @Override
    public Type<UpdateBokHandler> getAssociatedType() {
	return TYPE;
    }

    public Bok getBok() {
	return bok;
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
