package net.boklab.tools.client.place;

import net.boklab.tools.client.eventbus.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;

public class PlaceManager {

    private final HistoryManager history;
    private final PlaceTokenizer tokenizer;

    @Inject
    public PlaceManager(final EventBus eventBus, final PlaceTokenizer tokenizer,
	    HistoryManager history) {
	this.tokenizer = tokenizer;
	this.history = history;
	history.addValueChangeHandler(new ValueChangeHandler<String>() {
	    @Override
	    public void onValueChange(ValueChangeEvent<String> event) {
		GWT.log("Token: " + event.getValue(), null);
		Place place = tokenizer.fromString(event.getValue());
		eventBus.fireEvent(new PlaceRequestEvent(place, true));
	    }
	});

	eventBus.addHandler(PlaceChangedEvent.TYPE, new PlaceChangedHandler() {
	    @Override
	    public void onPlaceChanged(PlaceChangedEvent event) {
		newPlace(event.getPlace());
	    }
	});
    }

    public void fireCurrentPlace() {
	history.fireCurrentHistoryState();
    }

    public void onPlaceRequest(PlaceRequestEvent event) {
	if (!event.isFromHistory()) {
	    newPlace(event.getPlace());
	}
    }

    private void newPlace(Place place) {
	String token = tokenizer.toString(place);
	history.newItem(token, false);
    }

}
