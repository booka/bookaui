package net.boklab.core.client.bok.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenBokEvent extends GwtEvent<OpenBokHandler> {

    private static final Type<OpenBokHandler> TYPE = new Type<OpenBokHandler>();

    public static Type<OpenBokHandler> getType() {
	return TYPE;
    }

    private final String bokId;
    private final String knownTitle;
    private final String bokType;
    private final BokOpenedHandler handler;

    public OpenBokEvent(final String bokType, final String bokId, final String knownTitle,
	    final BokOpenedHandler handler) {
	assert bokType != null : "OpenBokEvent can't have bokType = null";
	this.bokType = bokType;
	this.bokId = bokId;
	this.knownTitle = knownTitle;
	this.handler = handler;
    }

    @Override
    public Type<OpenBokHandler> getAssociatedType() {
	return TYPE;
    }

    public String getBokId() {
	return bokId;
    }

    public BokOpenedHandler getHandler() {
	return handler;
    }

    public String getKnownTitle() {
	return knownTitle;
    }

    public boolean isBokType(final String bokType) {
	return this.bokType.equals(bokType);
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + bokType + "-" + bokId + " '"
		+ (knownTitle != null ? knownTitle : "¿?") + "'";
    }

    @Override
    protected void dispatch(final OpenBokHandler handler) {
	handler.onOpenBok(this);
    }
}
