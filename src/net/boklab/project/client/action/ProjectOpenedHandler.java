package net.boklab.project.client.action;

import com.google.gwt.event.shared.EventHandler;

public interface ProjectOpenedHandler extends EventHandler {

    void onProject(ProjectOpenedEvent event);

}
