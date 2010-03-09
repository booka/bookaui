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
    public PlaceManager(final EventBus eventBus, final PlaceTokenizer tokenizer,
	    final HistoryManager history) {
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

    public void addPlaceRequestHandler(final PlaceRequestHandler handler) {
	eventBus.addHandler(PlaceRequestEvent.getType(), handler);
    }

    public void setCurrent(final Place place) {
	if (isNewPlace(place)) {
	    eventBus.fireEvent(new PlaceChangedEvent(place));
	}
    }

    public void fireCurrentPlace() {
	history.fireCurrentHistoryState();
    }

    public Place getCurrentPlace() {
	return currentPlace;
    }

    public void request(final Place place) {
	if (isNewPlace(place)) {
	    eventBus.fireEvent(new PlaceRequestEvent(place));
	}
    }

    private boolean isNewPlace(final Place place) {
	return currentPlace == null || !currentPlace.equals(place);
    }

    private void newPlace(final Place place) {
	currentPlace = place;
	final String token = tokenizer.toString(place);
	history.newItem(token, false);
    }

}
