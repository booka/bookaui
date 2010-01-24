package net.boklab.tools.client.rest;

import com.google.gwt.event.shared.EventHandler;

public interface RestRequestErrorHandler extends EventHandler {

    void onError(RestRequestErrorEvent event);

}
