package net.boklab.project.client.action;

import net.boklab.project.client.model.ProjectDocuments;

import com.google.gwt.event.shared.EventHandler;

public interface ProjectDocumentsHandler extends EventHandler {

    void onProjectDocuments(ProjectDocuments projectDocuments);

}
