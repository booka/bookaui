package net.boklab.tools.client.place;

import com.google.gwt.event.shared.GwtEvent;

public class PlaceChangedEvent extends GwtEvent<PlaceChangedHandler> {

    public static final Type<PlaceChangedHandler> TYPE = new Type<PlaceChangedHandler>();
    private final Place place;
    private final String description;

    public PlaceChangedEvent(final String description, final Place place) {
	this.description = description;
	this.place = place;
    }

    @Override
    public Type<PlaceChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public String getDescription() {
	return description;
    }

    public Place getPlace() {
	return place;
    }

    @Override
    protected void dispatch(final PlaceChangedHandler handler) {
	handler.onPlaceChanged(this);
    }

}
