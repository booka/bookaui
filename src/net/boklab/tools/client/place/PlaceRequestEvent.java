package net.boklab.tools.client.place;

import com.google.gwt.event.shared.GwtEvent;

public class PlaceRequestEvent extends GwtEvent<PlaceRequestHandler> {

    private static final Type<PlaceRequestHandler> TYPE = new Type<PlaceRequestHandler>();

    public static Type<PlaceRequestHandler> getType() {
	return TYPE;
    }
    private final Place place;

    private final boolean isFromHistory;

    public PlaceRequestEvent(final Place place) {
	this(place, false);
    }

    public PlaceRequestEvent(final Place place, final boolean isFromHistory) {
	this.place = place;
	this.isFromHistory = isFromHistory;
    }

    @Override
    public Type<PlaceRequestHandler> getAssociatedType() {
	return getType();
    }

    public Place getPlace() {
	return place;
    }

    public boolean isFromHistory() {
	return isFromHistory;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + place.toDebugString() + " (from history: " + isFromHistory + ")";
    }

    @Override
    protected void dispatch(final PlaceRequestHandler handler) {
	handler.onPlaceRequest(this);
    }

}
