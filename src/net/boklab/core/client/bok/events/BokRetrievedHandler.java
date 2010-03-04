package net.boklab.core.client.bok.events;

import com.google.gwt.event.shared.EventHandler;

public interface BokRetrievedHandler extends EventHandler {

    void onBokRetrieved(BokRetrievedEvent event);

}
