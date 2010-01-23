package net.boklab.tools.client.place;

import com.google.gwt.event.shared.GwtEvent;

public class PlaceRequestEvent extends GwtEvent<PlaceRequestHandler> {

    public static final Type<PlaceRequestHandler> TYPE = new Type<PlaceRequestHandler>();
    private final Place place;
    private final boolean isFromHistory;

    public PlaceRequestEvent(Place place) {
	this(place, false);
    }

    public PlaceRequestEvent(Place place, boolean isFromHistory) {
	this.place = place;
	this.isFromHistory = isFromHistory;
    }

    @Override
    public Type<PlaceRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public Place getPlace() {
	return place;
    }

    public boolean isFromHistory() {
	return isFromHistory;
    }

    @Override
    protected void dispatch(PlaceRequestHandler handler) {
	handler.onPlaceRequest(this);
    }

}
