package net.boklab.core.client.persistence;

import com.google.gwt.event.shared.EventHandler;

public interface CreateBokHandler extends EventHandler {
    void onCreateBok(CreateBokEvent event);
}