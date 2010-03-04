package net.boklab.core.client.bok.events;

import com.google.gwt.event.shared.GwtEvent;

public class OpenBokEvent extends GwtEvent<OpenBokHandler> {

    private static final Type<OpenBokHandler> TYPE = new Type<OpenBokHandler>();

    public static Type<OpenBokHandler> getType() {
	return TYPE;
    }

    private final String bokId;
    private final boolean changePlace;
    private final String knownTitle;
    private final String bokType;

    public OpenBokEvent(final String bokType, final String bokId, final boolean changePlace, final String knownTitle) {
	assert bokType != null : "OpenBokEvent can't have bokType = null";
	this.bokType = bokType;
	this.bokId = bokId;
	this.changePlace = changePlace;
	this.knownTitle = knownTitle;
    }

    @Override
    public Type<OpenBokHandler> getAssociatedType() {
	return TYPE;
    }

    public String getBokId() {
	return bokId;
    }

    public String getKnownTitle() {
	return knownTitle;
    }

    public boolean isBokType(final String bokType) {
	return this.bokType.equals(bokType);
    }

    public boolean isChangePlace() {
	return changePlace;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + bokType + "-" + bokId + " '" + (knownTitle != null ? knownTitle : "¿?") + "' "
		+ (changePlace ? "force" : "");
    }

    @Override
    protected void dispatch(final OpenBokHandler handler) {
	handler.onOpenBok(this);
    }
}
