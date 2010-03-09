package net.boklab.tools.client.place;

import com.google.gwt.event.shared.GwtEvent;

public class PlaceChangedEvent extends GwtEvent<PlaceChangedHandler> {

    private static final Type<PlaceChangedHandler> TYPE = new Type<PlaceChangedHandler>();

    public static Type<PlaceChangedHandler> getType() {
	return TYPE;
    }
    private final Place place;

    public PlaceChangedEvent(final Place place) {
	this.place = place;
    }

    @Override
    public Type<PlaceChangedHandler> getAssociatedType() {
	return getType();
    }

    public Place getPlace() {
	return place;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + place.toDebugString();
    }

    @Override
    protected void dispatch(final PlaceChangedHandler handler) {
	handler.onPlaceChanged(this);
    }

}
