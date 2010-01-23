package net.boklab.tools.client.place;

import com.google.gwt.event.shared.GwtEvent;

public class PlaceChangedEvent extends GwtEvent<PlaceChangedHandler> {

    public static final Type<PlaceChangedHandler> TYPE = new Type<PlaceChangedHandler>();
    private final Place place;

    public PlaceChangedEvent(Place place) {
	this.place = place;
    }

    @Override
    public Type<PlaceChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public Place getPlace() {
	return place;
    }

    @Override
    protected void dispatch(PlaceChangedHandler handler) {
	handler.onPlaceChanged(this);
    }

}
