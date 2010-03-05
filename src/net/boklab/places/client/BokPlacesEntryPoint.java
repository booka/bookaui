package net.boklab.places.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BokPlacesEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final PlacesMessages messages = GWT.create(PlacesMessages.class);
	I18nPlaces.setMessages(messages);
    }

}
