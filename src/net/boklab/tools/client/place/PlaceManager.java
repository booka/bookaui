package net.boklab.tools.client.place;

import net.boklab.tools.client.eventbus.EventBus;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PlaceManager {

    private final HistoryManager history;
    private final PlaceTokenizer tokenizer;
    private Place currentPlace;
    private final EventBus eventBus;

    @Inject
    public PlaceManager(final EventBus eventBus, final PlaceTokenizer tokenizer, final HistoryManager history) {
	this.eventBus = eventBus;
	this.tokenizer = tokenizer;
	this.history = history;
	history.addValueChangeHandler(new ValueChangeHandler<String>() {
	    @Override
	    public void onValueChange(final ValueChangeEvent<String> event) {
		final Place place = tokenizer.fromString(event.getValue());
		eventBus.fireEvent(new PlaceRequestEvent(place, true));
	    }
	});

	eventBus.addHandler(PlaceChangedEvent.getType(), new PlaceChangedHandler() {
	    @Override
	    public void onPlaceChanged(final PlaceChangedEvent event) {
		newPlace(event.getPlace());
	    }
	});
    }

    public void fireCurrentPlace() {
	history.fireCurrentHistoryState();
    }

    public void firePlaceRequest(final PlaceRequestEvent event) {
	eventBus.fireEvent(event);
    }

    public Place getCurrentPlace() {
	return currentPlace;
    }

    public void onPlaceRequest(final PlaceRequestEvent event) {
	if (!event.isFromHistory()) {
	    newPlace(event.getPlace());
	}
    }

    private void newPlace(final Place place) {
	currentPlace = place;
	final String token = tokenizer.toString(place);
	history.newItem(token, false);
    }

}
