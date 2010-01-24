package net.boklab.project.client.action;

import net.boklab.project.client.model.Project;

import com.google.gwt.event.shared.EventHandler;

public interface ProjectOpenedHandler extends EventHandler {

    void onProject(Project Project);

}
