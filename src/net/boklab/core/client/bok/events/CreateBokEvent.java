package net.boklab.core.client.bok.events;

import net.boklab.core.client.model.Bok;

import com.google.gwt.event.shared.GwtEvent;

public class CreateBokEvent extends GwtEvent<CreateBokHandler> {

    public static final Type<CreateBokHandler> TYPE = new Type<CreateBokHandler>();
    private final Bok bok;
    private final BokCreatedHandler handler;

    public CreateBokEvent(final Bok bok, final BokCreatedHandler handler) {
	this.bok = bok;
	this.handler = handler;
    }

    @Override
    public Type<CreateBokHandler> getAssociatedType() {
	return TYPE;
    }

    public Bok getBok() {
	return bok;
    }

    public BokCreatedHandler getHandler() {
	return handler;
    }

    public boolean isBokType(final String bokType) {
	if (bokType == null) {
	    return bok.getBokType() == null;
	} else {
	    return bokType.equals(bok.getBokType());
	}
    }

    @Override
    protected void dispatch(final CreateBokHandler handler) {
	handler.onCreateBok(this);
    }

}
